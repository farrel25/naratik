package com.b21cap0051.naratik.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.databinding.ActivityHomeBinding
import com.b21cap0051.naratik.databinding.CustomActionBarLogoFavoriteBinding
import com.b21cap0051.naratik.ui.cameraui.CameraActivity
import com.b21cap0051.naratik.ui.favourite.FavouriteActivity


class HomeActivity : AppCompatActivity()
{
	private lateinit var binding : ActivityHomeBinding
	private lateinit var bindingActionBar : CustomActionBarLogoFavoriteBinding
	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivityHomeBinding.inflate(layoutInflater)
		bindingActionBar = CustomActionBarLogoFavoriteBinding.inflate(layoutInflater)
		setContentView(binding.root)

		binding.bottomBarMenu.background = null
		binding.bottomBarMenu.menu.getItem(1).isEnabled = false

		val navHostFragment = supportFragmentManager.findFragmentById(R.id.HomeFragmentActivity) as NavHostFragment
		val navControl = navHostFragment.navController

		binding.bottomBarMenu.setupWithNavController(navControl)


		binding.actionBarItem.btnFavorite.setOnClickListener {
			val intent = Intent(this@HomeActivity , FavouriteActivity::class.java)
			startActivity(intent)
			finish()
		}

		binding.fabCamera.setOnClickListener {
			val intent = Intent(this@HomeActivity , CameraActivity::class.java)
			startActivity(intent)
		}
	}
}