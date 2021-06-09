package com.b21cap0051.naratik.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.b21cap0051.naratik.databinding.ItemRowSearchBatikBinding
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.view.DetailBatikActivity
import com.b21cap0051.naratik.view.DetailBatikActivity.Companion.EXTRA_BATIK
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.util.*

class SearchAdapter() :
	RecyclerView.Adapter<SearchAdapter.ItemTarget>()
{
	
	private val listBatik = ArrayList<BatikEntity>()
	
	fun setList(listBatik : List<BatikEntity>)
	{
		this.listBatik.clear()
		this.listBatik.addAll(listBatik)
		notifyDataSetChanged()
	}
	
	inner class ItemTarget(private val binding : ItemRowSearchBatikBinding) :
		RecyclerView.ViewHolder(binding.root)
	{
		fun bind(model : BatikEntity)
		{
			
			
			Glide.with(itemView.context)
				.load(model.Image)
				.apply(RequestOptions().override(100 , 100))
				.into(binding.ivItemSearchBatik)
			binding.tvItemSearchBatik.text = model.name_batik
			
			binding.cvSearch.setOnClickListener {
				val intent = Intent(itemView.context , DetailBatikActivity::class.java)
				intent.putExtra(EXTRA_BATIK , model)
				itemView.context.startActivity(intent)
			}
		}
		
	}
	
	override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : ItemTarget
	{
		return ItemTarget(
			ItemRowSearchBatikBinding.inflate(
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



