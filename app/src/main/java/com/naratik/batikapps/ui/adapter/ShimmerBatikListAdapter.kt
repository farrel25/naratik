package com.naratik.batikapps.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.naratik.batikapps.databinding.ShimmerItemRowBatikBinding
import com.naratik.batikapps.ui.adapter.modellist.ShimmerModel

class ShimmerBatikListAdapter :
	RecyclerView.Adapter<ShimmerBatikListAdapter.ItemTarget>()
{
	
	private val shimmer = ArrayList<ShimmerModel>()
	
	fun setList(shimmer : List<ShimmerModel>)
	{
		this.shimmer.clear()
		this.shimmer.addAll(shimmer)
		notifyDataSetChanged()
	}
	
	inner class ItemTarget(private val binding : ShimmerItemRowBatikBinding) :
		RecyclerView.ViewHolder(binding.root)
	{
		fun bind(model : ShimmerModel)
		{
			var height = 900
			if (layoutPosition % 2 == 1)
			{
				height = 450
			}
			
			Glide.with(itemView.context)
				.load(model.image)
				.apply(RequestOptions().override(200 , height))
				.into(binding.ivItemBatik)
		}
		
	}
	
	override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : ItemTarget
	{
		return ItemTarget(
			ShimmerItemRowBatikBinding.inflate(
				LayoutInflater.from(parent.context) ,
				parent ,
				false
			                                  )
		                 )
	}
	
	override fun onBindViewHolder(holder : ItemTarget , position : Int)
	{
		holder.bind(shimmer[position])
		
	}
	
	override fun getItemCount() : Int
	{
		return shimmer.size
	}
	
	
}



