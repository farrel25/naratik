package com.b21cap0051.naratik.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.b21cap0051.naratik.databinding.ActivityDetailArticleBinding
import com.b21cap0051.naratik.ui.home.homefragment.ExploreFragment

class DetailArticleActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailArticleBinding

    companion object {
        val TAG: String = ExploreFragment::class.java.simpleName
        const val EXTRA_ARTICLE = "extra_article"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}