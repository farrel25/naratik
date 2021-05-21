package com.b21cap0051.naratik.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.databinding.ActivityCameraBinding
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraActivity : AppCompatActivity()
{
	private lateinit var binding : ActivityCameraBinding
	private lateinit var CameraExecutors : ExecutorService
	private lateinit var outputDirectory : File
	private var ImageCapture : ImageCapture? = null
	
	companion object
	{
		private val TAG = "CameraXBasic"
		private val FILENAME_FORMAT = "batik_xx"
		private val REQUEST_CODE_PERMISSION = 10
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
		binding.captureImage.setOnClickListener {
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
	
	
	private fun startCamera()
	{
		val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
		cameraProviderFuture.addListener(
			Runnable {
				val cameraProvider : ProcessCameraProvider = cameraProviderFuture.get()
				
				val preview = Preview.Builder()
					.build()
					.also {
						it.setSurfaceProvider(binding.cameraFinder.surfaceProvider)
					}
				val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
				
				try
				{
					cameraProvider.unbindAll()
					cameraProvider.bindToLifecycle(this , cameraSelector , preview)
				} catch (e : Exception)
				{
					Log.e(TAG , "Use Case Binding Failed" , e)
				}
				
			} , ContextCompat.getMainExecutor(this)
		                                )
	}
	
	private fun takePhoto()
	{
	}
	
	override fun onDestroy()
	{
		super.onDestroy()
		CameraExecutors.shutdown()
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
	
	
}