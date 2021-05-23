package com.b21cap0051.naratik.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.databinding.ItemRowResultBinding
import com.b21cap0051.naratik.dataresource.datamodellist.ResultModel
import com.b21cap0051.naratik.util.ItemResultCallback

class ResultMotifAdapter(private val callback: ItemResultCallback) :
	RecyclerView.Adapter<ResultMotifAdapter.ItemTarget>() {
	
	private val listMotifResult = ArrayList<ResultModel>()
	
	fun setList(listMotifResult : java.util.ArrayList<ResultModel>) {
		this.listMotifResult.clear()
		this.listMotifResult.addAll(listMotifResult)
		notifyDataSetChanged()
	}
	
	inner class ItemTarget(private val binding: ItemRowResultBinding) : RecyclerView.ViewHolder(binding.root) {
		
		fun bind(motif: ResultModel) {
			with(binding) {
				tvItemMotifResult.text = motif.motif
				tvItemPercentageResult.text = itemView.resources.getString(R.string.presentase,motif.percentage.toString())
			}
		}
	}
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTarget {
		val binding = ItemRowResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return ItemTarget(binding)
	}
	
	override fun onBindViewHolder(holder: ItemTarget, position: Int) {
		holder.bind(listMotifResult[position])
		
		holder.itemView.setOnClickListener{
//			val intent =Intent(holder.itemView.context, DetailArticleActivity::class.java)
//			intent.putExtra(DetailArticleActivity.EXTRA_ARTICLE,listArticle[position])
//			holder.itemView.context.startActivity(intent)
		}
	}
	
	override fun getItemCount(): Int = listMotifResult.size
}
