package com.b21cap0051.naratik.ui.favourite.favouritefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.b21cap0051.naratik.databinding.FragmentArticleFavouriteBinding

class ArticleFavouriteFragment : Fragment() {
    private var _binding: FragmentArticleFavouriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleFavouriteBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    companion object {

    }
}