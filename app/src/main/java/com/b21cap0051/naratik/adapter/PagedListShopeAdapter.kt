package com.b21cap0051.naratik.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.b21cap0051.naratik.dataresource.local.model.ShopEntity

//class PagedListShopeAdapter:PagedListAdapter<ShopEntity,PagedListShopeAdapter.ItemTarget>(DIFF_CALLBACK)
//{
//	inner class ItemTarget():RecyclerView.ViewHolder()
//	{
//
//	}
//
//	companion object{
//		private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ShopEntity>(){
//			override fun areItemsTheSame(oldItem : ShopEntity , newItem : ShopEntity) : Boolean
//			{
//				return oldItem.id == newItem.id
//			}
//
//			override fun areContentsTheSame(oldItem : ShopEntity , newItem : ShopEntity) : Boolean
//			{
//				return oldItem == newItem
//			}
//
//		}
//	}
//
//	override fun onCreateViewHolder(
//		parent : ViewGroup ,
//		viewType : Int
//	                               ) : PagedListShopeAdapter.ItemTarget
//	{
//		TODO("Not yet implemented")
//	}
//
//	override fun onBindViewHolder(holder : PagedListShopeAdapter.ItemTarget , position : Int)
//	{
//		TODO("Not yet implemented")
//	}
//}