package com.b21cap0051.naratik.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.b21cap0051.naratik.adapter.ArticleLargeListAdapter
import com.b21cap0051.naratik.adapter.ArticleListAdapter
import com.b21cap0051.naratik.databinding.ActivityArticleBinding
import com.b21cap0051.naratik.util.DataDummy

class ArticleActivity : AppCompatActivity() {
    private lateinit var binding : ActivityArticleBinding
    private lateinit var adapterArticle : ArticleLargeListAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        adapterArticle = ArticleLargeListAdapter(DataDummy.generateDummyArticle())
        binding.rvAllArticle.layoutManager = LinearLayoutManager(this)
        binding.rvAllArticle.adapter = adapterArticle
    }
}