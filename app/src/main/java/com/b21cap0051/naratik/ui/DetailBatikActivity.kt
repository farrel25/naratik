package com.b21cap0051.naratik.ui

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.adapter.BatikMiniListAdapter
import com.b21cap0051.naratik.databinding.ActivityDetailBatikBinding
import com.b21cap0051.naratik.databinding.ItemRowBatikBinding
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.mainview.FavoriteMainView
import com.b21cap0051.naratik.mainview.ViewFactoryModel
import com.b21cap0051.naratik.util.DataDummy
import com.b21cap0051.naratik.util.ItemBatikCallBack
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailBatikActivity : AppCompatActivity() , ItemBatikCallBack
{
	private lateinit var binding : ActivityDetailBatikBinding
	private lateinit var batikMiniAdapter : BatikMiniListAdapter
	private lateinit var mainView : FavoriteMainView
	
	companion object
	{
		const val EXTRA_BATIK = "extra_batik"
	}
	
	override fun onCreate(savedInstanceState : Bundle?)
	{
		super.onCreate(savedInstanceState)
		binding = ActivityDetailBatikBinding.inflate(layoutInflater)
		setContentView(binding.root)
		
		val factory = ViewFactoryModel.GetInstance(this)
		mainView = ViewModelProvider(this , factory)[FavoriteMainView::class.java]
		
		batikMiniAdapter = BatikMiniListAdapter(this)
		
		loadActionBar()
		loadDetail()
		loadViewMore()
		
	}
	
	private fun loadViewMore()
	{
		val listBatik = DataDummy.generateDummyBatik()
		batikMiniAdapter.setList(listBatik)
		binding.rvVmBatik.layoutManager =
			LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false)
		binding.rvVmBatik.adapter = batikMiniAdapter
		
	}
	
	private fun loadDetail()
	{
		val batik = intent.getParcelableExtra<BatikEntity>(EXTRA_BATIK) as BatikEntity
		binding.collapsingToolbar.title = batik.name_batik
		binding.tvItemLocationBatik.text = batik.daerah_batik
		binding.tvMeaning.text = batik.makna_batik
		Glide.with(this)
			.load(batik.Image)
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
		btnBack.setOnClickListener() {
			super.onBackPressed()
		}
	}
	
	override fun itemBatikClick(model : BatikEntity)
	{
	
	}
	
	override fun AddFavour(v : ItemRowBatikBinding , model : BatikEntity)
	{
		if(CheckIsFavor(model)){
			val modelbaru = BatikEntity(
				model.batik_id,
				model.name_batik,
				model.makna_batik,
				model.Image,
				model.daerah_batik,
				0
			                           )
			mainView.addFavor(modelbaru)
			v.btnItemFavBatik.setIconResource(R.drawable.ic_love_outlined)
		}else{
			val modelbaru = BatikEntity(
				model.batik_id,
				model.name_batik,
				model.makna_batik,
				model.Image,
				model.daerah_batik,
				1
			                           )
			mainView.addFavor(modelbaru)
			v.btnItemFavBatik.setIconResource(R.drawable.ic_love_filled)
		}
	}
	
	override fun CheckIsFavor(model : BatikEntity) : Boolean
	{
		var stat = false
		mainView.checkFavorite().observe(this , {
			for (i in 0 until it.size)
			{
				if (model.batik_id == it[i].batik_id)
				{
					stat = true
				}
			}
		})
		return stat
	}
}
