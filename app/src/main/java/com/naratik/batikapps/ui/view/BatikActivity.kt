package com.naratik.batikapps.ui.view

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.naratik.batikapps.R
import com.naratik.batikapps.databinding.ActivityBatikBinding
import com.naratik.batikapps.ui.adapter.BatikListAdapter
import com.naratik.batikapps.ui.viewmodel.BatikMainView
import com.naratik.batikapps.util.ResultStateData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BatikActivity : AppCompatActivity()
{
	
	private lateinit var batik : BatikListAdapter
	private lateinit var binding : ActivityBatikBinding
	private val mainView : BatikMainView by viewModels()
	
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
		
		
		binding.rvAllBatik.layoutManager =
			StaggeredGridLayoutManager(row , StaggeredGridLayoutManager.VERTICAL)
		
		batik = BatikListAdapter(this , mainView)
		
		
		mainView.getAllbatik().observe(this , {
			when (it)
			{
				is ResultStateData.Success ->
				{
					binding.laiLoading.visibility = View.GONE
					binding.rvAllBatik.adapter = batik
					batik.setList(it.data)
				}
				is ResultStateData.Failure ->
				{
					Snackbar.make(binding.root , it.message , Snackbar.LENGTH_SHORT)
						.show()
				}
				is ResultStateData.Loading ->
				{
					binding.laiLoading.visibility = View.VISIBLE
				}
				else                       -> {
				
				}
			}
		})
		
		
	}
	
	
	private fun loadActionBar()
	{
		val btnBack : Button = findViewById(R.id.btnBack)
		val title : TextView = findViewById(R.id.tvTitle)
		title.text = resources.getString(R.string.batik)
		btnBack.setOnClickListener() {
			super.onBackPressed()
		}
	}
	
}