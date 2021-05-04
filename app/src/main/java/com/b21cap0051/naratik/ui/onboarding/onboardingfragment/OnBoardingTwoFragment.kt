package com.b21cap0051.naratik.ui.onboarding.onboardingfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.databinding.FragmentExploreBinding
import com.b21cap0051.naratik.databinding.FragmentOnBoardingTwoBinding


class OnBoardingTwoFragment : Fragment() {


    private var _binding: FragmentOnBoardingTwoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnBoardingTwoBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    companion object {

    }
}