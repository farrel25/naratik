package com.naratik.batikapps.ui.view.favourite

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.naratik.batikapps.R
import com.naratik.batikapps.databinding.ActivityFavouriteBinding
import com.naratik.batikapps.ui.adapter.SectionPagerAdapter
import com.naratik.batikapps.ui.viewmodel.NetworkCheckView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavouriteActivity : AppCompatActivity()
{
	
	private lateinit var binding : ActivityFavouriteBinding
	
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
		binding = ActivityFavouriteBinding.inflate(layoutInflater)
		setContentView(binding.root)
		connectCheck.isNetworkAvaible.observe(this , {
			if (it.hasBeenHandled)
			{
			
			} else
			{
				it.getContentIfNotHandled().let {
					when (it)
					{
						true  ->
						{
							Snackbar.make(
								binding.root ,
								"Internet is Connected!" ,
								Snackbar.LENGTH_SHORT
							             ).show()
						}
						
						false ->
						{
							Snackbar.make(
								binding.root ,
								"Internet isn't Connected!" ,
								Snackbar.LENGTH_SHORT
							             ).show()
						}
						
						else  ->
						{
						
						}
						
					}
				}
			}
		})
		loadActionBar()
		
		val sectionsPagerAdapter = SectionPagerAdapter(this , supportFragmentManager)
		binding.viewPager.adapter = sectionsPagerAdapter
		binding.tabs.setupWithViewPager(binding.viewPager)
		supportActionBar?.elevation = 0f
		
		
	}
	
	private fun loadActionBar()
	{
		val title : TextView = findViewById(R.id.tvTitle)
		title.text = resources.getString(R.string.favorite)
		val btnBack : Button = findViewById(R.id.btnBack)
		btnBack.setOnClickListener() {
			super.onBackPressed()
		}
	}
}