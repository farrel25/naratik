package com.b21cap0051.naratik.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.b21cap0051.naratik.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity()
{
	private lateinit var binding : ActivitySettingBinding
	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivitySettingBinding.inflate(layoutInflater)
		setContentView(binding.root)
	}
}