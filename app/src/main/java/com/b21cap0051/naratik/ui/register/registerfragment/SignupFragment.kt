package com.b21cap0051.naratik.ui.register.registerfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.b21cap0051.naratik.databinding.FragmentSignupBinding


class SignupFragment : Fragment()
{
	
	
	private var _binding : FragmentSignupBinding? = null
	private val binding get() = _binding!!
	
	override fun onCreateView(
		inflater : LayoutInflater , container : ViewGroup? ,
		savedInstanceState : Bundle?
	                         ) : View?
	{
		_binding = FragmentSignupBinding.inflate(layoutInflater , container , false)
		return binding.root
	}
	
	companion object
	{

	}
}