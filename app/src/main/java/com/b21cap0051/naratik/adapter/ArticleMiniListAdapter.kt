package com.b21cap0051.naratik.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.b21cap0051.naratik.databinding.ItemRowArticleMiniBinding
import com.b21cap0051.naratik.dataresource.datamodellist.ArticleModel
import com.b21cap0051.naratik.ui.DetailArticleActivity
import com.b21cap0051.naratik.util.ItemArticleCallBack
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import java.util.*

class ArticleMiniListAdapter(private val callback : ItemArticleCallBack) :
	RecyclerView.Adapter<ArticleMiniListAdapter.ItemTarget>()
{
	
	private val listArticle = ArrayList<ArticleModel>()
	
	fun setList(listArticle : ArrayList<ArticleModel>)
	{
		this.listArticle.clear()
		this.listArticle.addAll(listArticle)
		notifyDataSetChanged()
	}
	
	inner class ItemTarget(private val binding : ItemRowArticleMiniBinding) :
		RecyclerView.ViewHolder(binding.root)
	{
		fun bind(article : ArticleModel)
		{
			with(binding) {
				Glide.with(itemView.context)
					.load(article.image)
					.transform(RoundedCorners(20))
					.into(ivItemArticle)
				tvItemTitleArticle.text = article.title
				tvItemWriterArticle.text = article.writer
				
			}
		}
	}
	
	override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : ItemTarget
	{
		val binding =
			ItemRowArticleMiniBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
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
