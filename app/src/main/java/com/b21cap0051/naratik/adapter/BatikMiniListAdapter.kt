package com.b21cap0051.naratik.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.databinding.ItemRowBatikMiniBinding
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.ui.DetailBatikActivity
import com.b21cap0051.naratik.util.ItemBatikCallBack
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import eightbitlab.com.blurview.RenderScriptBlur
import java.util.*

class BatikMiniListAdapter(private val callBack : ItemBatikCallBack) :
	RecyclerView.Adapter<BatikMiniListAdapter.ItemTarget>()
{
	
	private val listBatik = ArrayList<BatikEntity>()
	
	fun setList(listBatik : ArrayList<BatikEntity>)
	{
		this.listBatik.clear()
		this.listBatik.addAll(listBatik)
		notifyDataSetChanged()
	}
	
	inner class ItemTarget(private val binding : ItemRowBatikMiniBinding) :
		RecyclerView.ViewHolder(binding.root)
	{
		fun bind(model : BatikEntity)
		{
			
			binding.blurry.setupWith(binding.root)
				.setBlurAlgorithm(RenderScriptBlur(itemView.context))
				.setBlurRadius(20f)
				.setBlurAutoUpdate(true)
				.setHasFixedTransformationMatrix(true)
			
			Glide.with(itemView.context)
				.load(model.Image)
				.apply(RequestOptions().override(200 , 200))
				.into(binding.ivItemBatik)
			binding.tvItemNameBatik.text = model.name_batik
			binding.tvItemLocationBatik.text = itemView.resources.getString(R.string.batik_id, model.batik_id)
			
//			binding.cvMiniBatik.setOnClickListener {
//				callBack.itemBatikClick(model)
//			}
		}
		
	}
	
	override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : ItemTarget
	{
		return ItemTarget(
			ItemRowBatikMiniBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
		                 )
	}
	
	override fun onBindViewHolder(holder : ItemTarget , position : Int)
	{
		holder.bind(listBatik[position])
//		holder.itemView.setOnClickListener {
//			val intent = Intent(holder.itemView.context , DetailBatikActivity::class.java)
//            intent.putExtra(DetailBatikActivity.EXTRA_BATIK,mo)
//			holder.itemView.context.startActivity(intent)
//		}
	}
	
	override fun getItemCount() : Int
	{
		return listBatik.size
	}
	
	
}



