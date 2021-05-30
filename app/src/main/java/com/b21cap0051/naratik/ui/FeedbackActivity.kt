package com.b21cap0051.naratik.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.b21cap0051.naratik.databinding.ActivityFeedbackBinding

class FeedbackActivity : AppCompatActivity()
{
	private lateinit var binding : ActivityFeedbackBinding
	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivityFeedbackBinding.inflate(layoutInflater)
		setContentView(binding.root)
	}
}