package com.b21cap0051.naratik.ui.home.homefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.adapter.ArticleListAdapter
import com.b21cap0051.naratik.databinding.FragmentExploreBinding
import com.b21cap0051.naratik.model.ArticleModel
import com.b21cap0051.naratik.model.OnBoardingModel


class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding as FragmentExploreBinding
    private var list: ArrayList<ArticleModel> = arrayListOf()
    private lateinit var adapter: ArticleListAdapter

    companion object {
        val TAG: String = ExploreFragment::class.java.simpleName
        const val EXTRA_ARTICLE = "extra_article"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExploreBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ArticleListAdapter(
            listOf(
                ArticleModel(
                    getString(R.string.article_id),
                    R.drawable.img_dummy,
                    getString(R.string.article_title),
                    getString(R.string.article_date),
                    getString(R.string.article_contributor),
                    getString(R.string.article_overview),
                ),
                ArticleModel(
                    getString(R.string.article_id),
                    R.drawable.img_dummy,
                    getString(R.string.article_title),
                    getString(R.string.article_date),
                    getString(R.string.article_contributor),
                    getString(R.string.article_overview),
                ),
                ArticleModel(
                    getString(R.string.article_id),
                    R.drawable.img_dummy,
                    getString(R.string.article_title),
                    getString(R.string.article_date),
                    getString(R.string.article_contributor),
                    getString(R.string.article_overview),
                )

            )
        )
        binding.rvArticle.layoutManager = LinearLayoutManager(activity)
        binding.rvArticle.adapter = adapter




    }
}