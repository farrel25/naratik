package com.b21cap0051.naratik.ui.cameraui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.b21cap0051.naratik.databinding.FragmentUploadProcessBinding
import com.b21cap0051.naratik.dataresource.remotedata.model.ImageUploadModel
import com.b21cap0051.naratik.mainview.UploadMainView
import com.b21cap0051.naratik.mainview.ViewFactoryModel
import com.b21cap0051.naratik.ui.ResultActivity
import com.b21cap0051.naratik.ui.ResultActivity.Companion.KEY_DATA
import com.b21cap0051.naratik.util.vo.Status
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
		
		mainView.IsCOnnected().observe(viewLifecycleOwner , { response ->
			when (response.Status)
			{
				Status.SUCCESS ->
				{
					
					LoadingUpload()
					uploadStatus(modelData)
				}
				Status.ERROR   ->
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
	
	private fun LoadingUpload()
	{
		
		binding.pbCameraUpload.max = 100
		lifecycleScope.launch(Dispatchers.Default) {
			
			for (i in 0 .. 100 step 25)
			{
				delay(200)
				withContext(Dispatchers.Main) {
					binding.tvProgress.text = "$i %"
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
				Status.SUCCESS ->
				{
					Toast.makeText(context , response.message , Toast.LENGTH_SHORT).show()
					val move = Intent(activity , ResultActivity::class.java)
					move.putExtra(KEY_DATA , image)
					startActivity(move)
					requireActivity().finish()
				}
				Status.ERROR   ->
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