package com.naratik.batikapps.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.naratik.batikapps.R
import com.naratik.batikapps.core.local.model.BatikEntity
import com.naratik.batikapps.databinding.ItemRowBatikBinding
import com.naratik.batikapps.ui.view.DetailBatikActivity
import com.naratik.batikapps.ui.viewmodel.BatikMainView
import eightbitlab.com.blurview.RenderScriptBlur

class BatikListAdapter(
	private val ctx : Context ,
	private val mainVIew : BatikMainView
                      ) :
	RecyclerView.Adapter<BatikListAdapter.ItemTarget>()
{
	inner class ItemTarget(private val binding : ItemRowBatikBinding) :
		RecyclerView.ViewHolder(binding.root)
	{
		
		private fun checkFavourite(model : BatikEntity) : Boolean
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
			var height = 900
			if (layoutPosition % 2 == 1)
			{
				height = 450
			}
			binding.cvBatik.layoutParams.height = height
			
			binding.blurry.setupWith(binding.root)
				.setBlurAlgorithm(RenderScriptBlur(itemView.context))
				.setBlurRadius(10f)
				.setBlurAutoUpdate(true)
				.setHasFixedTransformationMatrix(true)
			
			Glide.with(itemView.context)
				.load(model.Image)
				.apply(RequestOptions().override(450 , height))
				.apply(
					RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
				      )
				.into(binding.ivItemBatik)
			binding.tvItemNameBatik.text = model.name_batik
			binding.tvItemLocationBatik.text =
				itemView.resources.getString(R.string.batik_id , model.batik_id)
			
			
			binding.btnItemFavBatik.setOnClickListener {
				if (checkFavourite(model))
				{
					model.favorite_batik = 0
					binding.btnItemFavBatik.setIconTintResource(R.color.white)
					mainVIew.setFavorite(model)
					Toast.makeText(ctx , "${model.name_batik} UnFavourite!" , Toast.LENGTH_SHORT)
						.show()
				} else
				{
					model.favorite_batik = 1
					binding.btnItemFavBatik.setIconTintResource(R.color.red)
					mainVIew.setFavorite(model)
					Toast.makeText(ctx , "${model.name_batik} Favourite!" , Toast.LENGTH_SHORT)
						.show()
				}
				
				
			}
			itemView.setOnClickListener {
				val intent = Intent(itemView.context , DetailBatikActivity::class.java)
				intent.putExtra(DetailBatikActivity.EXTRA_BATIK , model)
				ctx.startActivity(intent)
			}
			
		}
	}
	
	private val batikList = ArrayList<BatikEntity>()
	
	fun setList(model : List<BatikEntity>)
	{
		batikList.clear()
		batikList.addAll(model)
		notifyDataSetChanged()
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
		holder.bind(batikList[position])
	}
	
	override fun getItemCount() : Int = batikList.size
	
	
}