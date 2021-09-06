package com.naratik.batikapps.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.naratik.batikapps.core.local.model.StoreEntity
import com.naratik.batikapps.databinding.ItemRowStoreBinding
import java.util.*

class StoreAdapter :
	RecyclerView.Adapter<StoreAdapter.ItemTarget>()
{
	
	private val listStore = ArrayList<StoreEntity>()
	
	fun setList(listStore : List<StoreEntity>)
	{
		this.listStore.clear()
		this.listStore.addAll(listStore)
		notifyDataSetChanged()
	}
	
	inner class ItemTarget(private val binding : ItemRowStoreBinding) :
		RecyclerView.ViewHolder(binding.root)
	{
		fun bind(model : StoreEntity)
		{
			Glide.with(itemView.context)
				.load(model.backdropImage)
				.apply(RequestOptions().override(200 , 200))
				.into(binding.ivItemImageBackdrop)
			Glide.with(itemView.context)
				.load(model.image)
				.apply(RequestOptions().override(200 , 200))
				.into(binding.ivItemStore)
			binding.tvItemNameStore.text = model.name
			val ratingHalf = model.rating / 2
			binding.rbItemStore.rating = ratingHalf.toFloat()
//			binding.tvItemRatingStore.text = model.rating.toString()

//			binding.cvBatik.setOnClickListener {
//				val intent = Intent(itemView.context , DetailBatikActivity::class.java)
////                intent.putExtra(DetailBatikActivity.,listBatik[position])
//				callBack.itemBatikClick(model)
//				itemView.context.startActivity(intent)
//			}
		}
		
	}
	
	override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : ItemTarget
	{
		return ItemTarget(
			ItemRowStoreBinding.inflate(
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



