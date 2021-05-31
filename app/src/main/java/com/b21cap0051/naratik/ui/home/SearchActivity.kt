package com.b21cap0051.naratik.ui.home

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.adapter.ArticleMiniListAdapter
import com.b21cap0051.naratik.adapter.BatikMiniListAdapter
import com.b21cap0051.naratik.databinding.ActivitySearchBinding
import com.b21cap0051.naratik.dataresource.datamodellist.ArticleModel
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.mainview.SearchMainView
import com.b21cap0051.naratik.mainview.ViewFactoryModel
import com.b21cap0051.naratik.util.DataDummy
import com.b21cap0051.naratik.util.ItemArticleCallBack
import com.b21cap0051.naratik.util.ItemBatikCallBack

class SearchActivity : AppCompatActivity() , ItemArticleCallBack , ItemBatikCallBack
{
	private lateinit var binding : ActivitySearchBinding
	private lateinit var articleMiniAdapter : ArticleMiniListAdapter
	private lateinit var batikMiniAdapter : BatikMiniListAdapter
	private lateinit var viewModel : SearchMainView
	
	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivitySearchBinding.inflate(layoutInflater)
		setContentView(binding.root)
		
		
		val factory = ViewFactoryModel.GetInstance(this)
		viewModel = ViewModelProvider(this , factory)[SearchMainView::class.java]
		
		binding.etSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener
		{
			override fun onQueryTextSubmit(query : String?) : Boolean
			{
				if (query != null)
				{
					findItem(query)
					return true
				}
				return false
			}
			
			override fun onQueryTextChange(newText : String?) : Boolean
			{
				if (newText != null)
				{
					findItem(newText)
					return true
				}
				return false
			}
			
		})
		
		
		loadActionBar()
		loadBatik()
		loadArticle()
	}
	
	
	private fun findItem(text : String)
	{
		viewModel.GetSearch(text).observe(this , {
		
		})
	}
	
	private fun loadBatik()
	{
		batikMiniAdapter = BatikMiniListAdapter(this)
		binding.rvSearchBatik.layoutManager = LinearLayoutManager(
			this ,
			LinearLayoutManager.HORIZONTAL ,
			false
		                                                         )
		binding.rvSearchBatik.adapter = batikMiniAdapter
		val listBatik = DataDummy.generateDummyBatik()
		batikMiniAdapter.setList(listBatik)
	}
	
	private fun loadArticle()
	{
		articleMiniAdapter = ArticleMiniListAdapter(this)
		binding.rvSearchArticle.layoutManager = LinearLayoutManager(
			this ,
			LinearLayoutManager.HORIZONTAL ,
			false
		                                                           )
		binding.rvSearchArticle.adapter = articleMiniAdapter
		val listArticle = DataDummy.generateDummyArticle()
		articleMiniAdapter.setList(listArticle)
	}
	
	fun loadHistory()
	{
	
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