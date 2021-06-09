package com.b21cap0051.naratik.view

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.adapter.BatikMiniListAdapter
import com.b21cap0051.naratik.adapter.StoreAdapter
import com.b21cap0051.naratik.databinding.ActivityDetailBatikBinding
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.viewmodel.FavoriteMainView
import com.b21cap0051.naratik.viewmodel.ViewFactoryModel
import com.b21cap0051.naratik.util.DataDummy
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailBatikActivity : AppCompatActivity()
{
	private lateinit var binding : ActivityDetailBatikBinding
	private lateinit var batikMiniAdapter : BatikMiniListAdapter
	private lateinit var mainView : FavoriteMainView
	private lateinit var storeAdapter : StoreAdapter
	
	companion object
	{
		const val EXTRA_BATIK = "extra_batik"
		const val EXTRA_RESULT = "extra_result"
	}
	
	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivityDetailBatikBinding.inflate(layoutInflater)
		setContentView(binding.root)
		val batik = intent.getParcelableExtra<BatikEntity>(EXTRA_BATIK) as BatikEntity
		val factory = ViewFactoryModel.GetInstance(this)
		mainView = ViewModelProvider(this , factory)[FavoriteMainView::class.java]
		batikMiniAdapter = BatikMiniListAdapter()
		if (isLiked(batik))
		{
			binding.IsFavoriteButton.setImageResource(R.drawable.ic_favorite_clicked)
		}
		else{
			
			binding.IsFavoriteButton.setImageResource(R.drawable.ic_favorite_outlined)
		}
		loadDetail(batik)
		loadActionBar()
		loadViewMore()
		loadStore()
//		loadResultDetail()
		
		binding.IsFavoriteButton.setOnClickListener {
			if (isLiked(batik))
			{
				batik.favorite_batik = 0
				binding.IsFavoriteButton.setImageResource(R.drawable.ic_favorite_outlined)
				mainView.setFavorite(batik)
				Toast.makeText(this , "${batik.name_batik} UnFavourite!" , Toast.LENGTH_SHORT)
					.show()
			} else
			{
				batik.favorite_batik = 1
				binding.IsFavoriteButton.setImageResource(R.drawable.ic_favorite_clicked)
				mainView.setFavorite(batik)
				Toast.makeText(this , "${batik.name_batik} Favourite!" , Toast.LENGTH_SHORT)
					.show()
			}
		}
		
	}
	
	private fun loadViewMore()
	{
		val listBatik = DataDummy.generateDummyBatik()
		batikMiniAdapter.setList(listBatik)
		binding.rvVmBatik.layoutManager =
			LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false)
		binding.rvVmBatik.adapter = batikMiniAdapter
		
	}
	
	private fun loadDetail(model : BatikEntity)
	{
		
		binding.collapsingToolbar.title = model.name_batik
		binding.tvItemLocationBatik.text = model.daerah_batik
		binding.tvMeaning.text = model.makna_batik
		Glide.with(this)
			.load(model.Image)
			.apply(RequestOptions().override(500 , 500))
			.apply(
				RequestOptions.placeholderOf(R.drawable.ic_loading)
					.error(R.drawable.ic_error)
			      )
			.into(binding.ivBatik)
		
	}
	
	private fun loadActionBar()
	{
		val btnBack : Button = findViewById(R.id.btnBack)
		btnBack.setOnClickListener {
			super.onBackPressed()
		}
	}
	
	private fun isLiked(model : BatikEntity) : Boolean = model.favorite_batik == 1
	
	
	private fun loadResultDetail()
	{
		binding.collapsingToolbar.title = EXTRA_RESULT
	}
	

	
	private fun loadStore()
	{
		binding.txtNoResult.visibility = View.GONE
		storeAdapter = StoreAdapter()
		binding.rvEcommerce.layoutManager = LinearLayoutManager(
			this ,
			LinearLayoutManager.HORIZONTAL ,
			false
		                                                       )
		binding.rvEcommerce.adapter = storeAdapter
		val listStore = DataDummy.generateDummyStore()
		storeAdapter.setList(listStore)
	}
	
	
}
