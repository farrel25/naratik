package com.b21cap0051.naratik.ui

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.adapter.StoreAllAdapter
import com.b21cap0051.naratik.databinding.ActivityStoreBinding
import com.b21cap0051.naratik.dataresource.local.model.StoreEntity
import com.b21cap0051.naratik.util.DataDummy
import com.b21cap0051.naratik.util.ItemStoreCallback

class StoreActivity : AppCompatActivity(),ItemStoreCallback
{
	private lateinit var binding : ActivityStoreBinding
	private lateinit var adapterStore : StoreAllAdapter
	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivityStoreBinding.inflate(layoutInflater)
		setContentView(binding.root)
		
		loadActionBar()
		
		adapterStore = StoreAllAdapter(this)
		
		var row = 1
		if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
		{
			row = 2
		}
		val listStore = DataDummy.generateDummyStore()
		adapterStore.setList(listStore)
		binding.rvAllStore.layoutManager = GridLayoutManager(this , row)
		binding.rvAllStore.adapter = adapterStore
		
		binding.laiLoading.visibility = View.GONE
		
	}
	
	private fun loadActionBar()
	{
		val btnBack : Button = findViewById(R.id.btnBack)
		val title : TextView = findViewById(R.id.tvTitle)
		title.text = resources.getString(R.string.store)
		btnBack.setOnClickListener() {
			super.onBackPressed()
		}
	}
	
	override fun itemStoreClick(model : StoreEntity)
	{
	
	}
}