package com.b21cap0051.naratik.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.b21cap0051.naratik.databinding.ItemRowBatikBinding
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.ui.DetailBatikActivity
import com.b21cap0051.naratik.util.ItemBatikCallBack
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import eightbitlab.com.blurview.RenderScriptBlur

class BatikPagedListAdapter(private val callBack : ItemBatikCallBack) :
	PagedListAdapter<BatikEntity , BatikPagedListAdapter.itemTarget>(DIFF_CALLBACK)
{
	companion object
	{
		private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BatikEntity>()
		{
			override fun areItemsTheSame(oldItem : BatikEntity , newItem : BatikEntity) : Boolean
			{
				return oldItem.batik_id == newItem.batik_id
			}
			
			override fun areContentsTheSame(oldItem : BatikEntity , newItem : BatikEntity) : Boolean
			{
				return oldItem == newItem
			}
			
		}
		
	}
	
	override fun onCreateViewHolder(
		parent : ViewGroup ,
		viewType : Int
	                               ) : BatikPagedListAdapter.itemTarget
	{
		return itemTarget(
			ItemRowBatikBinding.inflate(
				LayoutInflater.from(parent.context) ,
				parent ,
				false
			                           )
		                 )
	}
	
	override fun onBindViewHolder(holder : BatikPagedListAdapter.itemTarget , position : Int)
	{
		holder.bind(getItem(position) as BatikEntity)
	}
	
	
	inner class itemTarget(private val binding : ItemRowBatikBinding) :
		RecyclerView.ViewHolder(binding.root)
	{
		fun bind(model : BatikEntity)
		{
			
			var height = 900
			if (position % 2 == 1)
			{
				height = 450
			}
			
			binding.blurry.setupWith(binding.root)
				.setBlurAlgorithm(RenderScriptBlur(itemView.context))
				.setBlurRadius(20f)
				.setBlurAutoUpdate(true)
				.setHasFixedTransformationMatrix(true)
			
			Glide.with(itemView.context)
				.load(model.Image)
				.apply(RequestOptions().override(200 , height))
				.into(binding.ivItemBatik)
			binding.tvItemNameBatik.text = model.name_batik
			binding.tvItemLocationBatik.text = model.batik_id?.toString()
			
			binding.cvBatik.setOnClickListener {
				val intent = Intent(itemView.context , DetailBatikActivity::class.java)
				callBack.itemBatikClick(model)
				itemView.context.startActivity(intent)
			}
		}
	}
	
	
}