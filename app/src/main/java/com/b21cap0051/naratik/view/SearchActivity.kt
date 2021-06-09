package com.b21cap0051.naratik.view

import android.os.Bundle
import android.text.TextUtils
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
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.dataresource.local.model.HistoryEntity
import com.b21cap0051.naratik.viewmodel.SearchMainView
import com.b21cap0051.naratik.viewmodel.ViewFactoryModel
import com.b21cap0051.naratik.util.DataDummy
import com.b21cap0051.naratik.util.ItemCallbackHistory
import com.b21cap0051.naratik.util.vo.Status.*

class SearchActivity : AppCompatActivity() , ItemCallbackHistory
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
		binding.svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener
		{
			override fun onQueryTextSubmit(query : String?) : Boolean
			{
				if (query != null && TextUtils.getTrimmedLength(query) > 0)
				{
					findItem(query)
					viewModel.AddHistory(HistoryEntity(0 , query))
					binding.rvSearchBatik.visibility = View.VISIBLE
					binding.llSearch.visibility = View.GONE
				}
				return false
			}
			
			override fun onQueryTextChange(newText : String?) : Boolean
			{
				
				if (newText != null)
				{
					if(newText.isNotEmpty()){
						findItem(newText)
						binding.rvSearchBatik.visibility = View.VISIBLE
						binding.llSearch.visibility = View.GONE
					}else{
						binding.rvSearchBatik.visibility = View.GONE
						binding.llSearch.visibility = View.VISIBLE
					}
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
		viewModel.GetSearch(text).observe(this , { response ->
			when (response.Status)
			{
				SUCCESS ->
				{
					loadSearch(response.Data!!)
				}
				LOADING ->
				{
				
				}
				ERROR   ->
				{
				
				}
			}
		})
	}
	
	private fun loadBatik()
	{
		batikMiniAdapter = BatikMiniListAdapter()
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
		searchAdapter = SearchAdapter()
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
		articleMiniAdapter = ArticleMiniListAdapter()
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
		binding.rvSearchHistory.adapter = adapterHistory
		
		viewModel.GetALLHistory().observe(this , { response ->
			if (response?.size != 0)
			{
				adapterHistory.setList(response)
				binding.rvSearchHistory.visibility = View.VISIBLE
			} else
			{
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