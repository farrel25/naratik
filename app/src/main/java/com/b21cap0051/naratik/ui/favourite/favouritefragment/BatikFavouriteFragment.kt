package com.b21cap0051.naratik.ui.favourite.favouritefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.b21cap0051.naratik.adapter.BatikFavoriteAdapter
import com.b21cap0051.naratik.adapter.BatikListAdapter
import com.b21cap0051.naratik.databinding.FragmentBatikFavouriteBinding
import com.b21cap0051.naratik.mainview.FavoriteMainView
import com.b21cap0051.naratik.mainview.ViewFactoryModel
import com.b21cap0051.naratik.util.vo.Status


class BatikFavouriteFragment : Fragment()
{
	
	private var _binding : FragmentBatikFavouriteBinding? = null
	private val binding get() = _binding!!
	private lateinit var favoriteBatikListAdapter : BatikFavoriteAdapter
	private lateinit var mainMainView : FavoriteMainView
	
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
		mainMainView = ViewModelProvider(requireActivity() , factory)[FavoriteMainView::class.java]
		favoriteBatikListAdapter = BatikFavoriteAdapter(requireContext(),mainMainView)
		binding.rvFavBatik.layoutManager =
			LinearLayoutManager(requireActivity() , LinearLayoutManager.VERTICAL , false)
		mainMainView.getAllFavourite().observe(viewLifecycleOwner , { response ->
			when (response.Status)
			{
				Status.SUCCESS ->
				{
					binding.rvFavBatik.adapter = favoriteBatikListAdapter
					favoriteBatikListAdapter.setList(response.Data!!)
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
	
}