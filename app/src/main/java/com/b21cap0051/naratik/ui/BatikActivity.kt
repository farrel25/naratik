package com.b21cap0051.naratik.ui

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.adapter.BatikPagedListAdapter
import com.b21cap0051.naratik.adapter.ShimmerBatikListAdapter
import com.b21cap0051.naratik.databinding.ActivityBatikBinding
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.mainview.BatikMainView
import com.b21cap0051.naratik.mainview.ViewFactoryModel
import com.b21cap0051.naratik.util.DataDummy
import com.b21cap0051.naratik.util.ItemBatikCallBack
import com.b21cap0051.naratik.util.naratikDependencys
import com.b21cap0051.naratik.util.vo.Status
import com.google.android.material.snackbar.Snackbar

class BatikActivity : AppCompatActivity() , ItemBatikCallBack
{
	
	private lateinit var batikAdapter : ShimmerBatikListAdapter
	private lateinit var batikPaged : BatikPagedListAdapter
	private lateinit var binding : ActivityBatikBinding
	private lateinit var mainView : BatikMainView
	
	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivityBatikBinding.inflate(layoutInflater)
		setContentView(binding.root)
		val factory = ViewFactoryModel(naratikDependencys.injectRepository(this))
		mainView = ViewModelProvider(this , factory)[BatikMainView::class.java]
		
		
		
		loadActionBar()
		
		var row = 2
		if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
		{
			row = 4
		}
		
		binding.rvAllBatik.layoutManager =
			StaggeredGridLayoutManager(row , StaggeredGridLayoutManager.VERTICAL)
		
		
		batikPaged = BatikPagedListAdapter(this)
		
		batikAdapter = ShimmerBatikListAdapter(this)
		
		
		val listBatik = DataDummy.generateDummyBatik()
//
		
		mainView.getAllbatik().observe(this , { response ->
			when (response.Status)
			{
				Status.SUCCESS ->
				{
					binding.rvAllBatik.adapter = batikPaged
					batikPaged.submitList(response.Data)
				}
				Status.ERROR   ->
				{
					Snackbar.make(binding.root , "${response.message}" , Snackbar.LENGTH_SHORT)
						.show()
				}
				Status.LOADING ->
				{
					binding.rvAllBatik.adapter = batikAdapter
					batikAdapter.setList(listBatik)
				}
			}
		})
		
		
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