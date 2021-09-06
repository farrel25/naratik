package com.naratik.batikapps.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.naratik.batikapps.databinding.ActivitySettingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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