package com.b21cap0051.naratik.ui.cameraui

import android.animation.ObjectAnimator
import android.os.Bundle
import android.provider.CalendarContract
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.databinding.FragmentUploadProcessBinding
import com.b21cap0051.naratik.dataresource.remotedata.StatusResponse
import com.b21cap0051.naratik.dataresource.remotedata.model.ImageUploadModel
import com.b21cap0051.naratik.mainview.UploadMainView
import com.b21cap0051.naratik.mainview.ViewFactoryModel
import com.google.android.material.snackbar.Snackbar


class UploadProcessFragment : Fragment()
{

	private lateinit var binding : FragmentUploadProcessBinding
	private lateinit var pb : ProgressBar
	override fun onCreateView(
		inflater : LayoutInflater , container : ViewGroup? ,
		savedInstanceState : Bundle?
	                         ) : View
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
		pb = binding.pbCameraUpload
	    val factory = ViewFactoryModel.GetInstance(requireContext())
		mainView = ViewModelProvider(requireActivity(),factory)[UploadMainView::class.java]
		
		mainView.uploadFile(modelData)
		
		mainView.GetProgress().observe(viewLifecycleOwner,{ response ->
			when(response.Status){
				StatusResponse.EMPTY -> {
					pb.max = 100
					val currentProgress = response.Data?.toInt()
					if (currentProgress != null)
					{
						ObjectAnimator.ofInt(binding.pbCameraUpload,"progress",currentProgress)
					}
//					pb.progress = "${response.Data} %"
				}
				StatusResponse.SUCCESS -> {
					Toast.makeText(context,response.message,Toast.LENGTH_SHORT).show()
				}StatusResponse.ERROR -> {
					Toast.makeText(context,response.message,Toast.LENGTH_SHORT).show()
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
			binding.pbCameraUpload.visibility = View.VISIBLE
			binding.textLoad.visibility = View.VISIBLE
		}else{
			binding.pbCameraUpload.visibility = View.GONE
			binding.textLoad.visibility = View.GONE
		}
	}
	
	
	companion object
	{
        const val KEY_UPLOAD = "key_upload"
		
	}
}