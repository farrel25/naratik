package com.b21cap0051.naratik.ui.home.homefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.adapter.ArticleListAdapter
import com.b21cap0051.naratik.adapter.BatikListAdapter
import com.b21cap0051.naratik.databinding.FragmentExploreBinding
import com.b21cap0051.naratik.model.ArticleModel
import com.b21cap0051.naratik.model.BatikModel
import com.b21cap0051.naratik.util.ItemBatikCallBack


class ExploreFragment : Fragment(),ItemBatikCallBack {

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding as FragmentExploreBinding
    private var list: ArrayList<ArticleModel> = arrayListOf()
    private lateinit var adapterArticle: ArticleListAdapter
    private lateinit var adapterBatik: BatikListAdapter

    companion object {
        val TAG: String = ExploreFragment::class.java.simpleName
        const val EXTRA_ARTICLE = "extra_article"
        const val EXTRA_BATIK = "extra_batik"
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

        adapterArticle = ArticleListAdapter(
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

        val listBatik = arrayListOf(
            BatikModel(
                getString(R.string.article_id),
                R.drawable.img_dummy,
                getString(R.string.batik_name),
                getString(R.string.batik_meaning),
            ),
            BatikModel(
                getString(R.string.article_id),
                R.drawable.img_dummy,
                getString(R.string.batik_name),
                getString(R.string.batik_meaning),
            ),
            BatikModel(
                getString(R.string.article_id),
                R.drawable.img_dummy,
                getString(R.string.batik_name),
                getString(R.string.batik_meaning),
            ),
            BatikModel(
                getString(R.string.article_id),
                R.drawable.img_dummy,
                getString(R.string.batik_name),
                getString(R.string.batik_meaning),
            )
        )

        adapterBatik = BatikListAdapter(
            this
        )

        binding.rvArticle.layoutManager = LinearLayoutManager(activity)
        binding.rvArticle.adapter = adapterArticle

        binding.rvBatik.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvBatik.adapter = adapterBatik
        adapterBatik.setList(listBatik)




    }

    override fun itemBatikClick(model: BatikModel) {

    }
}