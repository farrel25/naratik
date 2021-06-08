package com.b21cap0051.naratik.view.cameraui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.databinding.FragmentUploadProcessBinding
import com.b21cap0051.naratik.dataresource.remotedata.model.ImageUploadModel
import com.b21cap0051.naratik.viewmodel.UploadMainView
import com.b21cap0051.naratik.viewmodel.ViewFactoryModel
import com.b21cap0051.naratik.view.ResultActivity
import com.b21cap0051.naratik.view.ResultActivity.Companion.KEY_DATA
import com.b21cap0051.naratik.util.vo.Status.ERROR
import com.b21cap0051.naratik.util.vo.Status.SUCCESS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


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
	
	private lateinit var mainView : UploadMainView
	
	override fun onViewCreated(view : View , savedInstanceState : Bundle?)
	{
		modelData = arguments?.getParcelable<ImageUploadModel>(KEY_UPLOAD) as ImageUploadModel
		val factory = ViewFactoryModel.GetInstance(requireContext())
		mainView = ViewModelProvider(requireActivity() , factory)[UploadMainView::class.java]
		
		mainView.uploadFile(modelData)
		
		mainView.IsConnected().observe(viewLifecycleOwner , { response ->
			when (response.Status)
			{
				SUCCESS ->
				{
					
					loadingUpload()
					uploadStatus(modelData)
				}
				ERROR   ->
				{
					backCameraActivity()
					Toast.makeText(requireContext() , response.message , Toast.LENGTH_SHORT).show()
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
			
			for (i in 0 .. 100 step 25)
			{
				delay(200)
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
		mainView.IsDone().observe(viewLifecycleOwner , { response ->
			
			when (response.Status)
			{
				SUCCESS ->
				{
					Toast.makeText(context , response.message , Toast.LENGTH_SHORT).show()
					val move = Intent(activity , ResultActivity::class.java)
					move.putExtra(KEY_DATA , image)
					startActivity(move)
					requireActivity().finish()
				}
				ERROR   ->
				{
					Toast.makeText(context , response.message , Toast.LENGTH_SHORT).show()
					backCameraActivity()
				}
			}
		})
	}
	
	private fun backCameraActivity()
	{
		dismiss()
	}
}