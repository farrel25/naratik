package com.b21cap0051.naratik.ui.favourite.favouritefragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.adapter.BatikPagedListAdapter
import com.b21cap0051.naratik.databinding.FragmentBatikFavouriteBinding
import com.b21cap0051.naratik.databinding.ItemRowBatikBinding
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.mainview.FavoriteMainView
import com.b21cap0051.naratik.mainview.ViewFactoryModel
import com.b21cap0051.naratik.ui.DetailBatikActivity
import com.b21cap0051.naratik.ui.DetailBatikActivity.Companion.EXTRA_BATIK
import com.b21cap0051.naratik.util.ItemBatikCallBack
import com.b21cap0051.naratik.util.vo.Status


class BatikFavouriteFragment : Fragment() , ItemBatikCallBack
{
	
	private var _binding : FragmentBatikFavouriteBinding? = null
	private val binding get() = _binding!!
	private lateinit var favoriteBatikListAdapter : BatikPagedListAdapter
	private lateinit var mainView : FavoriteMainView
	
	override fun onCreateView(
		inflater : LayoutInflater , container : ViewGroup? ,
		savedInstanceState : Bundle?
	                         ) : View
	{
		_binding = FragmentBatikFavouriteBinding.inflate(layoutInflater , container , false)
		return binding.root
	}
	

	
	override fun onViewCreated(view : View , savedInstanceState : Bundle?)
	{
		super.onViewCreated(view , savedInstanceState)
		
		val factory = ViewFactoryModel.GetInstance(requireContext())
		mainView = ViewModelProvider(requireActivity() , factory)[FavoriteMainView::class.java]
		favoriteBatikListAdapter = BatikPagedListAdapter(this)
		binding.rvFavBatik.layoutManager =
			LinearLayoutManager(requireActivity() , LinearLayoutManager.VERTICAL , false)
		mainView.getFavourite().observe(viewLifecycleOwner , { response ->
			when (response.Status)
			{
				Status.SUCCESS ->
				{
					binding.rvFavBatik.adapter = favoriteBatikListAdapter
					favoriteBatikListAdapter.submitList(response.Data)
				}
				Status.LOADING -> {
				
				}
				Status.ERROR -> {
				
				}
			}
		})
		
		
		
		
		
	
	}
	
	companion object
	{
	
	}
	
	override fun itemBatikClick(model : BatikEntity)
	{
		val intent = Intent(requireContext(),DetailBatikActivity::class.java)
		intent.putExtra(EXTRA_BATIK,model)
		startActivity(intent)
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
		mainView.checkFavorite().observe(viewLifecycleOwner,{
			for (element in it){
				if(model.batik_id == element.batik_id){
					stat = true
				}
			}
		})
		return stat
	}
}