package com.b21cap0051.naratik.ui.home.homefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.b21cap0051.naratik.databinding.FragmentECommerceBinding
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel


class ECommerceFragment : Fragment() {

    private var _binding : FragmentECommerceBinding? = null
    private val binding get() = _binding as FragmentECommerceBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentECommerceBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
    
    override fun onViewCreated(view : View , savedInstanceState : Bundle?)
    {
        super.onViewCreated(view , savedInstanceState)
        val slideModel : ArrayList<SlideModel> = arrayListOf()
        slideModel.add(SlideModel("https://picsum.photos/id/237/700/700"))
        slideModel.add(SlideModel("https://picsum.photos/id/238/700/700"))
        slideModel.add(SlideModel("https://picsum.photos/id/231/700/700"))
        slideModel.add(SlideModel("https://picsum.photos/id/232/700/700"))
        
        binding.isEc.setImageList(slideModel,ScaleTypes.CENTER_CROP)
        
    }

    companion object {

    }
}