package com.b21cap0051.naratik.ui.cameraui

import android.os.Bundle
import android.provider.CalendarContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.databinding.FragmentUploadProcessBinding
import com.b21cap0051.naratik.dataresource.remotedata.StatusResponse
import com.b21cap0051.naratik.dataresource.remotedata.model.ImageUploadModel
import com.b21cap0051.naratik.mainview.UploadMainView
import com.b21cap0051.naratik.mainview.ViewFactoryModel


class UploadProcessFragment : Fragment()
{

	private lateinit var binding : FragmentUploadProcessBinding
	
	override fun onCreateView(
		inflater : LayoutInflater , container : ViewGroup? ,
		savedInstanceState : Bundle?
	                         ) : View?
	{
		binding = FragmentUploadProcessBinding.inflate(layoutInflater,container,false)
		return binding.root
	}
	
	private lateinit var mainView : UploadMainView
	private lateinit var modelData : ImageUploadModel
	override fun onViewCreated(view : View , savedInstanceState : Bundle?)
	{
		if(arguments!= null){
			modelData = arguments?.getParcelable<ImageUploadModel>(KEY_UPLOAD) as ImageUploadModel
		}
		
	   val factory = ViewFactoryModel.GetInstance(requireContext())
		mainView = ViewModelProvider(requireActivity(),factory)[UploadMainView::class.java]
		
		mainView.uploadFile(modelData)
		
		mainView.GetProgress().observe(viewLifecycleOwner,{ response ->
			when(response.Status){
				StatusResponse.EMPTY -> {
					binding.LoadingUpload.text = "${response.Data} %"
				}
				StatusResponse.SUCCESS -> {
					binding.notifSend.text = response.message
				}StatusResponse.ERROR -> {
				binding.notifSend.text = response.message
				}
			}
		})
		
		mainView.IsDone().observe(viewLifecycleOwner,{response ->
			when(response.Status){
				StatusResponse.EMPTY -> {
					showText(response.Data!!)
				}
				StatusResponse.SUCCESS -> {
					showText(response.Data!!)
				}
				StatusResponse.ERROR -> {
					showText(response.Data!!)
				}
			}
		})
		
		
	}
	
	private fun showText(stat : Boolean){
		if(stat){
			binding.LoadingUpload.visibility = View.VISIBLE
			binding.textLoad.visibility = View.VISIBLE
			binding.notifSend.visibility = View.GONE
		}else{
			binding.LoadingUpload.visibility = View.GONE
			binding.textLoad.visibility = View.GONE
			binding.notifSend.visibility = View.VISIBLE
		}
	}
	
	
	companion object
	{
        const val KEY_UPLOAD = "key_upload"
		
	}
}