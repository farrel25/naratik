package com.naratik.batikapps.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.naratik.batikapps.databinding.ActivitySplashBinding
import com.naratik.batikapps.ui.view.home.HomeActivity
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity()
{
	private lateinit var binding : ActivitySplashBinding
	override fun onStart()
	{
		super.onStart()
		window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
				or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
				or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
				or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
				or View.SYSTEM_UI_FLAG_FULLSCREEN)
	}
	
	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivitySplashBinding.inflate(layoutInflater)
		setContentView(binding.root)
		
		
		CoroutineScope(Dispatchers.Default).launch {
			delay(2000)
			withContext(Dispatchers.Main) {
				val intent = Intent(this@SplashActivity , HomeActivity::class.java)
				startActivity(intent)
				finish()
			}
		}
	}
	
}