package com.b21cap0051.naratik.ui.cameraui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.b21cap0051.naratik.databinding.FragmentUploadProcessBinding
import com.b21cap0051.naratik.dataresource.remotedata.model.ImageUploadModel
import com.b21cap0051.naratik.mainview.UploadMainView
import com.b21cap0051.naratik.mainview.ViewFactoryModel
import com.b21cap0051.naratik.util.vo.Status


class UploadProcessFragment : Fragment()
{

	private lateinit var binding : FragmentUploadProcessBinding
	private lateinit var pb : ProgressBar
	override fun onCreateView(
		inflater : LayoutInflater , container : ViewGroup? ,
		savedInstanceState : Bundle?
	                         ) : View
	{
		binding = FragmentUploadProcessBinding.inflate(layoutInflater , container , false)
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
		mainView = ViewModelProvider(requireActivity() , factory)[UploadMainView::class.java]
		
		mainView.uploadFile(modelData)
		
		mainView.GetProgress().observe(viewLifecycleOwner , { response ->
			when (response.Status)
			{
				Status.LOADING   ->
				{
					binding.tvProgress.text = "${response.Data} %"
					pb.progress = response.Data!!.toInt()
				}
				Status.SUCCESS ->
				{
					Toast.makeText(context , response.message , Toast.LENGTH_SHORT).show()
				}
				Status.ERROR   ->
				{
					Toast.makeText(context , response.message , Toast.LENGTH_SHORT).show()
				}
			}
		})
		
		mainView.IsDone().observe(viewLifecycleOwner , { response ->
			when (response.Status)
			{
				Status.LOADING ->
				{
					showText(response.Data!!)
				}
				Status.SUCCESS  ->
				{
					showText(response.Data!!)
				}
				Status.ERROR    ->
				{
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