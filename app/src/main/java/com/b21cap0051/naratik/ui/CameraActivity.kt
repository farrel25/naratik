package com.b21cap0051.naratik.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.util.Size
import android.view.Surface.ROTATION_0
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.core.ImageCapture.OutputFileOptions.Builder
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.databinding.ActivityCameraBinding
import java.io.File
import java.nio.ByteBuffer
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

typealias LumaListener = (luma : Double) -> Unit

class CameraActivity : AppCompatActivity()
{
	private lateinit var binding  : ActivityCameraBinding
	private lateinit var CameraExecutors : ExecutorService
	private lateinit var outputDirectory : File
	private var CapturePhoto : ImageCapture? = null
	
	companion object
	{
		private const val TAG = "CameraXBasic"
		private const val DAY_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
		private const val FILENAME_FORMAT = "batik_xx"
		private const val REQUEST_CODE_PERMISSION = 10
		private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
	}
	
	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivityCameraBinding.inflate(layoutInflater)
		setContentView(binding.root)
		
		
		
		if (CameraGranted())
		{
			startCamera()
		} else
		{
			ActivityCompat.requestPermissions(
				this , REQUIRED_PERMISSIONS , REQUEST_CODE_PERMISSION
			                                 )
		}
		
		CameraExecutors = Executors.newSingleThreadExecutor()
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
	
	private fun CameraGranted() = REQUIRED_PERMISSIONS.all {
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
					.build()
					.also {
						it.setSurfaceProvider(binding.cameraFinder.surfaceProvider)
					}
				
				CapturePhoto = ImageCapture.Builder()
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
						CapturePhoto ,
						imageAnalyzer,
					                              )
					preview?.setSurfaceProvider(binding.cameraFinder.surfaceProvider)
					val cameraContorl = preview?.camera?.cameraControl
					val factory = SurfaceOrientedMeteringPointFactory(1f,1f)
					val point = factory.createPoint(.5f,.5f)
					val action = FocusMeteringAction.Builder(point,FocusMeteringAction.FLAG_AF)
						.setAutoCancelDuration(2,TimeUnit.SECONDS)
						.build()
					cameraContorl!!.startFocusAndMetering(action)
				} catch (e : Exception)
				{
					Log.e(TAG , "Use Case Binding Failed" , e)
				}
				
			} , ContextCompat.getMainExecutor(this)
		                                )
	}
	
	private fun takePhoto()
	{
		val imageCapture = CapturePhoto ?: return
		
		val photoFile = File(
			outputDirectory ,
			FILENAME_FORMAT + "_" + SimpleDateFormat(
				DAY_FORMAT ,
				Locale.US
			                                        ).format(System.currentTimeMillis()) + ".jpg"
		                    )
		
		val outputOptions = Builder(photoFile).build()
		imageCapture.takePicture(
			outputOptions ,
			ContextCompat.getMainExecutor(this) ,
			object : ImageCapture.OnImageSavedCallback
			{
				override fun onImageSaved(outputFileResults : ImageCapture.OutputFileResults)
				{
					val SavedURI = Uri.fromFile(photoFile)
					Toast.makeText(
						baseContext ,
						"Photo Captured and Saved on $SavedURI" ,
						Toast.LENGTH_SHORT
					              ).show()
				}
				
				override fun onError(e : ImageCaptureException)
				{
					Log.e(TAG , "Photo Captured fail : ${e.message} " , e)
				}
				
			})
		
	}
	
	override fun onDestroy()
	{
		super.onDestroy()
		CameraExecutors.shutdown()
	}
	
	override fun onBackPressed()
	{
		super.onBackPressed()
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
			if (CameraGranted())
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
	
	inline fun View.afterMeasured(crossinline block : () -> Unit)
	{
		viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener
		{
			override fun onGlobalLayout()
			{
				if (measuredWidth > 0 && measuredHeight > 0)
				{
					viewTreeObserver.removeOnGlobalLayoutListener(this)
					block()
				}
			}
			
		})
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