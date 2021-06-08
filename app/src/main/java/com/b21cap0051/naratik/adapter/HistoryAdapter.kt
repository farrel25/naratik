package com.b21cap0051.naratik.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.b21cap0051.naratik.databinding.ItemRowHistoryBinding
import com.b21cap0051.naratik.dataresource.local.model.HistoryEntity
import com.b21cap0051.naratik.util.ItemCallbackHistory

class HistoryAdapter(private val callback : ItemCallbackHistory) :
	RecyclerView.Adapter<HistoryAdapter.ItemTarget>()
{
	
	private val listHistory = ArrayList<HistoryEntity>()
	
	
	fun setList(model : List<HistoryEntity>)
	{
		listHistory.clear()
		listHistory.addAll(model)
		notifyDataSetChanged()
	}
	
	fun removeItem(position : Int)
	{
		this.listHistory.removeAt(position)
		notifyItemRemoved(position)
		notifyItemRangeChanged(position , this.listHistory.size)
	}
	
	
	inner class ItemTarget(private val binding : ItemRowHistoryBinding) :
		RecyclerView.ViewHolder(binding.root)
	{
		fun bind(model : HistoryEntity)
		{
			
			binding.tvHistory.text = model.history
			
			binding.btnItemFavBatik.setOnClickListener {
				callback.getItem(model)
			}
		}
	}
	
	
	override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : ItemTarget =
		ItemTarget(
			ItemRowHistoryBinding.inflate(
				LayoutInflater.from(parent.context) ,
				parent ,
				false
			                             )
		          )
	
	override fun onBindViewHolder(holder : ItemTarget , position : Int)
	{
		holder.bind(listHistory[position])
		callback.getPosition(position)
	}
	
	override fun getItemCount() : Int = listHistory.size
	
	
}
