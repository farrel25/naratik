package com.naratik.batikapps.ui.view.cameraui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.util.Size
import android.view.Surface
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import cn.pedant.SweetAlert.SweetAlertDialog
import com.google.android.material.snackbar.Snackbar
import com.naratik.batikapps.R
import com.naratik.batikapps.core.remote.model.ImageUploadModel
import com.naratik.batikapps.databinding.ActivityCameraBinding
import com.naratik.batikapps.ui.view.cameraui.UploadProcessFragment.Companion.KEY_UPLOAD
import com.naratik.batikapps.ui.view.home.HomeActivity
import com.naratik.batikapps.ui.viewmodel.NetworkCheckView
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.nio.ByteBuffer
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.random.Random

typealias LumaListener = (luma : Double) -> Unit

@AndroidEntryPoint
class CameraActivity : AppCompatActivity()
{
	private lateinit var binding : ActivityCameraBinding
	private lateinit var cameraExecutors : ExecutorService
	private lateinit var outputDirectory : File
	private var capturePhoto : ImageCapture? = null
	private lateinit var dialog : SweetAlertDialog
	
	@Inject
	lateinit var connectCheck : NetworkCheckView
	
	override fun onStart()
	{
		super.onStart()
		if (cameraGranted())
		{
			startCamera()
		} else
		{
			ActivityCompat.requestPermissions(
				this ,
				REQUIRED_PERMISSIONS ,
				REQUEST_CODE_PERMISSION
			                                 )
		}
		
		connectCheck.registerConnectionObserver(this)
	}
	
	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivityCameraBinding.inflate(layoutInflater)
		setContentView(binding.root)
		loadActionBar()
		connectCheck.isNetworkAvaible.observe(this,{
			if(it.hasBeenHandled){
			
			}else{
				it.getContentIfNotHandled().let {
					when(it){
						true ->{
							Snackbar.make(binding.root,"Internet is Connected!",Snackbar.LENGTH_SHORT).show()
						}
						
						false -> {
							Snackbar.make(binding.root,"Internet isn't Connected!",Snackbar.LENGTH_SHORT).show()
						}
						
						else -> {
						
						}
						
					}
				}
				
			}
			
		})
		cameraExecutors = Executors.newSingleThreadExecutor()
		outputDirectory = getOutputDirectory()
		binding.btnCapture.setOnClickListener {
			takePhoto()
		}
		
