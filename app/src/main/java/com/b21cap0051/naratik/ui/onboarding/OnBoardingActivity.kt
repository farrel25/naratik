package com.b21cap0051.naratik.ui.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.b21cap0051.naratik.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding : ActivityOnBoardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}