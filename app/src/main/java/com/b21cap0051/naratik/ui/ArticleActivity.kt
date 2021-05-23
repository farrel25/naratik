package com.b21cap0051.naratik.ui

import android.R.attr.*
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.b21cap0051.naratik.adapter.ArticleLargeListAdapter
import com.b21cap0051.naratik.databinding.ActivityArticleBinding
import com.b21cap0051.naratik.dataresource.datamodellist.ArticleModel
import com.b21cap0051.naratik.util.DataDummy
import com.b21cap0051.naratik.util.ItemArticleCallBack


class ArticleActivity : AppCompatActivity() , ItemArticleCallBack
{
    private lateinit var binding : ActivityArticleBinding
    private lateinit var adapterArticle : ArticleLargeListAdapter
    
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
    
        adapterArticle = ArticleLargeListAdapter(this)
    
        var row = 1
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            row = 2
        }
        val listArticle = DataDummy.generateDummyArticle()
        adapterArticle.setList(listArticle)
        binding.rvAllArticle.layoutManager = GridLayoutManager(this , row)
        binding.rvAllArticle.adapter = adapterArticle
    }
    
    override fun itemArticleClick(model : ArticleModel)
    {
    
    }
}