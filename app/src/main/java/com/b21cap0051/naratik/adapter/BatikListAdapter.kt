package com.b21cap0051.naratik.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.b21cap0051.naratik.databinding.ItemRowBatikBinding
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.ui.DetailBatikActivity
import com.b21cap0051.naratik.util.ItemBatikCallBack
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import eightbitlab.com.blurview.RenderScriptBlur
import java.util.*

class BatikListAdapter(private val callBack : ItemBatikCallBack) :
	RecyclerView.Adapter<BatikListAdapter.ItemTarget>()
{
	
	private val listBatik = ArrayList<BatikEntity>()
	
	fun setList(listBatik : List<BatikEntity>)
	{
		this.listBatik.clear()
		this.listBatik.addAll(listBatik)
		notifyDataSetChanged()
	}
	
	fun setListLimited(listBatik : List<BatikEntity>)
	{
		this.listBatik.clear()
		for (i in 1 .. 4)
		{
			this.listBatik.addAll(listOf(listBatik[i]))
		}
		notifyDataSetChanged()
	}
	
	inner class ItemTarget(private val binding : ItemRowBatikBinding) :
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
//                intent.putExtra(DetailBatikActivity.,listBatik[position])
				callBack.itemBatikClick(model)
				itemView.context.startActivity(intent)
			}
		}
		
	}
	
	override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : ItemTarget
	{
		return ItemTarget(
			ItemRowBatikBinding.inflate(
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



