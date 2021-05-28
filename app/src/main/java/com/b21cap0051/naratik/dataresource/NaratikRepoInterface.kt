package com.b21cap0051.naratik.dataresource

import androidx.lifecycle.LiveData
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.dataresource.local.model.PopularBatikEntity
import com.b21cap0051.naratik.dataresource.remotedata.model.ImageUploadModel
import com.b21cap0051.naratik.util.vo.Resource

interface NaratikRepoInterface
{
	fun GetAllBatik():LiveData<Resource<List<BatikEntity>>>
	
	fun GetPopular():LiveData<Resource<List<PopularBatikEntity>>>
	
	fun InsertUploadImage(upload : ImageUploadModel)
	
	fun GetProgress():LiveData<Resource<Double>>
	
	fun IsDone():LiveData<Resource<Boolean>>
	
}