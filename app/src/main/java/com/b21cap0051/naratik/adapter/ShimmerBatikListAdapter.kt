package com.b21cap0051.naratik.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.b21cap0051.naratik.databinding.ShimmerItemRowBatikBinding
import com.b21cap0051.naratik.dataresource.datamodellist.BatikModel
import com.b21cap0051.naratik.util.ItemBatikCallBack
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ShimmerBatikListAdapter(private val callBack : ItemBatikCallBack) :
	RecyclerView.Adapter<ShimmerBatikListAdapter.ItemTarget>()
{
	
	private val listBatik = ArrayList<BatikModel>()
	
	fun setList(listBatik : List<BatikModel>)
	{
		this.listBatik.clear()
		this.listBatik.addAll(listBatik)
		notifyDataSetChanged()
	}
	
	fun setListLimited(listBatik : List<BatikModel>)
	{
		this.listBatik.clear()
		for (i in 1 .. 4)
		{
			this.listBatik.addAll(listOf(listBatik[i]))
		}
		notifyDataSetChanged()
	}
	
	inner class ItemTarget(private val binding : ShimmerItemRowBatikBinding) :
		RecyclerView.ViewHolder(binding.root)
	{
		fun bind(model : BatikModel)
		{
			
			var height = 900
			if (position % 2 == 1)
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
		holder.bind(listBatik[position])
		
	}
	
	override fun getItemCount() : Int
	{
		return listBatik.size
	}
	
	
}



