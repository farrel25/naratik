package com.b21cap0051.naratik.ui

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.adapter.ArticleMiniListAdapter
import com.b21cap0051.naratik.adapter.BatikMiniListAdapter
import com.b21cap0051.naratik.databinding.ActivitySearchBinding
import com.b21cap0051.naratik.dataresource.datamodellist.ArticleModel
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.util.DataDummy
import com.b21cap0051.naratik.util.ItemArticleCallBack
import com.b21cap0051.naratik.util.ItemBatikCallBack

class SearchActivity : AppCompatActivity() , ItemArticleCallBack , ItemBatikCallBack
{
	private lateinit var binding : ActivitySearchBinding
	private lateinit var articleMiniAdapter : ArticleMiniListAdapter
	private lateinit var batikMiniAdapter : BatikMiniListAdapter
	
	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivitySearchBinding.inflate(layoutInflater)
		setContentView(binding.root)
		
		loadActionBar()
		loadBatik()
		loadArticle()
	}
	
	private fun loadBatik(){
		batikMiniAdapter = BatikMiniListAdapter(this)
		binding.rvSearchBatik.layoutManager = LinearLayoutManager(
			this ,
			LinearLayoutManager.HORIZONTAL ,
			false)
		binding.rvSearchBatik.adapter = batikMiniAdapter
		val listBatik = DataDummy.generateDummyBatik()
		batikMiniAdapter.setList(listBatik)
	}
	
	private fun loadArticle(){
		articleMiniAdapter = ArticleMiniListAdapter(this)
		binding.rvSearchArticle.layoutManager = LinearLayoutManager(
			this ,
			LinearLayoutManager.HORIZONTAL ,
			false)
		binding.rvSearchArticle.adapter = articleMiniAdapter
		val listArticle = DataDummy.generateDummyArticle()
		articleMiniAdapter.setList(listArticle)
	}
	
	fun loadHistory(){
	
	}
	private fun loadActionBar()
	{
		val btnBack : Button = findViewById(R.id.btnBack)
		btnBack.setOnClickListener() {
			super.onBackPressed()
		}
	}
	
	override fun itemArticleClick(model : ArticleModel)
	{
	}
	
	override fun itemBatikClick(model : BatikEntity)
	{
	}
}