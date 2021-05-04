package com.b21cap0051.naratik.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.b21cap0051.naratik.databinding.ActivityDetailBatikBinding

class DetailBatikActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBatikBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBatikBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}