package com.b21cap0051.naratik.ui.home.homefragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.b21cap0051.naratik.adapter.*
import com.b21cap0051.naratik.databinding.FragmentStoreBinding
import com.b21cap0051.naratik.dataresource.local.model.ProductEntity
import com.b21cap0051.naratik.dataresource.local.model.StoreEntity
import com.b21cap0051.naratik.util.*
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel


class StoreFragment : Fragment() , ItemProductCallback , ItemStoreCallback
{
	
	private var _binding : FragmentStoreBinding? = null
	private val binding get() = _binding as FragmentStoreBinding
	private lateinit var productAdapter : ProductAdapter
	private lateinit var storeAdapter : StoreAdapter
	
	override fun onCreateView(
		inflater : LayoutInflater ,
		container : ViewGroup? ,
		savedInstanceState : Bundle?
	                         ) : View
	{
		_binding = FragmentStoreBinding.inflate(layoutInflater , container , false)
		return binding.root
	}
	
	override fun onViewCreated(view : View , savedInstanceState : Bundle?)
	{
		super.onViewCreated(view , savedInstanceState)
		val slideModel : ArrayList<SlideModel> = arrayListOf()
		
		loadStore()
		loadProduct()
		
		slideModel.add(SlideModel("https://picsum.photos/id/237/700/700"))
		slideModel.add(SlideModel("https://picsum.photos/id/238/700/700"))
		slideModel.add(SlideModel("https://picsum.photos/id/231/700/700"))
		slideModel.add(SlideModel("https://picsum.photos/id/232/700/700"))
		
		binding.isEc.setImageList(slideModel , ScaleTypes.CENTER_CROP)
		
	}
	
	private fun loadStore()
	{
		storeAdapter = StoreAdapter(this)
		binding.rvStore.layoutManager = LinearLayoutManager(
			requireActivity() ,
			LinearLayoutManager.HORIZONTAL ,
			false
		                                                         )
		binding.rvStore.adapter = storeAdapter
		val listStore = DataDummy.generateDummyStore()
		storeAdapter.setList(listStore)
	}
	
	private fun loadProduct()
	{
		productAdapter = ProductAdapter(this)
		binding.rvProduct.layoutManager = LinearLayoutManager(
			requireActivity() ,
			LinearLayoutManager.HORIZONTAL ,
			false
		                                                                  )
		binding.rvProduct.adapter = productAdapter
		val listProduct = DataDummy.generateDummyProduct()
		productAdapter.setList(listProduct)
	}
	
	companion object
	{
	
	}
	
	override fun itemProductClick(model : ProductEntity)
	{
	}
	
	override fun itemStoreClick(model : StoreEntity)
	{
	}
}