		binding.btnCancel.setOnClickListener {
			startActivity(Intent(this,HomeActivity::class.java))
			finish()
		}
		
	}
	
	private fun loadActionBar()
	{
		
		binding.btnCancel.setOnClickListener() {
			super.onBackPressed()
		}
	}
	
	companion object
	{
		private val TAG = CameraActivity::class.java.simpleName
		private const val REQUEST_CODE_PERMISSION = 10
		private val REQUIRED_PERMISSIONS =
			arrayOf(Manifest.permission.CAMERA)
	}
	
	
	private fun getOutputDirectory() : File
	{
		val mediaDir = this.externalMediaDirs?.firstOrNull()?.let {
			File(
				it ,
				resources.getString(R.string.app_name)
			    ).apply { mkdirs() }
		}
		return if (mediaDir != null && mediaDir.exists()) mediaDir else this.filesDir
	}
	
	private fun cameraGranted() = REQUIRED_PERMISSIONS.all {
		ContextCompat.checkSelfPermission(
			this.baseContext ,
			it
		                                 ) == PackageManager.PERMISSION_GRANTED
	}
	
	
	@SuppressLint("RestrictedApi")
	private fun startCamera()
	{
		val cameraProviderFuture =
			ProcessCameraProvider.getInstance(
				this
			                                 )
		cameraProviderFuture.addListener(
			{
				val cameraProvider : ProcessCameraProvider = cameraProviderFuture.get()
				
				val preview = Preview.Builder()
					.setTargetRotation(Surface.ROTATION_0)
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
							ThreadExecutor(Handler(analyzer.looper)) ,
							LuminosityAnalyzer {  }
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
						imageAnalyzer ,
					                              )
					preview.setSurfaceProvider(binding.cameraFinder.surfaceProvider)
					val cameraControl = preview.camera?.cameraControl
					val factory = SurfaceOrientedMeteringPointFactory(1f , 1f)
					val point = factory.createPoint(.5f , .5f)
					val action = FocusMeteringAction.Builder(point , FocusMeteringAction.FLAG_AF)
						.setAutoCancelDuration(2 , TimeUnit.SECONDS)
						.build()
					cameraControl!!.startFocusAndMetering(action)
				} catch (e : Exception)
				{
					Log.e(TAG , "Use Case Binding Failed" , e)
				}
				
			} , ContextCompat.getMainExecutor(this)
		                                )
	}
	
	private fun getRandomString(length : Int) : String
	{
		val allowedChars = ('A' .. 'Z') + ('a' .. 'z') + ('0' .. '9')
		return (1 .. length)
			.map { allowedChars.random() }
			.joinToString("")
	}
	
	
	private fun takePhoto()
	{
		val imageCapture = capturePhoto ?: return
		val data = Random.nextInt(100)
		val filename = getRandomString(5)
		val photoFile = File(
			outputDirectory , filename +
					data.toString() + ".jpg"
		                    )
		
		val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
		imageCapture.takePicture(
			outputOptions ,
			ContextCompat.getMainExecutor(this) ,
			object : ImageCapture.OnImageSavedCallback
			{
				@RequiresApi(Build.VERSION_CODES.M)
				override fun onImageSaved(outputFileResults : ImageCapture.OutputFileResults)
				{
					Toast.makeText(
						this@CameraActivity ,
						resources.getString(R.string.savephotonotif) ,
						Toast.LENGTH_SHORT
					              ).show()
					uploadNotification(Uri.fromFile(photoFile))
				}
				
				override fun onError(e : ImageCaptureException)
				{
					Toast.makeText(
						this@CameraActivity ,
						resources.getString(R.string.savephotonotif) ,
						Toast.LENGTH_SHORT
					              ).show()
					Log.e(TAG , "Photo Captured fail : ${e.message} " , e)
				}
				
			})
		
		
	}
	
	
	@RequiresApi(Build.VERSION_CODES.M)
	private fun uploadNotification(uri : Uri)
	{
		dialog = SweetAlertDialog(this , SweetAlertDialog.WARNING_TYPE)
		dialog.titleText = resources.getString(R.string.noticeupload)
		dialog.contentText = resources.getString(R.string.noticeuploadcontext)
		dialog.confirmText = resources.getString(R.string.noticeuploadconfirmtext)
		dialog.setCancelable(false)
		dialog.setConfirmClickListener {
			uploadProcess(ImageUploadModel(uri))
			it.dismissWithAnimation()
		}
		dialog.setCancelButton(
			resources.getString(R.string.noticeuploadconfirmcancel)
		                      ) { sDialog -> sDialog.dismissWithAnimation() }
		dialog.show()
		dialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).backgroundTintList =
			this.getColorStateList(R.color.cream)
		
		dialog.getButton(SweetAlertDialog.BUTTON_CANCEL)
			.backgroundTintList = this.getColorStateList(R.color.red)
		
		
		dialog.confirmButtonTextColor = this.getColor(R.color.black)
		dialog.cancelButtonTextColor = this.getColor(R.color.white)
	}
	
	
	override fun onDestroy()
	{
		super.onDestroy()
		cameraExecutors.shutdown()
		finish()
		connectCheck.unregisterConnectionObserver(this)
	}
	
	private fun uploadProcess(modelUpload : ImageUploadModel)
	{
		val mbundle = Bundle()
		mbundle.putParcelable(KEY_UPLOAD , modelUpload)
		val uploadFragment = UploadProcessFragment()
		uploadFragment.arguments = mbundle
		uploadFragment.show(supportFragmentManager , UploadProcessFragment::class.java.simpleName)
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
	
}