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
	
	private val ListHistory = ArrayList<HistoryEntity>()
	
	
	fun setList(model : List<HistoryEntity>)
	{
		if (model != null)
		{
			ListHistory.clear()
			ListHistory.addAll(model)
			notifyDataSetChanged()
		} else
		{
			ListHistory.clear()
			notifyDataSetChanged()
		}
	}
	
	fun removeItem(position : Int)
	{
		this.ListHistory.removeAt(position)
		notifyItemRemoved(position)
		notifyItemRangeChanged(position , this.ListHistory.size)
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
		holder.bind(ListHistory[position])
		callback.getPosition(position)
	}
	
	override fun getItemCount() : Int = ListHistory.size
	
	
}
