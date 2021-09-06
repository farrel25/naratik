package com.naratik.batikapps.ui.view.home.homefragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.naratik.batikapps.databinding.FragmentStoreBinding
import com.naratik.batikapps.ui.adapter.ProductAdapter
import com.naratik.batikapps.ui.adapter.StoreAdapter
import com.naratik.batikapps.ui.view.StoreActivity
import com.naratik.batikapps.util.dummydata.DataDummy
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreFragment : Fragment()
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
		
		slideModel.add(SlideModel("https://cdn-2.tstatic.net/tribunnews/foto/bank/images/promo-yang-ditawarkan-untuk-memperingati-hari-batik-nasional_20181002_125726.jpg"))
		slideModel.add(SlideModel("https://malangstrudel.com/wp-content/uploads/2019/10/WEB-HARI-BATIK.jpg"))
		slideModel.add(SlideModel("https://images.milledcdn.com/2018-10-01/TFObbj4W4lz_Qidg/DrKAk8Mhw9Uf.jpg"))
		
		binding.isEc.setImageList(slideModel , ScaleTypes.CENTER_CROP)
		
	}
	
	private fun loadStore()
	{
		storeAdapter = StoreAdapter()
		binding.rvStore.layoutManager = LinearLayoutManager(
			requireActivity() ,
			LinearLayoutManager.HORIZONTAL ,
			false
		                                                   )
		binding.rvStore.adapter = storeAdapter
		val listStore = DataDummy.generateDummyStore()
		storeAdapter.setList(listStore)
		
		binding.btnShowAllStore.setOnClickListener {
			val intent = Intent(requireActivity() , StoreActivity::class.java)
			startActivity(intent)
		}
	}
	
	private fun loadProduct()
	{
		productAdapter = ProductAdapter()
		binding.rvProduct.layoutManager = LinearLayoutManager(
			requireActivity() ,
			LinearLayoutManager.HORIZONTAL ,
			false
		                                                     )
		binding.rvProduct.adapter = productAdapter
		val listProduct = DataDummy.generateDummyProduct()
		productAdapter.setList(listProduct)
	}
	
	
}