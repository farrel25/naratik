package com.naratik.batikapps.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.naratik.batikapps.databinding.ActivityFeedbackBinding


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