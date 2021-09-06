package com.naratik.batikapps.ui.view

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.naratik.batikapps.R
import com.naratik.batikapps.databinding.ActivityStoreBinding
import com.naratik.batikapps.ui.adapter.StoreAllAdapter
import com.naratik.batikapps.ui.viewmodel.ShopMainView
import com.naratik.batikapps.util.ResultStateData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreActivity : AppCompatActivity()
{
	private lateinit var binding : ActivityStoreBinding
	private lateinit var adapterStore : StoreAllAdapter
	private val viewModel : ShopMainView by viewModels()
	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivityStoreBinding.inflate(layoutInflater)
		setContentView(binding.root)
		
		loadActionBar()
		
		adapterStore = StoreAllAdapter()
		
		var row = 1
		if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
		{
			row = 2
		}
		
		binding.rvAllStore.layoutManager = GridLayoutManager(this , row)
		binding.rvAllStore.adapter = adapterStore
		
		viewModel.getAllShop().observe(this , {
			when(it){
				is ResultStateData.Success -> {
					adapterStore.setList(it.data)
					binding.rvAllStore.visibility = View.VISIBLE
					binding.laiLoading.visibility = View.GONE
				}
				is ResultStateData.Loading -> {
					binding.rvAllStore.visibility = View.GONE
					binding.laiLoading.visibility = View.VISIBLE
				}
				
				is ResultStateData.Failure ->{
					binding.rvAllStore.visibility = View.GONE
					Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
					binding.laiLoading.visibility = View.GONE
				}
			}
		})
		
		
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
	
}