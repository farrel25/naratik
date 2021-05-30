package com.b21cap0051.naratik.ui

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.adapter.BatikMiniListAdapter
import com.b21cap0051.naratik.databinding.ActivityDetailBatikBinding
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.util.DataDummy
import com.b21cap0051.naratik.util.ItemBatikCallBack

class DetailBatikActivity : AppCompatActivity() , ItemBatikCallBack
{
	private lateinit var binding : ActivityDetailBatikBinding
	private lateinit var batikAdapter : BatikMiniListAdapter
	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivityDetailBatikBinding.inflate(layoutInflater)
		setContentView(binding.root)
		
		loadActionBar()
		
		batikAdapter = BatikMiniListAdapter(this)
		binding.rvVmBatik.layoutManager =
			LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false)
		binding.rvVmBatik.adapter = batikAdapter
		val listBatik = DataDummy.generateDummyBatik()
		//batikAdapter.setList(listBatik)
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
		TODO("Not yet implemented")
	}
}