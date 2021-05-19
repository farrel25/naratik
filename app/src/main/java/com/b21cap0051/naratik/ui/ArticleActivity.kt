package com.b21cap0051.naratik.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.b21cap0051.naratik.adapter.ArticleListAdapter
import com.b21cap0051.naratik.databinding.ActivityArticleBinding
import com.b21cap0051.naratik.util.DataDummy

class ArticleActivity : AppCompatActivity() {
    private lateinit var binding : ActivityArticleBinding
    private lateinit var adapterArticle : ArticleListAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        adapterArticle = ArticleListAdapter(DataDummy.generateDummyArticle())
        binding.rvArticle.layoutManager = LinearLayoutManager(this)
        binding.rvArticle.adapter = adapterArticle
    }
}