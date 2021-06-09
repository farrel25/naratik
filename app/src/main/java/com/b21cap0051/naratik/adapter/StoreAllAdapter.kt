package com.b21cap0051.naratik.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.databinding.ItemRowAllStoreBinding
import com.b21cap0051.naratik.dataresource.local.model.ShopEntity


class StoreAllAdapter :
	PagedListAdapter<ShopEntity , StoreAllAdapter.ItemTarget>(DIFF_CALLBACK)
{
	
	
	companion object
	{
		val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ShopEntity>()
		{
			override fun areItemsTheSame(oldItem : ShopEntity , newItem : ShopEntity) : Boolean
			{
				return oldItem.id == newItem.id
			}
			
			override fun areContentsTheSame(oldItem : ShopEntity , newItem : ShopEntity) : Boolean
			{
				return oldItem == newItem
			}
			
		}
		
	}
	
	
	inner class ItemTarget(private val binding : ItemRowAllStoreBinding) :
		RecyclerView.ViewHolder(binding.root)
	{
		fun bind(model : ShopEntity)
		{
			binding.tvItemAllNameStore.text = model.namaToko
			binding.tvItemAllLocationStore.text = model.alamatToko
			binding.tvItemAllProductStore.text =
				itemView.resources.getString(R.string.product , model.productToko)
			
			binding.btnItemLocationStore.setOnClickListener{
				val intent = Intent(
					Intent.ACTION_VIEW ,
					Uri.parse("https://www.google.com/maps/search/?api=1&query=${model.alamatToko}")
				                   )
				itemView.context.startActivity(intent)
			}
		}
		
	}
	
	override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : ItemTarget
	{
		return ItemTarget(
			ItemRowAllStoreBinding.inflate(
				LayoutInflater.from(parent.context) ,
				parent ,
				false
			                              )
		                 )
	}
	
	override fun onBindViewHolder(holder : ItemTarget , position : Int)
	{
		holder.bind(getItem(position) as ShopEntity)
	}
	
}



