package com.b21cap0051.naratik.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.adapter.ArticleMiniListAdapter
import com.b21cap0051.naratik.adapter.BatikMiniListAdapter
import com.b21cap0051.naratik.adapter.HistoryAdapter
import com.b21cap0051.naratik.adapter.SearchAdapter
import com.b21cap0051.naratik.databinding.ActivitySearchBinding
import com.b21cap0051.naratik.dataresource.datamodellist.ArticleModel
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.dataresource.local.model.HistoryEntity
import com.b21cap0051.naratik.mainview.SearchMainView
import com.b21cap0051.naratik.mainview.ViewFactoryModel
import com.b21cap0051.naratik.util.DataDummy
import com.b21cap0051.naratik.util.ItemArticleCallBack
import com.b21cap0051.naratik.util.ItemBatikCallBack
import com.b21cap0051.naratik.util.ItemCallbackHistory
import com.b21cap0051.naratik.util.vo.Status

class SearchActivity : AppCompatActivity() , ItemArticleCallBack , ItemBatikCallBack,ItemCallbackHistory
{
	private lateinit var binding : ActivitySearchBinding
	private lateinit var articleMiniAdapter : ArticleMiniListAdapter
	private lateinit var batikMiniAdapter : BatikMiniListAdapter
	private lateinit var searchAdapter : SearchAdapter
	private lateinit var viewModel : SearchMainView
	private lateinit var adapterHistory : HistoryAdapter
	private var posIndexAdapter = 0
	
	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivitySearchBinding.inflate(layoutInflater)
		setContentView(binding.root)
		
		
		val factory = ViewFactoryModel.GetInstance(this)
		viewModel = ViewModelProvider(this , factory)[SearchMainView::class.java]
		
		adapterHistory = HistoryAdapter(this)
		
		binding.rvSearchHistory.visibility = View.GONE
		binding.etSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener
		{
			override fun onQueryTextSubmit(query : String?) : Boolean
			{
				if (query != null)
				{
					findItem(query)
					viewModel.AddHistory(HistoryEntity(0,query))
					binding.rvSearchBatik.visibility = View.VISIBLE
					binding.llSearch.visibility = View.GONE
					return true
				}
				return false
			}
			
			override fun onQueryTextChange(newText : String?) : Boolean
			{
				if (newText != null)
				{
					findItem(newText)
					viewModel.AddHistory(HistoryEntity(0,newText))
					binding.rvSearchBatik.visibility = View.VISIBLE
					binding.llSearch.visibility = View.GONE
					return true
				}
				return false
			}
		})
		loadActionBar()
		loadBatik()
		loadArticle()
		loadHistory()
	}
	
	
	private fun findItem(text : String)
	{
		viewModel.GetSearch(text).observe(this , {
		     response ->
			when(response.Status){
				Status.SUCCESS -> {
					
					loadSearch(response.Data!!)
				}
			}
		})
	}
	
	private fun loadBatik()
	{
		batikMiniAdapter = BatikMiniListAdapter(this)
		binding.rvSearchPopularBatik.layoutManager = LinearLayoutManager(
			this ,
			LinearLayoutManager.HORIZONTAL ,
			false
		                                                         )
		binding.rvSearchPopularBatik.adapter = batikMiniAdapter
		val listBatik = DataDummy.generateDummyBatik()
		batikMiniAdapter.setList(listBatik)
	}
	
	private fun loadSearch(model : List<BatikEntity>)
	{
		searchAdapter = SearchAdapter(this)
		binding.rvSearchBatik.layoutManager = LinearLayoutManager(
			this ,
			LinearLayoutManager.VERTICAL ,
			false
		                                                         )
		binding.rvSearchBatik.adapter = searchAdapter
		searchAdapter.setList(model)
	}
	
	private fun loadArticle()
	{
		articleMiniAdapter = ArticleMiniListAdapter(this)
		binding.rvSearchPopularArticle.layoutManager = LinearLayoutManager(
			this ,
			LinearLayoutManager.HORIZONTAL ,
			false
		                                                           )
		binding.rvSearchPopularArticle.adapter = articleMiniAdapter
		val listArticle = DataDummy.generateDummyArticle()
		articleMiniAdapter.setList(listArticle)
	}
	
	private fun loadHistory()
	{
		binding.rvSearchHistory.layoutManager = LinearLayoutManager(this)
		binding.rvSearchHistory.adapter =adapterHistory
		
		viewModel.GetALLHistory().observe(this,{
			response ->
			if(response?.size != 0){
				adapterHistory.setList(response)
				binding.rvSearchHistory.visibility = View.VISIBLE
			}else{
				binding.rvSearchHistory.visibility = View.VISIBLE
			}
		})
	}
	
	private fun loadActionBar()
	{
		val title : TextView = findViewById(R.id.tvTitle)
		title.text = resources.getString(R.string.search)
		val btnBack : Button = findViewById(R.id.btnBack)
		btnBack.setOnClickListener {
			super.onBackPressed()
		}
	}
	
	override fun itemArticleClick(model : ArticleModel)
	{
	}
	
	override fun itemBatikClick(model : BatikEntity)
	{
	}
	
	
	
	override fun getItem(model : HistoryEntity)
	{
		viewModel.DelHistory(model)
		adapterHistory.removeItem(posIndexAdapter)
	}
	
	override fun getPosition(Position : Int)
	{
		posIndexAdapter = Position
	
	}
	
}