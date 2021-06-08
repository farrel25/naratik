package com.b21cap0051.naratik.view

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.adapter.ArticleLargeListAdapter
import com.b21cap0051.naratik.databinding.ActivityArticleBinding
import com.b21cap0051.naratik.util.DataDummy


class ArticleActivity : AppCompatActivity()
{
	private lateinit var binding : ActivityArticleBinding
	private lateinit var adapterArticle : ArticleLargeListAdapter
	
	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivityArticleBinding.inflate(layoutInflater)
		setContentView(binding.root)
		
		loadActionBar()
		
		adapterArticle = ArticleLargeListAdapter()
		
		var row = 1
		if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
		{
			row = 2
		}
		val listArticle = DataDummy.generateDummyArticle()
		adapterArticle.setList(listArticle)
		binding.rvAllArticle.layoutManager = GridLayoutManager(this , row)
		binding.rvAllArticle.adapter = adapterArticle
	}
	
	private fun loadActionBar()
	{
		val btnBack : Button = findViewById(R.id.btnBack)
		val title : TextView = findViewById(R.id.tvTitle)
		title.text = resources.getString(R.string.article)
		btnBack.setOnClickListener() {
			super.onBackPressed()
		}
	}
	

}