package com.b21cap0051.naratik.util

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class CameraDevice(private val activity : Activity)
{
	companion object
	{
		@Volatile
		private var Instance : CameraDevice? = null
		
		
		fun GetInstance(ctx : Activity) : CameraDevice =
			Instance ?: synchronized(this) {
				Instance ?: CameraDevice(ctx).apply {
					Instance = this
				}
			}
	}
	
	
	private val TAG = "CameraXBasic"
	private val FILENAME_FORMAT = "batik_xx"
	private val REQUEST_CODE_PERMISSION = 10
	private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
	private var ThreadExecute : ExecutedApp? = null
	init
	{
		 this.ThreadExecute = ExecutedApp()
	}
	
	
	private fun CheckThePermissionCamera()
	{
		if (CameraGranted())
		{
			startCamera()
		} else
		{
			ActivityCompat.requestPermissions(
				activity , REQUIRED_PERMISSIONS , REQUEST_CODE_PERMISSION
			                                 )
		}
	}
	
	
	private fun startCamera(){
	
	}
	
	private fun takePhoto(){
	
	}
	
	private fun ShutdownCamera(){
		ThreadExecute?.CameraThread()?.shutdown()
	}
	
	private fun CameraGranted() = REQUIRED_PERMISSIONS.all {
		ContextCompat.checkSelfPermission(activity.baseContext , it) == PackageManager.PERMISSION_GRANTED
	}
	
	
	
}