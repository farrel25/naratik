package com.b21cap0051.naratik.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.b21cap0051.naratik.databinding.ActivityArticleBinding

class ArticleActivity : AppCompatActivity() {
    private lateinit var binding : ActivityArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}