package com.b21cap0051.naratik.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.adapter.ArticleMiniListAdapter
import com.b21cap0051.naratik.databinding.ActivityDetailArticleBinding
import com.b21cap0051.naratik.dataresource.datamodellist.ArticleModel
import com.b21cap0051.naratik.util.DataDummy
import com.b21cap0051.naratik.util.ItemArticleCallBack


class DetailArticleActivity : AppCompatActivity(),ItemArticleCallBack {
    private lateinit var binding : ActivityDetailArticleBinding
    private lateinit var articleAdapter : ArticleMiniListAdapter

    companion object {
        val TAG: String = DetailArticleActivity::class.java.simpleName
        const val EXTRA_ARTICLE = "extra_article"
    }

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        articleAdapter = ArticleMiniListAdapter(this)
    
        loadActionBar()
        binding.rvVMArticle.layoutManager = LinearLayoutManager(
            this ,
            LinearLayoutManager.HORIZONTAL ,
            false
                                                               )
        binding.rvVMArticle.adapter = articleAdapter
        val listArticle = DataDummy.generateDummyArticle()
        articleAdapter.setList(listArticle)

    }
    
    private fun loadActionBar(){
        val btnBack : Button = findViewById(R.id.btnBack)
        btnBack.setOnClickListener(){
            super.onBackPressed()
        }
    }
    
    override fun itemArticleClick(model : ArticleModel)
    {
    
    }
}