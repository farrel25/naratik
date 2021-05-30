package com.b21cap0051.naratik.ui

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.adapter.ShimmerBatikListAdapter
import com.b21cap0051.naratik.databinding.ActivityBatikBinding
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.util.DataDummy
import com.b21cap0051.naratik.util.ItemBatikCallBack

class BatikActivity : AppCompatActivity() , ItemBatikCallBack
{
	
	private lateinit var batikAdapter : ShimmerBatikListAdapter
	private lateinit var binding : ActivityBatikBinding
	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivityBatikBinding.inflate(layoutInflater)
		setContentView(binding.root)
		
		loadActionBar()
		
		var row = 2
		if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
		{
			row = 4
		}
		
		batikAdapter = ShimmerBatikListAdapter(this)
		binding.rvAllBatik.layoutManager =
			StaggeredGridLayoutManager(row , StaggeredGridLayoutManager.VERTICAL)
		binding.rvAllBatik.adapter = batikAdapter
		val listBatik = DataDummy.generateDummyBatik()
		// batikAdapter.setList(listBatik)
	}
	
	private fun loadActionBar()
	{
		val btnBack : Button = findViewById(R.id.btnBack)
		btnBack.setOnClickListener() {
			super.onBackPressed()
		}
	}
	
	override fun itemBatikClick(model : BatikEntity)
	{
	}
}