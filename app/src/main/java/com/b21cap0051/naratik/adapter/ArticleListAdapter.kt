package com.b21cap0051.naratik.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.b21cap0051.naratik.databinding.ItemRowArticleBinding
import com.b21cap0051.naratik.dataresource.datamodellist.ArticleModel
import com.b21cap0051.naratik.ui.DetailArticleActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class ArticleListAdapter(private val listArticle: ArrayList<ArticleModel>) : RecyclerView.Adapter<ArticleListAdapter.ArticleListViewHolder>() {


    inner class ArticleListViewHolder(private val binding: ItemRowArticleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ArticleModel) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(article.image)
                    .transform(RoundedCorners(20))
                    .into(ivArticle)
                tvDateArticle.text = article.date
                tvTitleArticle.text = article.title
                tvWriterArticle.text = article.writer

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleListViewHolder {
        val binding = ItemRowArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleListViewHolder, position: Int) {
        holder.bind(listArticle[position])

        holder.itemView.setOnClickListener{
            val intent =Intent(holder.itemView.context, DetailArticleActivity::class.java)

            intent.putExtra(DetailArticleActivity.EXTRA_ARTICLE,listArticle[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listArticle.size
}
