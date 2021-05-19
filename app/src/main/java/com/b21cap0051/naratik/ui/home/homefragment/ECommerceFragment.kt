package com.b21cap0051.naratik.ui.home.homefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.b21cap0051.naratik.databinding.FragmentECommerceBinding


class ECommerceFragment : Fragment() {

    private var _binding : FragmentECommerceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentECommerceBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    companion object {

    }
}