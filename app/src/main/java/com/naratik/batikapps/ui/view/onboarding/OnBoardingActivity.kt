package com.naratik.batikapps.ui.view.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.naratik.batikapps.R
import com.naratik.batikapps.databinding.ActivityOnBoardingBinding
import com.naratik.batikapps.ui.adapter.OnBoardingItemAdapter
import com.naratik.batikapps.ui.adapter.modellist.OnBoardingModel
import com.naratik.batikapps.ui.view.home.HomeActivity


class OnBoardingActivity : AppCompatActivity()
{
	private lateinit var binding : ActivityOnBoardingBinding
	private lateinit var dots : LinearLayout
	private var prevStarted = "yes"
	private lateinit var onBoardingItemAdapter : OnBoardingItemAdapter
	
	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivityOnBoardingBinding.inflate(layoutInflater)
		setContentView(binding.root)
		setOnBoardingItems()
		setDotsOnBoarding()
		setCurrentDotsOnBoarding(0)
	}
	
	override fun onResume()
	{
		super.onResume()
		val sharedPreferences =
			getSharedPreferences(getString(R.string.app_name) , Context.MODE_PRIVATE)
		if (!sharedPreferences.getBoolean(prevStarted , false))
		{
			val editor = sharedPreferences.edit()
			editor.putBoolean(prevStarted , true)
			editor.apply()
		} else
		{
			val intent = Intent(this@OnBoardingActivity , HomeActivity::class.java)
			startActivity(intent)
			finish()
		}
	}
	
	private fun setOnBoardingItems()
	{
		onBoardingItemAdapter = OnBoardingItemAdapter(
			listOf(
				OnBoardingModel(
					"animation_onboarding_1.json" ,
					getString(R.string.title_on_boarding_1) ,
					getString(
						R.string.on_boarding_1
					         )
				               ) ,
				OnBoardingModel(
					"animation_onboarding_2.json" ,
					getString(R.string.title_on_boarding_2) ,
					getString(
						R.string.on_boarding_2
					         )
				               ) ,
				OnBoardingModel(
					"animation_onboarding_3.json" ,
					getString(R.string.title_on_boarding_3) ,
					getString(
						R.string.on_boarding_3
					         )
				               )
			      )
		                                             )
		val viewPager = binding.vpOnBaord
		viewPager.adapter = onBoardingItemAdapter
		viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback()
		{
			override fun onPageSelected(position : Int)
			{
				super.onPageSelected(position)
				setCurrentDotsOnBoarding(position)
			}
			
		})
		(viewPager.getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
		binding.btnNext.setOnClickListener {
			if (viewPager.currentItem + 1 < onBoardingItemAdapter.itemCount)
			{
				viewPager.currentItem += 1
			} else
			{
				val intent = Intent(this@OnBoardingActivity , HomeActivity::class.java)
				startActivity(intent)
				finish()
			}
		}
		binding.btnSkip.setOnClickListener {
			val intent = Intent(this@OnBoardingActivity , HomeActivity::class.java)
			startActivity(intent)
			finish()
		}
	}
	
	private fun setDotsOnBoarding()
	{
		dots = binding.llDots
		
		val indicator = arrayOfNulls<ImageView>(onBoardingItemAdapter.itemCount)
		val layoutParameter : LinearLayout.LayoutParams =
			LinearLayout.LayoutParams(WRAP_CONTENT , WRAP_CONTENT)
		layoutParameter.setMargins(16 , 0 , 0 , 0)
		
		for (i in indicator.indices)
		{
			indicator[i] = ImageView(applicationContext)
			indicator[i]?.let {
				it.setImageDrawable(
					ContextCompat.getDrawable(applicationContext , R.drawable.on_boarding_inactive)
				                   )
				it.layoutParams = layoutParameter
				dots.addView(it)
			}
		}
	}
	
	fun setCurrentDotsOnBoarding(position : Int)
	{
		val childCount = dots.childCount
		for (i in 0 until childCount)
		{
			val imageView = dots.getChildAt(i) as ImageView
			if (i == position)
			{
				imageView.setImageDrawable(
					ContextCompat.getDrawable(applicationContext , R.drawable.on_boarding_active)
				                          )
			} else
			{
				imageView.setImageDrawable(
					ContextCompat.getDrawable(applicationContext , R.drawable.on_boarding_inactive)
				                          )
			}
		}
	}
}

