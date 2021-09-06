package com.naratik.batikapps.ui.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.naratik.batikapps.R
import com.naratik.batikapps.core.local.model.ShopEntity
import com.naratik.batikapps.databinding.ItemRowAllStoreBinding

class StoreAllAdapter() : RecyclerView.Adapter<StoreAllAdapter.ItemTarget>()
{
	
	private val listShop = ArrayList<ShopEntity>()
	
	fun setList(data : List<ShopEntity>)
	{
		if (listShop.isNotEmpty())
		{
			listShop.clear()
			listShop.addAll(data)
			notifyDataSetChanged()
		}
		listShop.addAll(data)
		
	}
	
	
	inner class ItemTarget(private val binding : ItemRowAllStoreBinding) :
		RecyclerView.ViewHolder(binding.root)
	{
		fun bind(model : ShopEntity)
		{
			
			binding.tvItemAllNameStore.text = model.namaToko
			binding.tvItemAllLocationStore.text = model.alamatToko
			binding.tvItemAllProductStore.text =
				itemView.resources.getString(R.string.product, model.productToko)
			
			binding.btnItemLocationStore.setOnClickListener {
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
		holder.bind(listShop[position])
	}
	
	override fun getItemCount() : Int
	{
		return listShop.size
	}
}