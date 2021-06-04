package com.b21cap0051.naratik.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.databinding.ItemRowAllStoreBinding
import com.b21cap0051.naratik.dataresource.local.model.StoreEntity
import com.b21cap0051.naratik.util.ItemStoreCallback
import java.util.*

class StoreAllAdapter(private val callBack : ItemStoreCallback) :
	RecyclerView.Adapter<StoreAllAdapter.ItemTarget>() {
	
	private val listStore = ArrayList<StoreEntity>()
	
	fun setList(listStore : List<StoreEntity>)
	{
		this.listStore.clear()
		this.listStore.addAll(listStore)
		notifyDataSetChanged()
	}
	
	inner class ItemTarget(private val binding : ItemRowAllStoreBinding) :
		RecyclerView.ViewHolder(binding.root)
	{
		fun bind(model : StoreEntity)
		{
			binding.tvItemAllNameStore.text = model.name
			binding.tvItemAllLocationStore.text = model.location
			binding.tvItemAllProductStore.text = itemView.resources.getString(R.string.product, model.product)
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
		holder.bind(listStore[position])
		
	}
	
	override fun getItemCount() : Int
	{
		return listStore.size
	}
	
	
}



