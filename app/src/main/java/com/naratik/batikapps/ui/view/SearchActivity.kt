package com.naratik.batikapps.ui.view

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.naratik.batikapps.R
import com.naratik.batikapps.core.local.model.BatikEntity
import com.naratik.batikapps.core.local.model.HistoryEntity
import com.naratik.batikapps.databinding.ActivitySearchBinding
import com.naratik.batikapps.ui.adapter.ArticleMiniListAdapter
import com.naratik.batikapps.ui.adapter.BatikMiniListAdapter
import com.naratik.batikapps.ui.adapter.HistoryAdapter
import com.naratik.batikapps.ui.adapter.SearchAdapter
import com.naratik.batikapps.ui.viewmodel.SearchMainView
import com.naratik.batikapps.util.ItemCallbackHistory
import com.naratik.batikapps.util.ResultStateData
import com.naratik.batikapps.util.dummydata.DataDummy
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() , ItemCallbackHistory
{
	private lateinit var binding : ActivitySearchBinding
	private lateinit var articleMiniAdapter : ArticleMiniListAdapter
	private lateinit var batikMiniAdapter : BatikMiniListAdapter
	private lateinit var searchAdapter : SearchAdapter
	private val viewModel : SearchMainView by viewModels()
	private lateinit var adapterHistory : HistoryAdapter
	private var posIndexAdapter = 0
	
	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivitySearchBinding.inflate(layoutInflater)
		setContentView(binding.root)
		
		
		adapterHistory = HistoryAdapter(this)
		
		binding.rvSearchHistory.visibility = View.GONE
		binding.svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener
		{
			override fun onQueryTextSubmit(query : String?) : Boolean
			{
				if (query != null && TextUtils.getTrimmedLength(query) > 0)
				{
					findItem(query)
					binding.rvSearchBatik.visibility = View.VISIBLE
					binding.llSearch.visibility = View.GONE
				}
				return false
			}
			
			override fun onQueryTextChange(newText : String?) : Boolean
			{
				
				if (newText != null)
				{
					if (newText.isNotEmpty())
					{
						findItem(newText)
						binding.rvSearchBatik.visibility = View.VISIBLE
						binding.llSearch.visibility = View.GONE
					} else
					{
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
		viewModel.getSearch(text).observe(this , {
			when (it)
			{
				is ResultStateData.Success ->
				{
				   if (it.data.isNotEmpty()){
				   	loadSearch(it.data)
				   }
					
					
				}
				is ResultStateData.Failure ->
				{
					Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
				}
				is ResultStateData.Loading ->
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
		searchAdapter = SearchAdapter(viewModel)
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
		
		viewModel.getALLHistory().observe(this , {
			when (it)
			{
				is ResultStateData.Success ->
				{
					if (it.data.isNotEmpty())
					{
						adapterHistory.setList(it.data)
					}
					binding.wgHistory.visibility = View.VISIBLE
					binding.txtHistory.visibility = View.VISIBLE
					binding.loadHistory.visibility = View.GONE
					binding.rvSearchHistory.visibility = View.VISIBLE
				}
				is ResultStateData.Failure ->
				{
					binding.wgHistory.visibility = View.GONE
					binding.txtHistory.visibility = View.GONE
					binding.loadHistory.visibility = View.GONE
					binding.rvSearchHistory.visibility = View.VISIBLE
					Toast.makeText(this , it.message , Toast.LENGTH_SHORT).show()
				}
				
				is ResultStateData.Loading ->
				{
					binding.wgHistory.visibility = View.GONE
					binding.txtHistory.visibility = View.GONE
					binding.loadHistory.visibility = View.VISIBLE
					binding.rvSearchHistory.visibility = View.GONE
				}
				
				else -> {
					binding.txtHistory.visibility = View.GONE
					binding.wgHistory.visibility = View.GONE
					binding.loadHistory.visibility = View.VISIBLE
					binding.rvSearchHistory.visibility = View.GONE
				}
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
		viewModel.delHistory(model)
		adapterHistory.removeItem(posIndexAdapter)
	}
	
	override fun getPosition(Position : Int)
	{
		posIndexAdapter = Position
		
	}
	
}