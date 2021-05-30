package com.b21cap0051.naratik.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.b21cap0051.naratik.ui.favourite.favouritefragment.ArticleFavouriteFragment
import com.b21cap0051.naratik.ui.favourite.favouritefragment.BatikFavouriteFragment

class FavouritAdapterPager(private val ctx : FragmentActivity) : FragmentStateAdapter(ctx)
{
	override fun getItemCount() : Int = 2
	
	override fun createFragment(position : Int) : Fragment
	{
		when (position)
		{
			0    -> return BatikFavouriteFragment()
			1    -> return ArticleFavouriteFragment()
			else -> return Fragment()
		}
	}
}