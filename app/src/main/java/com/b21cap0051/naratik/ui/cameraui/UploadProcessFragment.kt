package com.b21cap0051.naratik.ui.cameraui

import android.os.Bundle
<<<<<<< Updated upstream
=======
<<<<<<< HEAD
import android.os.Handler
=======
>>>>>>> Stashed changes
import androidx.fragment.app.Fragment
>>>>>>> 4c4913545203a416843919a836bb8dde9bdfcefe
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.b21cap0051.naratik.databinding.FragmentUploadProcessBinding
import com.b21cap0051.naratik.util.voapi.StatusResponse
import com.b21cap0051.naratik.dataresource.remotedata.model.ImageUploadModel
import com.b21cap0051.naratik.mainview.UploadMainView
import com.b21cap0051.naratik.mainview.ViewFactoryModel
<<<<<<< Updated upstream
import com.b21cap0051.naratik.util.vo.Status
=======
<<<<<<< HEAD
=======
import com.b21cap0051.naratik.util.vo.Status
>>>>>>> 4c4913545203a416843919a836bb8dde9bdfcefe
>>>>>>> Stashed changes


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
		
<<<<<<< HEAD
		mainView.GetProgress().observe(viewLifecycleOwner , { response ->
			when (response.Status)
			{
				StatusResponse.EMPTY   ->
				{
					binding.tvProgress.text = "${response.Data} %"
					pb.progress = response.Data!!.toInt()
				}
				StatusResponse.SUCCESS ->
				{
					Toast.makeText(context , response.message , Toast.LENGTH_SHORT).show()
				}
				StatusResponse.ERROR   ->
				{
					Toast.makeText(context , response.message , Toast.LENGTH_SHORT).show()
=======
		mainView.GetProgress().observe(viewLifecycleOwner,{ response ->
			when(response.Status){
				Status.LOADING    -> {
					pb.max = 100
					val currentProgress = response.Data?.toInt()
					if (currentProgress != null)
					{
						ObjectAnimator.ofInt(binding.pbCameraUpload,"progress",currentProgress)
					}
//					pb.progress = "${response.Data} %"
				}
				Status.SUCCESS    -> {
					Toast.makeText(context,response.message,Toast.LENGTH_SHORT).show()
				}
				Status.ERROR -> {
					Toast.makeText(context,response.message,Toast.LENGTH_SHORT).show()
>>>>>>> 4c4913545203a416843919a836bb8dde9bdfcefe
				}
			}
		})
		
<<<<<<< HEAD
		mainView.IsDone().observe(viewLifecycleOwner , { response ->
			when (response.Status)
			{
				StatusResponse.EMPTY   ->
				{
					showText(response.Data!!)
				}
				StatusResponse.SUCCESS ->
				{
					showText(response.Data!!)
				}
				StatusResponse.ERROR   ->
				{
=======
		mainView.IsDone().observe(viewLifecycleOwner,{response ->
			when(response.Status){
				Status.LOADING    -> {
					showText(response.Data!!)
				}
				Status.SUCCESS    -> {
					showText(response.Data!!)
				}
				Status.ERROR -> {
<<<<<<< Updated upstream
=======
>>>>>>> 4c4913545203a416843919a836bb8dde9bdfcefe
>>>>>>> Stashed changes
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