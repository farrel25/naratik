package com.b21cap0051.naratik.ui.favourite.favouritefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.b21cap0051.naratik.adapter.BatikPagedListAdapter
import com.b21cap0051.naratik.adapter.FavoriteBatikListAdapter
import com.b21cap0051.naratik.databinding.FragmentBatikFavouriteBinding
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.util.DataDummy
import com.b21cap0051.naratik.util.ItemBatikCallBack


class BatikFavouriteFragment : Fragment(),ItemBatikCallBack
{
	
	private var _binding : FragmentBatikFavouriteBinding? = null
	private val binding get() = _binding!!
	private lateinit var favoriteBatikListAdapter : FavoriteBatikListAdapter
	
	
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
		
		favoriteBatikListAdapter = FavoriteBatikListAdapter(this)
		val listBatik = DataDummy.generateDummyBatik()
		favoriteBatikListAdapter.setList(listBatik)
		binding.rvFavBatik.layoutManager = LinearLayoutManager(requireActivity() , LinearLayoutManager.VERTICAL , false)
		binding.rvFavBatik.adapter = favoriteBatikListAdapter
	}
	
	companion object
	{
	
	}
	
	override fun itemBatikClick(model : BatikEntity)
	{
	}
}