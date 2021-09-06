package com.naratik.batikapps.ui.view

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.naratik.batikapps.R
import com.naratik.batikapps.databinding.ActivityDetailArticleBinding
import com.naratik.batikapps.ui.adapter.ArticleMiniListAdapter
import com.naratik.batikapps.ui.adapter.modellist.ArticleModel
import com.naratik.batikapps.util.dummydata.DataDummy
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailArticleActivity : AppCompatActivity()
{
	private lateinit var binding : ActivityDetailArticleBinding
	private lateinit var articleMiniAdapter : ArticleMiniListAdapter
	
	companion object
	{
		val TAG : String = DetailArticleActivity::class.java.simpleName
		const val EXTRA_ARTICLE = "extra_article"
	}
	
	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivityDetailArticleBinding.inflate(layoutInflater)
		setContentView(binding.root)
		
		
		
		articleMiniAdapter = ArticleMiniListAdapter()
		
		loadViewMore()
		loadDetail()
		loadActionBar()
		
		
	}
	
	private fun loadDetail()
	{
		val article = intent.getParcelableExtra<ArticleModel>(EXTRA_ARTICLE) as ArticleModel
		binding.collapsingToolbar.title = article.title
		binding.tvArticleOverview.text = article.overview
		binding.tvDateArticle.text = article.date
		binding.tvAdminArticle.text = article.writer
		
		Glide.with(this)
			.load(article.image)
			.apply(RequestOptions().override(500 , 500))
			.apply(
				RequestOptions.placeholderOf(R.drawable.ic_loading)
					.error(R.drawable.ic_error)
			      )
			.into(binding.ivArticle)
		
		Glide.with(this)
			.load(article.imageWriter)
			.apply(RequestOptions().override(500 , 500))
			.apply(
				RequestOptions.placeholderOf(R.drawable.ic_loading)
					.error(R.drawable.ic_error)
			      )
			.into(binding.ivAdmin)
	}
	
	private fun loadViewMore()
	{
		binding.rvVMArticle.layoutManager = LinearLayoutManager(
			this , LinearLayoutManager.HORIZONTAL , false
		                                                       )
		binding.rvVMArticle.adapter = articleMiniAdapter
		val listArticle = DataDummy.generateDummyArticle()
		articleMiniAdapter.setList(listArticle)
	}
	
	private fun loadActionBar()
	{
		val btnBack : Button = findViewById(R.id.btnBack)
		btnBack.setOnClickListener() {
			super.onBackPressed()
		}
	}
	
}