package com.b21cap0051.naratik.ui.favourite

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.adapter.FavouritAdapterPager
import com.b21cap0051.naratik.databinding.ActivityFavouriteBinding
import com.google.android.material.tabs.TabLayoutMediator

class FavouriteActivity : AppCompatActivity()
{
	@StringRes
	val Tab = arrayOf(
		R.string.batik ,
		R.string.article
	                 )
	
	private lateinit var binding : ActivityFavouriteBinding
	private lateinit var adapter : FavouritAdapterPager
	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivityFavouriteBinding.inflate(layoutInflater)
		setContentView(binding.root)
		
		adapter = FavouritAdapterPager(this)
		binding.Pager2List.adapter = adapter
		val stat = 0
		TabLayoutMediator(binding.tabItem , binding.Pager2List) { tabtext , position ->
			tabtext.view.setBackgroundResource(R.drawable.bg_yellow_rounded)
			tabtext.text = Tab[position].toString()
		}.attach()
		
		
	}
}