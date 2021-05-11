package com.b21cap0051.naratik.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.b21cap0051.naratik.databinding.ItemRowArticleBinding
import com.b21cap0051.naratik.model.ArticleModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class ArticleListAdapter(private val listArticle: List<ArticleModel>) : RecyclerView.Adapter<ArticleListAdapter.ArticleListViewHolder>() {


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
                btnFavArticle.setOnClickListener {
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleListViewHolder {
        val binding = ItemRowArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleListViewHolder, position: Int) {
        holder.bind(listArticle[position])
    }

    override fun getItemCount(): Int = listArticle.size
}
