package com.b21cap0051.naratik.util

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Camera
import java.lang.Exception

class CameraDevice(private val ctx : Context)
{
	companion object{
		private var Instance : CameraDevice? = null
		fun GetInstance(ctx : Context):CameraDevice =
			Instance?: synchronized(this){
				Instance?:CameraDevice(ctx).apply {
					Instance = this
				}
			}
	}
	
	
	
	fun CheckCameraDevices():Boolean{
		if(ctx.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)){
			return true
		} else
		{
			return false
		}
	}
	
	
}