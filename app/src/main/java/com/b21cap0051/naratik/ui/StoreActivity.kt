package com.b21cap0051.naratik.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.adapter.StoreAllAdapter
import com.b21cap0051.naratik.databinding.ActivityStoreBinding
import com.b21cap0051.naratik.dataresource.local.model.StoreEntity
import com.b21cap0051.naratik.mainview.ShopMainView
import com.b21cap0051.naratik.mainview.ViewFactoryModel
import com.b21cap0051.naratik.util.ItemStoreCallback
import com.b21cap0051.naratik.util.vo.Status

class StoreActivity : AppCompatActivity() , ItemStoreCallback
{
	private lateinit var binding : ActivityStoreBinding
	private lateinit var adapterStore : StoreAllAdapter
	private lateinit var viewModel : ShopMainView
	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivityStoreBinding.inflate(layoutInflater)
		setContentView(binding.root)
		
		val factory = ViewFactoryModel.GetInstance(this)
		viewModel = ViewModelProvider(this , factory)[ShopMainView::class.java]
		
		
		loadActionBar()
		
		adapterStore = StoreAllAdapter(this)
		
		var row = 1
		if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
		{
			row = 2
		}
		
		binding.rvAllStore.layoutManager = GridLayoutManager(this , row)
		binding.rvAllStore.adapter = adapterStore
		
		viewModel.getAllShop().observe(this , { response ->
			when (response.Status)
			{
				Status.SUCCESS ->
				{
					adapterStore.submitList(response.Data)
					binding.laiLoading.visibility = View.GONE
					
				}
				Status.LOADING ->
				{
					binding.laiLoading.visibility = View.VISIBLE
				}
				
				Status.ERROR   ->
				{
				
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
	
	override fun itemStoreClick(model : StoreEntity)
	{
	
	}
}