package com.b21cap0051.naratik.adapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.view.favourite.favouritefragment.ArticleFavouriteFragment
import com.b21cap0051.naratik.view.favourite.favouritefragment.BatikFavouriteFragment

class SectionPagerAdapter(private val mContext : Context , fm : FragmentManager) :
	FragmentPagerAdapter(fm , BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
{
	
	companion object
	{
		@StringRes
		private val TAB_TITLES = intArrayOf(R.string.batik , R.string.article)
	}
	
	override fun getItem(position : Int) : Fragment =
		when (position)
		{
			0    -> BatikFavouriteFragment()
			1    -> ArticleFavouriteFragment()
			else -> Fragment()
		}
	
	override fun getPageTitle(position : Int) : CharSequence =
		mContext.resources.getString(TAB_TITLES[position])
	
	override fun getCount() : Int = TAB_TITLES.size
	
}