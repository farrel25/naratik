package com.naratik.batikapps.ui.view.cameraui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.naratik.batikapps.R
import com.naratik.batikapps.core.remote.model.ImageUploadModel
import com.naratik.batikapps.databinding.FragmentUploadProcessBinding
import com.naratik.batikapps.ui.view.ResultActivity
import com.naratik.batikapps.ui.view.ResultActivity.Companion.KEY_DATA
import com.naratik.batikapps.ui.viewmodel.UploadMainView
import com.naratik.batikapps.util.ResultStateData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class UploadProcessFragment : DialogFragment()
{
	
	private lateinit var binding : FragmentUploadProcessBinding
	private lateinit var modelData : ImageUploadModel
	
	override fun onCreateView(
		inflater : LayoutInflater , container : ViewGroup? ,
		savedInstanceState : Bundle?
	                         ) : View
	{
		binding = FragmentUploadProcessBinding.inflate(layoutInflater , container , false)
		return binding.root
	}
	
	private val mainView : UploadMainView by viewModels()
	
	override fun onViewCreated(view : View , savedInstanceState : Bundle?)
	{
		modelData = arguments?.getParcelable<ImageUploadModel>(KEY_UPLOAD) as ImageUploadModel
		
		mainView.uploadFile(this.requireActivity() , modelData)
		
		mainView.isConnectedNetwork().observe(viewLifecycleOwner , { response ->
			when (response)
			{
				is ResultStateData.Success ->
				{
					loadingUpload()
					uploadStatus(modelData)
				}
				is ResultStateData.Failure ->
				{
					Toast.makeText(activity, response.message,Toast.LENGTH_SHORT).show()
					backCameraActivity()
				}
				is ResultStateData.Idle    ->
				{
					
				}
			}
		})
		
		
		
	}
	
	
	companion object
	{
		const val KEY_UPLOAD = "key_upload"
	}
	
	private fun loadingUpload()
	{
		
		binding.pbCameraUpload.max = 100
		lifecycleScope.launch(Dispatchers.Default) {
			
			for (i in 0 .. 100 step 20)
			{
				delay(250)
				withContext(Dispatchers.Main) {
					binding.tvProgress.text =
						resources.getString(R.string.presentase , i.toString())
					binding.pbCameraUpload.progress = i
				}
				
				
			}
			
		}
	}
	
	private fun uploadStatus(image : ImageUploadModel)
	{
		mainView.isDoneUpload().observe(viewLifecycleOwner , { response ->
			when (response)
			{
				is ResultStateData.Success ->
				{
					Toast.makeText(context , "Upload Successfully!" , Toast.LENGTH_SHORT).show()
					startActivity(Intent(activity,ResultActivity::class.java).putExtra(KEY_DATA,image))
					requireActivity().finish()
				
				}
				
				is ResultStateData.Failure -> {
					Toast.makeText(context , response.message , Toast.LENGTH_SHORT).show()
					backCameraActivity()
				}
				
				is ResultStateData.Loading -> {
				
				}
				
				is ResultStateData.Idle -> {
				
				}
			}
			
		})
	}
	
	private fun backCameraActivity()
	{
		dismiss()
	}
}