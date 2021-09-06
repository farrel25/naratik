package com.naratik.batikapps.ui.view.favourite.favouritefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.naratik.batikapps.databinding.FragmentBatikFavouriteBinding
import com.naratik.batikapps.ui.adapter.BatikFavoriteAdapter
import com.naratik.batikapps.ui.viewmodel.FavoriteMainView
import com.naratik.batikapps.util.ResultStateData
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BatikFavouriteFragment : Fragment()
{
	
	private var _binding : FragmentBatikFavouriteBinding? = null
	private val binding get() = _binding!!
	private lateinit var favoriteBatikListAdapter : BatikFavoriteAdapter
	private val mainMainView : FavoriteMainView by viewModels()
	
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
		
		favoriteBatikListAdapter = BatikFavoriteAdapter(requireContext() , mainMainView)
		binding.rvFavBatik.layoutManager =
			LinearLayoutManager(requireActivity() , LinearLayoutManager.VERTICAL , false)
		mainMainView.getAllFavourite().observe(viewLifecycleOwner , {
			when (it)
			{
				is ResultStateData.Success ->
				{
					binding.rvFavBatik.adapter = favoriteBatikListAdapter
					if(it.data.isNotEmpty()){
						favoriteBatikListAdapter.setList(it.data)
					}
					
				}
				is ResultStateData.Failure ->
				{

				}
				is ResultStateData.Loading   ->
				{

				}
			}
		})
	}
	
	
}