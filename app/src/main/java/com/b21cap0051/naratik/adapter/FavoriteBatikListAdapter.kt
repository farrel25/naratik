package com.b21cap0051.naratik.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.databinding.ItemRowBatikBinding
import com.b21cap0051.naratik.databinding.ItemRowBatikFavoriteBinding
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.ui.DetailBatikActivity
import com.b21cap0051.naratik.util.ItemBatikCallBack
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import eightbitlab.com.blurview.RenderScriptBlur
import java.util.*

class FavoriteBatikListAdapter(private val callBack : ItemBatikCallBack) :
	RecyclerView.Adapter<FavoriteBatikListAdapter.ItemTarget>()
{
	
	private val listBatik = ArrayList<BatikEntity>()
	
	fun setList(listBatik : List<BatikEntity>)
	{
		this.listBatik.clear()
		this.listBatik.addAll(listBatik)
		notifyDataSetChanged()
	}
	
	inner class ItemTarget(private val binding : ItemRowBatikFavoriteBinding) :
		RecyclerView.ViewHolder(binding.root)
	{
		fun bind(model : BatikEntity)
		{
			binding.tvItemIdFavBatik.text = model.name_batik
			binding.tvItemIdFavBatik.text =
				itemView.resources.getString(R.string.batik_id , model.batik_id)
			
			Glide.with(itemView.context)
				.load(model.Image)
				.apply(RequestOptions().override(200 , 200))
				.apply(
					RequestOptions.placeholderOf(R.drawable.ic_loading)
						.error(R.drawable.ic_error)
				      )
				.into(binding.ivItemFavBatik)
			
			binding.cvFavoriteBatik.setOnClickListener {
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
			ItemRowBatikFavoriteBinding.inflate(
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



