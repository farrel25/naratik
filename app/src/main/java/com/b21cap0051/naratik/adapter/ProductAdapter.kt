package com.b21cap0051.naratik.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.b21cap0051.naratik.databinding.ItemRowProductBinding
import com.b21cap0051.naratik.dataresource.local.model.ProductEntity
import com.b21cap0051.naratik.util.ItemProductCallback
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.util.*

class ProductAdapter(private val callBack : ItemProductCallback) :
	RecyclerView.Adapter<ProductAdapter.ItemTarget>()
{
	
	private val listProduct = ArrayList<ProductEntity>()
	
	fun setList(listProduct : List<ProductEntity>)
	{
		this.listProduct.clear()
		this.listProduct.addAll(listProduct)
		notifyDataSetChanged()
	}
	
	inner class ItemTarget(private val binding : ItemRowProductBinding) :
		RecyclerView.ViewHolder(binding.root)
	{
		fun bind(model : ProductEntity)
		{
			Glide.with(itemView.context)
				.load(model.image)
				.apply(RequestOptions().override(200 , 200))
				.into(binding.ivItemProduct)
			binding.tvItemNameProduct.text = model.name
			binding.tvItemPrice.text = model.price
			binding.tvItemCategoryProduct.text = model.category
			val ratingHalf = model.rating / 2
			binding.rbItemProduct.rating = ratingHalf.toFloat()

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
			ItemRowProductBinding.inflate(
				LayoutInflater.from(parent.context) ,
				parent ,
				false
			                             )
		                 )
	}
	
	override fun onBindViewHolder(holder : ItemTarget , position : Int)
	{
		holder.bind(listProduct[position])
		
	}
	
	override fun getItemCount() : Int
	{
		return listProduct.size
	}
	
	
}



