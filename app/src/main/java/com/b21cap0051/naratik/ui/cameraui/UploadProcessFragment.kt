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
	private var stat = false
	private var notif = ""
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
					uploadStatus()
					LoadingUpload(modelData)
				
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
	
	private fun LoadingUpload(image : ImageUploadModel)
	{
		mainView.IsDone().removeObservers(this)
		binding.pbCameraUpload.max = 100
		lifecycleScope.launch(Dispatchers.Default) {
			for (i in 0 .. 100 step 10)
			{
				delay(200)
				withContext(Dispatchers.Main) {
					binding.tvProgress.text = "$i %"
					binding.pbCameraUpload.progress = i
				}
			
				
			}
			if(stat){
				val move = Intent(activity , ResultActivity::class.java)
				move.putExtra(KEY_DATA , image)
				startActivity(move)
				requireActivity().finish()
			}else{
				backCameraActivity()
			}
		}
		
	}
	
	private fun uploadStatus()
	{
		mainView.IsDone().observe(viewLifecycleOwner , { response ->
			
			when (response.Status)
			{
				Status.SUCCESS ->
				{
					
					stat = true
					Toast.makeText(context , response.message , Toast.LENGTH_SHORT).show()
					
				}
				Status.ERROR   ->
				{
					stat = false
					Toast.makeText(context , response.message , Toast.LENGTH_SHORT).show()
					
				}
			}
		})
	}
	
	private fun backCameraActivity()
	{
		findNavController().popBackStack()
	}
}