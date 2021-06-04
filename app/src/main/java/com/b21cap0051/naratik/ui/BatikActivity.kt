package com.b21cap0051.naratik.ui

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.adapter.BatikPagedListAdapter
import com.b21cap0051.naratik.databinding.ActivityBatikBinding
import com.b21cap0051.naratik.databinding.ItemRowBatikBinding
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.mainview.BatikMainView
import com.b21cap0051.naratik.mainview.ViewFactoryModel
import com.b21cap0051.naratik.util.ItemBatikCallBack
import com.b21cap0051.naratik.util.naratikDependencys
import com.b21cap0051.naratik.util.vo.Status
import com.google.android.material.snackbar.Snackbar

class BatikActivity : AppCompatActivity() , ItemBatikCallBack
{
	
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
		
		
		mainView.getAllbatik().observe(this , { response ->
			when (response.Status)
			{
				Status.SUCCESS ->
				{
					binding.laiLoading.visibility = View.GONE
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
					binding.laiLoading.visibility = View.VISIBLE
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
	
	override fun itemBatikClick(model : BatikEntity)
	{
	}
	
	override fun AddFavour(v : ItemRowBatikBinding , model : BatikEntity)
	{
		if(CheckIsFavor(model)){
			val modelbaru = BatikEntity(
				model.batik_id,
				model.name_batik,
				model.makna_batik,
				model.Image,
				model.daerah_batik,
				0
			                           )
			mainView.addFavor(modelbaru)
			v.btnItemFavBatik.setIconResource(R.drawable.ic_love_outlined)
		}else{
			val modelbaru = BatikEntity(
				model.batik_id,
				model.name_batik,
				model.makna_batik,
				model.Image,
				model.daerah_batik,
				1
			                           )
			mainView.addFavor(modelbaru)
			v.btnItemFavBatik.setIconResource(R.drawable.ic_love_filled)
		}
	}
	
	override fun CheckIsFavor(model : BatikEntity) : Boolean
	{
		var stat = false
		mainView.checkFavorite().observe(this,{
			for (i in 0 until it.size){
				if(model.batik_id == it[i].batik_id){
					stat = true
				}
			}
		})
		return stat
	}
}