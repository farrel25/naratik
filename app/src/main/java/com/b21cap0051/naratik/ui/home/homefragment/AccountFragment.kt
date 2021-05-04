package com.b21cap0051.naratik.ui.home.homefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.databinding.FragmentAccountBinding


class AccountFragment : Fragment() {

    private var _binding : FragmentAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccountBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    companion object {

    }
}