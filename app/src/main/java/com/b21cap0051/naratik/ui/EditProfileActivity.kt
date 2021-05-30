package com.b21cap0051.naratik.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.b21cap0051.naratik.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity()
{
	private lateinit var binding : ActivityEditProfileBinding
	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivityEditProfileBinding.inflate(layoutInflater)
		setContentView(binding.root)
	}
}