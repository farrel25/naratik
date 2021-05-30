package com.b21cap0051.naratik.ui.cameraui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.b21cap0051.naratik.databinding.ActivityCameraBinding


class CameraActivity : AppCompatActivity()
{
	private lateinit var binding : ActivityCameraBinding
	
	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivityCameraBinding.inflate(layoutInflater)
		setContentView(binding.root)
	}
}