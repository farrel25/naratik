package com.naratik.batikapps.ui.view.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import com.naratik.batikapps.R
import com.naratik.batikapps.databinding.ActivityHomeBinding
import com.naratik.batikapps.databinding.CustomActionBarLogoFavoriteBinding
import com.naratik.batikapps.ui.view.cameraui.CameraActivity
import com.naratik.batikapps.ui.view.favourite.FavouriteActivity
import com.naratik.batikapps.ui.viewmodel.NetworkCheckView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity()
{
	private lateinit var binding : ActivityHomeBinding
	private lateinit var bindingActionBar : CustomActionBarLogoFavoriteBinding
	@Inject
	lateinit var connectCheck : NetworkCheckView
	
	override fun onStart()
	{
		super.onStart()
		connectCheck.registerConnectionObserver(this)
	}
	
	override fun onDestroy()
	{
		super.onDestroy()
		connectCheck.unregisterConnectionObserver(this)
	}
	
	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivityHomeBinding.inflate(layoutInflater)
		bindingActionBar = CustomActionBarLogoFavoriteBinding.inflate(layoutInflater)
		setContentView(binding.root)
		connectCheck.isNetworkAvaible.observe(this,{
			if(it.hasBeenHandled){
			
			}else{
				it.getContentIfNotHandled().let {
					when(it){
						true ->{
							Snackbar.make(binding.bottomBar,"Internet is Connected!",Snackbar.LENGTH_SHORT).show()
						}
						
						false -> {
							Snackbar.make(binding.bottomBar,"Internet isn't Connected!",Snackbar.LENGTH_SHORT).show()
						}
						
						else -> {
						
						}
						
					}
			}
			
			}
		
		})
		binding.bottomBarMenu.background = null
		binding.bottomBarMenu.menu.getItem(1).isEnabled = false
		
		val navHostFragment =
			supportFragmentManager.findFragmentById(R.id.HomeFragmentActivity) as NavHostFragment
		val navControl = navHostFragment.navController
		
		binding.bottomBarMenu.setupWithNavController(navControl)
		
		
		binding.actionBarItem.btnFavorite.setOnClickListener {
			val intent = Intent(this@HomeActivity , FavouriteActivity::class.java)
			startActivity(intent)
		}
		
		binding.fabCamera.setOnClickListener {
			val intent = Intent(this@HomeActivity , CameraActivity::class.java)
			startActivity(intent)
		}
	}
}