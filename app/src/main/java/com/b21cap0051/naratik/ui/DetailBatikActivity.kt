package com.b21cap0051.naratik.ui

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.adapter.BatikMiniListAdapter
import com.b21cap0051.naratik.databinding.ActivityDetailBatikBinding
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.util.DataDummy
import com.b21cap0051.naratik.util.ItemBatikCallBack
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailBatikActivity : AppCompatActivity() , ItemBatikCallBack
{
	private lateinit var binding : ActivityDetailBatikBinding
	private lateinit var mActionBarToolbar : Toolbar
	private lateinit var batikMiniAdapter : BatikMiniListAdapter
	
	companion object
	{
		const val EXTRA_BATIK = "extra_batik"
	}
	
	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivityDetailBatikBinding.inflate(layoutInflater)
		setContentView(binding.root)
		
		mActionBarToolbar = findViewById(R.id.custom_action_bar_back)
		setSupportActionBar(mActionBarToolbar)
		
		val batik = intent.getParcelableExtra<BatikEntity>(EXTRA_BATIK) as BatikEntity
		
		loadActionBar()
		
		batikMiniAdapter = BatikMiniListAdapter(this)
		binding.rvVmBatik.layoutManager =
			LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false)
		binding.rvVmBatik.adapter = batikMiniAdapter
		
		
		supportActionBar?.title = batik.name_batik
		
		binding.tvItemLocationBatik.text = batik.daerah_batik
		binding.tvMeaning.text = batik.makna_batik
		Glide.with(this)
			.load(batik.Image)
			.apply(RequestOptions().override(500 , 500))
			.apply(
				RequestOptions.placeholderOf(R.drawable.ic_loading)
					.error(R.drawable.ic_error)
			      )
			.into(binding.ivBatik)
		
		
		val listBatik = DataDummy.generateDummyBatik()
		batikMiniAdapter.setList(listBatik)
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