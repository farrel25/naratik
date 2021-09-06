package com.naratik.batikapps.ui.view.home.homefragment

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.naratik.batikapps.core.local.model.BatikEntity
import com.naratik.batikapps.databinding.FragmentExploreBinding
import com.naratik.batikapps.ui.adapter.ArticleListAdapter
import com.naratik.batikapps.ui.adapter.BatikListAdapter
import com.naratik.batikapps.ui.adapter.ShimmerBatikListAdapter
import com.naratik.batikapps.ui.adapter.modellist.ArticleModel
import com.naratik.batikapps.ui.adapter.modellist.ShimmerModel
import com.naratik.batikapps.ui.view.ArticleActivity
import com.naratik.batikapps.ui.view.BatikActivity
import com.naratik.batikapps.ui.view.SearchActivity
import com.naratik.batikapps.ui.viewmodel.BatikMainView
import com.naratik.batikapps.util.ResultStateData
import com.naratik.batikapps.util.dummydata.DataDummy
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : Fragment()
{
	
	private var _binding : FragmentExploreBinding? = null
	private val binding get() = _binding as FragmentExploreBinding
	private var listArticle : ArrayList<ArticleModel> = arrayListOf()
	
	private var listShimmer : ArrayList<ShimmerModel> = arrayListOf()
	private lateinit var adapterArticle : ArticleListAdapter
	private lateinit var adapterBatik : BatikListAdapter
	private lateinit var adapterShimmer : ShimmerBatikListAdapter
	
	
	companion object
	{
		val TAG : String = ExploreFragment::class.java.simpleName
		const val EXTRA_ARTICLE = "extra_article"
		const val EXTRA_BATIK = "extra_batik"
	}
	
	override fun onCreateView(
		inflater : LayoutInflater ,
		container : ViewGroup? ,
		savedInstanceState : Bundle?
	                         ) : View
	{
		_binding = FragmentExploreBinding.inflate(layoutInflater , container , false)
		return binding.root
	}
	
	private val mainView : BatikMainView by viewModels()
	
	
	override fun onViewCreated(view : View , savedInstanceState : Bundle?)
	{
		super.onViewCreated(view , savedInstanceState)
		loadShimmerBatikList()
		mainView.getAllbatikRandom().observe(viewLifecycleOwner , {
			when (it)
			{
				is ResultStateData.Success ->
				{
					loadListBatik(it.data)
				}
				is ResultStateData.Failure ->
				{
					Toast.makeText(requireActivity() , it.message , Toast.LENGTH_SHORT).show()
					binding.shimmerLayout.visibility = View.GONE
				}
				
				is ResultStateData.Loading ->
				{
				
				}
				else                       ->
				{
				
				}
			}
		})
		
		binding.svSearch.isEnabled = true
		binding.svSearch.setOnClickListener {
			val move = Intent(requireContext() , SearchActivity::class.java)
			startActivity(move)
			onPause()
		}
		
		loadListArticle()
	}
	
	private fun loadListBatik(value : List<BatikEntity>)
	{
		adapterBatik = BatikListAdapter(requireContext() , mainView)
		var row = 2
		if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
		{
			row = 4
		}
		
		binding.rvBatik.layoutManager =
			StaggeredGridLayoutManager(row , StaggeredGridLayoutManager.VERTICAL)
		binding.rvBatik.adapter = adapterBatik
		adapterBatik.setList(value)
		
		binding.shimmerLayout.visibility = View.GONE
		
		binding.btnShowAllBatik.setOnClickListener {
			val intent = Intent(requireActivity() , BatikActivity::class.java)
			startActivity(intent)
			onDetach()
		}
	}
	
	private fun loadShimmerBatikList()
	{
		var row = 2
		if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
		{
			row = 4
		}
		adapterShimmer = ShimmerBatikListAdapter()
		
		listShimmer = DataDummy.generateShimmerBatik()
		binding.rvShimmer.layoutManager =
			StaggeredGridLayoutManager(row , StaggeredGridLayoutManager.VERTICAL)
		binding.rvShimmer.adapter = adapterShimmer
		
		adapterShimmer.setList(listShimmer)
	}
	
	private fun loadListArticle()
	{
		adapterArticle = ArticleListAdapter()
		
		binding.rvArticle.layoutManager =
			LinearLayoutManager(activity , LinearLayoutManager.HORIZONTAL , false)
		binding.rvArticle.adapter = adapterArticle
		listArticle = DataDummy.generateDummyArticle()
		adapterArticle.setList(listArticle)
		
		binding.btnShowAllArticle.setOnClickListener {
			val intent = Intent(requireActivity() , ArticleActivity::class.java)
			startActivity(intent)
		}
	}
	
	
}