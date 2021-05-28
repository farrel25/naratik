package com.b21cap0051.naratik.ui.cameraui

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.le.AdvertisingSetParameters
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.util.Rational
import android.util.Size
import android.view.Surface.ROTATION_0
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.core.ImageCapture.OutputFileOptions.Builder
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.databinding.ActivityCameraBinding
import com.b21cap0051.naratik.dataresource.remotedata.model.ImageUploadModel
import com.b21cap0051.naratik.ui.cameraui.UploadProcessFragment.Companion.KEY_UPLOAD
import java.io.File
import java.nio.ByteBuffer
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.random.Random

typealias LumaListener = (luma : Double) -> Unit

class CameraActivity : AppCompatActivity()
{
	private lateinit var binding  : ActivityCameraBinding
	private lateinit var cameraExecutors : ExecutorService
	private lateinit var outputDirectory : File
	private var capturePhoto : ImageCapture? = null
	
	companion object
	{
		private const val TAG = "CameraXBasic"
		private const val REQUEST_CODE_PERMISSION = 10
		private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
	}
	
	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivityCameraBinding.inflate(layoutInflater)
		setContentView(binding.root)
		
		
		
		if (cameraGranted())
		{
			startCamera()
		} else
		{
			ActivityCompat.requestPermissions(
				this , REQUIRED_PERMISSIONS , REQUEST_CODE_PERMISSION
			                                 )
		}
		
		cameraExecutors = Executors.newSingleThreadExecutor()
		outputDirectory = getOutputDirectory()
		binding.btnCapture.setOnClickListener {
			takePhoto()
		}
		
	
	}
	
	
	private fun getOutputDirectory() : File
	{
		val mediaDir = externalMediaDirs.firstOrNull()?.let {
			File(it , resources.getString(R.string.app_name)).apply { mkdirs() }
		}
		return if (mediaDir != null && mediaDir.exists()) mediaDir else filesDir
	}
	
	private fun cameraGranted() = REQUIRED_PERMISSIONS.all {
		ContextCompat.checkSelfPermission(baseContext , it) == PackageManager.PERMISSION_GRANTED
	}
	
	@SuppressLint("RestrictedApi")
	private fun startCamera()
	{
		val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
		cameraProviderFuture.addListener(
			Runnable {
				val cameraProvider : ProcessCameraProvider = cameraProviderFuture.get()
				
				val preview = Preview.Builder()
					.setTargetRotation(ROTATION_0)
					.setTargetAspectRatio(AspectRatio.RATIO_4_3)
					.build()
					.also {
						it.setSurfaceProvider(binding.cameraFinder.surfaceProvider)
					}
				
				capturePhoto = ImageCapture.Builder()
					.setTargetRotation(binding.cameraFinder.display.rotation)
					.setTargetResolution(Size(300 , 300))
					.build()
				
				val imageAnalyzer = ImageAnalysis.Builder()
					.build()
					.apply {
						val analyzer = HandlerThread("LuminosityAnalysis").apply { start() }
						setAnalyzer(
							ThreadExecutor(Handler(analyzer.looper)),
							LuminosityAnalyzer{ luma -> Log.d(TAG,"$luma")  }
								   )
					}
				
				val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
				
				try
				{
					cameraProvider.unbindAll()
					cameraProvider.bindToLifecycle(
						this ,
						cameraSelector ,
						preview ,
						capturePhoto ,
						imageAnalyzer,
					                              )
					preview.setSurfaceProvider(binding.cameraFinder.surfaceProvider)
					val cameraControl = preview.camera?.cameraControl
					val factory = SurfaceOrientedMeteringPointFactory(1f,1f)
					val point = factory.createPoint(.5f,.5f)
					val action = FocusMeteringAction.Builder(point,FocusMeteringAction.FLAG_AF)
						.setAutoCancelDuration(2,TimeUnit.SECONDS)
						.build()
					cameraControl!!.startFocusAndMetering(action)
				} catch (e : Exception)
				{
					Log.e(TAG , "Use Case Binding Failed" , e)
				}
				
			} , ContextCompat.getMainExecutor(this)
		                                )
	}
	
	fun getRandomString(length: Int) : String {
		val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
		return (1..length)
			.map { allowedChars.random() }
			.joinToString("")
	}
	
	
	private fun takePhoto()
	{
		
		val imageCapture = capturePhoto ?: return
		val data = Random.nextInt(100)
		val filename = getRandomString(5)
		val photoFile = File(
			outputDirectory ,filename +
			data.toString() + ".jpg")
		
		val outputOptions = Builder(photoFile).build()
		imageCapture.takePicture(
			outputOptions ,
			ContextCompat.getMainExecutor(this) ,
			object : ImageCapture.OnImageSavedCallback
			{
				override fun onImageSaved(outputFileResults : ImageCapture.OutputFileResults)
				{
					val savedURI = Uri.fromFile(photoFile)
					uploadProcess(ImageUploadModel(savedURI))
				}
				
				override fun onError(e : ImageCaptureException)
				{
					Log.e(TAG , "Photo Captured fail : ${e.message} " , e)
				}
				
			})
		
	}
	
	private fun uploadProcess(modelUpload : ImageUploadModel)
	{
	    val fragment = UploadProcessFragment()
		val bData = Bundle()
		bData.putParcelable(KEY_UPLOAD, modelUpload)
		fragment.arguments = bData
		supportFragmentManager.beginTransaction()
			.add(R.id.uploadProcess,fragment,UploadProcessFragment::class.java.simpleName)
			.commit()
	}
	
	
	override fun onDestroy()
	{
		super.onDestroy()
		cameraExecutors.shutdown()
	}
	

	override fun onRequestPermissionsResult(
		requestCode : Int ,
		permissions : Array<out String> ,
		grantResults : IntArray
	                                       )
	{
		super.onRequestPermissionsResult(requestCode , permissions , grantResults)
		if (requestCode == REQUEST_CODE_PERMISSION)
		{
			if (cameraGranted())
			{
				startCamera()
			} else
			{
				Toast.makeText(
					this ,
					"Permissions not granted by the user." ,
					Toast.LENGTH_SHORT
				              ).show()
				finish()
			}
		}
	}
	
	
	
	
}

open class ThreadExecutor(private val handler : Handler) : Executor
{
	override fun execute(command : Runnable)
	{
		handler.post(command)
	}
	
}

private class LuminosityAnalyzer(private val listener : LumaListener) : ImageAnalysis.Analyzer
{
	
	private fun ByteBuffer.toByteArray() : ByteArray
	{
		rewind()
		val data = ByteArray(remaining())
		get(data)
		return data
	}
	
	override fun analyze(image : ImageProxy)
	{
		val buffer = image.planes[0].buffer
		val data = buffer.toByteArray()
		val pixels = data.map { it.toInt() and 0xFF }
		val luma = pixels.average()
		
		listener(luma)
		image.close()
	}
	
}