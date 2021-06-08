package com.b21cap0051.naratik.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.databinding.ItemRowBatikFavoriteBinding
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.mainview.FavoriteMainView
import com.b21cap0051.naratik.ui.DetailBatikActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class BatikFavoriteAdapter(private val ctx : Context , private val mainView : FavoriteMainView) :
	RecyclerView.Adapter<BatikFavoriteAdapter.ItemTarget>()
{
	
	
	private val listFavorite = ArrayList<BatikEntity>()
	
	
	fun setList(model : List<BatikEntity>)
	{
		listFavorite.clear()
		listFavorite.addAll(model)
		notifyDataSetChanged()
	}
	
	fun removeItem(position : Int)
	{
		this.listFavorite.removeAt(position)
		notifyItemRemoved(position)
		notifyItemRangeChanged(position , this.listFavorite.size)
	}
	
	
	inner class ItemTarget(private val binding : ItemRowBatikFavoriteBinding) :
		RecyclerView.ViewHolder(binding.root)
	{
		fun checkFavourite(model : BatikEntity) : Boolean
		{
			if (model.favorite_batik == 1)
			{
				return true
			}
			return false
		}
		
		fun bind(model : BatikEntity)
		{
			if (checkFavourite(model))
			{
				binding.btnItemFavBatik.setIconTintResource(R.color.red)
			} else
			{
				binding.btnItemFavBatik.setIconTintResource(R.color.white)
			}
			
			
			Glide.with(itemView.context)
				.load(model.Image)
				.apply(RequestOptions().override(120 , 120))
				.apply(
					RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
				      )
				.into(binding.ivItemFavBatik)
			binding.tvItemNameFavBatik.text = model.name_batik
			binding.tvItemIdFavBatik.text =
				itemView.resources.getString(R.string.batik_id , model.batik_id)
			
			
			binding.btnItemFavBatik.setOnClickListener {
				if (checkFavourite(model))
				{
					model.favorite_batik = 0
					binding.btnItemFavBatik.setIconTintResource(R.color.white)
					mainView.setFavorite(model)
					Toast.makeText(ctx , "${model.name_batik} UnFavourite!" , Toast.LENGTH_SHORT)
						.show()
				} else
				{
					model.favorite_batik = 1
					binding.btnItemFavBatik.setIconTintResource(R.color.red)
					mainView.setFavorite(model)
					Toast.makeText(ctx , "${model.name_batik} Favourite!" , Toast.LENGTH_SHORT)
						.show()
				}
				
				binding.cvFavoriteBatik.setOnClickListener {
					val intent = Intent(itemView.context , DetailBatikActivity::class.java)
					intent.putExtra(DetailBatikActivity.EXTRA_BATIK , model)
					ctx.startActivity(intent)
				}
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
		holder.bind(listFavorite[position])
	}
	
	override fun getItemCount() : Int = listFavorite.size
}