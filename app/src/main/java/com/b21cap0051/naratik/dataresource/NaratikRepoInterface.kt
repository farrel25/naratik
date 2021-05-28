package com.b21cap0051.naratik.dataresource

import android.content.Context
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
	
	fun getInternetConnect():LiveData<Resource<Boolean>>
	
	fun IsDone():LiveData<Resource<Boolean>>
	
}