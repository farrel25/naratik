package com.b21cap0051.naratik.ui.favourite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.b21cap0051.naratik.adapter.SectionPagerAdapter
import com.b21cap0051.naratik.databinding.ActivityFavouriteBinding

class FavouriteActivity : AppCompatActivity()
{
	
	private lateinit var binding : ActivityFavouriteBinding
	
	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivityFavouriteBinding.inflate(layoutInflater)
		setContentView(binding.root)
		
		val sectionsPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
		binding.viewPager.adapter = sectionsPagerAdapter
		binding.tabs.setupWithViewPager(binding.viewPager)
		supportActionBar?.elevation = 0f
		
		
	}
}