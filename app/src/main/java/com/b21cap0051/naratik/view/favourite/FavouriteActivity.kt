package com.b21cap0051.naratik.view.favourite

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.b21cap0051.naratik.R
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