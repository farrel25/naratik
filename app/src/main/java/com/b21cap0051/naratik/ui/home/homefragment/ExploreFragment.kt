package com.b21cap0051.naratik.ui.home.homefragment

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.b21cap0051.naratik.adapter.ArticleListAdapter
import com.b21cap0051.naratik.adapter.BatikListAdapter
import com.b21cap0051.naratik.databinding.FragmentExploreBinding
import com.b21cap0051.naratik.dataresource.datamodellist.ArticleModel
import com.b21cap0051.naratik.dataresource.datamodellist.BatikModel
import com.b21cap0051.naratik.ui.ArticleActivity
import com.b21cap0051.naratik.ui.BatikActivity
import com.b21cap0051.naratik.util.DataDummy
import com.b21cap0051.naratik.util.ItemArticleCallBack
import com.b21cap0051.naratik.util.ItemBatikCallBack


class ExploreFragment : Fragment() , ItemBatikCallBack,ItemArticleCallBack
{
	
	private var _binding : FragmentExploreBinding? = null
	private val binding get() = _binding as FragmentExploreBinding
	private var list : ArrayList<ArticleModel> = arrayListOf()
	private lateinit var adapterArticle : ArticleListAdapter
	private lateinit var adapterBatik : BatikListAdapter
	
	companion object {
		val TAG : String = ExploreFragment::class.java.simpleName
		const val EXTRA_ARTICLE = "extra_article"
		const val EXTRA_BATIK = "extra_batik"
	}
	
	override fun onCreateView(
		inflater : LayoutInflater ,
		container : ViewGroup? ,
		savedInstanceState : Bundle?
	                         ) : View {
		_binding = FragmentExploreBinding.inflate(layoutInflater , container , false)
		return binding.root
	}
	
	override fun onViewCreated(view : View , savedInstanceState : Bundle?)
	{
		super.onViewCreated(view , savedInstanceState)
		loadListBatik()
		loadListArticle()
	}
	
	private fun loadListBatik(){
		adapterBatik = BatikListAdapter(this)
		
		var row = 2
		val orientCheck = resources.configuration.orientation
		if (orientCheck == Configuration.ORIENTATION_LANDSCAPE)
		{
			row = 4
			
		}
		
		binding.rvBatik.layoutManager = StaggeredGridLayoutManager(row , StaggeredGridLayoutManager.VERTICAL)
		binding.rvBatik.adapter = adapterBatik
		val listBatik = DataDummy.generateDummyBatik()
		adapterBatik.setList(listBatik)
		
		binding.btnShowAllBatik.setOnClickListener{
			val intent = Intent(requireActivity(), BatikActivity::class.java)
			startActivity(intent)
		}
	}
	
	private fun loadListArticle(){
		adapterArticle = ArticleListAdapter(this)
		
		binding.rvArticle.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
		binding.rvArticle.adapter = adapterArticle
		val listArticle = DataDummy.generateDummyArticle()
		adapterArticle.setList(listArticle)
		
		binding.btnShowAllArticle.setOnClickListener{
			val intent = Intent(requireActivity(), ArticleActivity::class.java)
			startActivity(intent)
		}
	}
	
	override fun itemBatikClick(model : BatikModel)
	{
	
	}
	
	override fun itemArticleClick(model : ArticleModel)
	{
	
	}
}