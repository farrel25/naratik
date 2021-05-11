package com.b21cap0051.naratik.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.databinding.ActivityBatikBinding

class BatikActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBatikBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBatikBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}