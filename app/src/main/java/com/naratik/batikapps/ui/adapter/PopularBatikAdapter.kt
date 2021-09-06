package com.naratik.batikapps.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.naratik.batikapps.R
import com.naratik.batikapps.core.local.model.BatikEntity
import com.naratik.batikapps.core.local.model.PopularBatikEntity
import com.naratik.batikapps.databinding.ItemRowBatikBinding
import com.naratik.batikapps.ui.view.DetailBatikActivity
import com.naratik.batikapps.ui.viewmodel.BatikMainView
import eightbitlab.com.blurview.RenderScriptBlur

class PopularBatikAdapter(
	private val ctx : Context ,
	private val life : LifecycleOwner ,
	private val mainView : BatikMainView
                         ) : RecyclerView.Adapter<PopularBatikAdapter.Itemtarget>()
{
	
	private val BatikList = ArrayList<PopularBatikEntity>()
	
	fun setList(model : List<PopularBatikEntity>)
	{
		BatikList.clear()
		BatikList.addAll(model)
		notifyDataSetChanged()
	}
	
	
	inner class Itemtarget(private val binding : ItemRowBatikBinding) :
		RecyclerView.ViewHolder(binding.root)
	{
		fun checkFavourite(model : PopularBatikEntity) : Boolean
		{
			var stat = false
			mainView.getAllFavoriteDb().observe(life , { response ->
//				when (response.Status)
//				{
//					SUCCESS ->
//					{
//						val data = response.Data
//						for (i in 0 until data?.size!!)
//						{
//							if (model.batik_id == data[i].batik_id)
//							{
//								stat = true
//								break
//							}
//						}
//					}
//					LOADING ->
//					{
//
//					}
//					ERROR   ->
//					{
//
//
//					}
//				}
			})
			return stat
		}
		
		fun bind(model : PopularBatikEntity)
		{
			
			val dataModel = BatikEntity(
				model.batik_id ,
				model.name_batik ,
				model.makna_batik ,
				model.Image ,
				model.daerah_batik ,
				model.favorite_batik
			                           )
			
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
					dataModel.favorite_batik = 0
					binding.btnItemFavBatik.setIconTintResource(R.color.white)
					mainView.setFavorite(dataModel)
					Toast.makeText(
						ctx ,
						"${dataModel.name_batik} UnFavourite!" ,
						Toast.LENGTH_SHORT
					              )
						.show()
				} else
				{
					dataModel.favorite_batik = 1
					binding.btnItemFavBatik.setIconTintResource(R.color.red)
					mainView.setFavorite(dataModel)
					Toast.makeText(ctx , "${dataModel.name_batik} Favourite!" , Toast.LENGTH_SHORT)
						.show()
				}
				
				binding.cvBatik.setOnClickListener {
					val intent = Intent(itemView.context , DetailBatikActivity::class.java)
					intent.putExtra(DetailBatikActivity.EXTRA_BATIK , dataModel)
					ctx.startActivity(intent)
				}
			}
		}
	}
	
	
	override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : Itemtarget
	{
		return Itemtarget(
			ItemRowBatikBinding.inflate(
				LayoutInflater.from(parent.context) ,
				parent ,
				false
			                           )
		                 )
	}
	
	override fun onBindViewHolder(holder : Itemtarget , position : Int)
	{
		holder.bind(BatikList[position])
	}
	
	override fun getItemCount() : Int = BatikList.size
	
}