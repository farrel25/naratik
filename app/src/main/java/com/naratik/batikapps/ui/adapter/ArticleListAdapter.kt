package com.naratik.batikapps.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.naratik.batikapps.databinding.ItemRowArticleBinding
import com.naratik.batikapps.ui.adapter.modellist.ArticleModel
import com.naratik.batikapps.ui.view.DetailArticleActivity

class ArticleListAdapter() :
	RecyclerView.Adapter<ArticleListAdapter.ItemTarget>()
{
	
	private val listArticle = ArrayList<ArticleModel>()
	
	fun setList(listArticle : ArrayList<ArticleModel>)
	{
		this.listArticle.clear()
		this.listArticle.addAll(listArticle)
		notifyDataSetChanged()
	}
	
	inner class ItemTarget(private val binding : ItemRowArticleBinding) :
		RecyclerView.ViewHolder(binding.root)
	{
		fun bind(article : ArticleModel)
		{
			with(binding) {
				Glide.with(itemView.context)
					.load(article.image)
					.transform(CenterInside() , RoundedCorners(24))
					.into(ivItemArticle)
				
				tvItemDateArticle.text = article.date
				tvItemTitleArticle.text = article.title
				tvItemWriterArticle.text = article.writer
				
			}
		}
	}
	
	override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : ItemTarget
	{
		val binding =
			ItemRowArticleBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
		return ItemTarget(binding)
	}
	
	override fun onBindViewHolder(holder : ItemTarget , position : Int)
	{
		holder.bind(listArticle[position])
		
		holder.itemView.setOnClickListener {
			val intent = Intent(holder.itemView.context , DetailArticleActivity::class.java)
			intent.putExtra(DetailArticleActivity.EXTRA_ARTICLE , listArticle[position])
			holder.itemView.context.startActivity(intent)
		}
	}
	
	override fun getItemCount() : Int = listArticle.size
}
