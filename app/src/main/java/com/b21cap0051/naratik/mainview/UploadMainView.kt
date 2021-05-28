package com.b21cap0051.naratik.mainview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.b21cap0051.naratik.dataresource.NaratikRepository
import com.b21cap0051.naratik.dataresource.remotedata.model.ImageUploadModel
import com.b21cap0051.naratik.util.vo.Resource

class UploadMainView(private val repository : NaratikRepository) : ViewModel()
{
	fun uploadFile(model : ImageUploadModel){
		repository.InsertUploadImage(model)
	}
	
	fun GetProgress():LiveData<Resource<Double>> = repository.GetProgress()
	
	fun IsDone():LiveData<Resource<Boolean>> = repository.IsDone()
}