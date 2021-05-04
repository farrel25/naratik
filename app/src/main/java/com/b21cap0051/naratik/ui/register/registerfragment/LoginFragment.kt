package com.b21cap0051.naratik.ui.register.registerfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.databinding.FragmentExploreBinding
import com.b21cap0051.naratik.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {


    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    companion object {

    }
}