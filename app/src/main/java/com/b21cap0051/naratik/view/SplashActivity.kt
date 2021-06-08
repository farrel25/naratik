package com.b21cap0051.naratik.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.b21cap0051.naratik.databinding.ActivitySplashBinding
import com.b21cap0051.naratik.view.onboarding.OnBoardingActivity


class SplashActivity : AppCompatActivity()
{
	private lateinit var binding : ActivitySplashBinding
	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivitySplashBinding.inflate(layoutInflater)
		setContentView(binding.root)
		
		Handler(mainLooper).postDelayed({
			val intent = Intent(this , OnBoardingActivity::class.java)
			startActivity(intent)
			finish()
		} , 1000)
		
	}
}