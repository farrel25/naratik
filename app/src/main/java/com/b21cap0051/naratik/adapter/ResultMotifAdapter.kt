package com.b21cap0051.naratik.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.databinding.ItemRowResultBinding
import com.b21cap0051.naratik.dataresource.remotedata.model.MotifResponseItem

class ResultMotifAdapter() :
	RecyclerView.Adapter<ResultMotifAdapter.ItemTarget>()
{
	
	private val listMotifResult = ArrayList<MotifResponseItem>()
	
	fun setList(listMotifResult : List<MotifResponseItem>)
	{
		this.listMotifResult.clear()
		this.listMotifResult.addAll(listMotifResult)
		notifyDataSetChanged()
	}
	
	inner class ItemTarget(private val binding : ItemRowResultBinding) :
		RecyclerView.ViewHolder(binding.root)
	{
		
		fun bind(motif : MotifResponseItem)
		{
			with(binding) {
				tvItemMotifResult.text = motif.motifName
				tvItemPercentageResult.text =
					itemView.resources.getString(R.string.presentase , motif.valueMotif.toString())
			}
		}
	}
	
	override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : ItemTarget
	{
		val binding =
			ItemRowResultBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
		return ItemTarget(binding)
	}
	
	override fun onBindViewHolder(holder : ItemTarget , position : Int)
	{
		holder.bind(listMotifResult[position])
		
		holder.itemView.setOnClickListener {
//			val intent = Intent(holder.itemView.context, DetailBatikActivity::class.java)
//			intent.putExtra(DetailBatikActivity.EXTRA_RESULT,listMotifResult[position].motifName)
//			holder.itemView.context.startActivity(intent)
		}
	}
	
	override fun getItemCount() : Int = listMotifResult.size
}
