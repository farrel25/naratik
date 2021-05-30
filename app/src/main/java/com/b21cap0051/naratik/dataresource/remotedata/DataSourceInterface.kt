package com.b21cap0051.naratik.dataresource.remotedata

import androidx.lifecycle.LiveData
import com.b21cap0051.naratik.dataresource.remotedata.model.BatikResponse
import com.b21cap0051.naratik.dataresource.remotedata.model.ImageUploadModel
import com.b21cap0051.naratik.util.voapi.ApiResponse

interface DataSourceInterface
{
	fun GetAllBatikResponse() : LiveData<ApiResponse<BatikResponse>>
	
	fun GetPopularBatikResponse() : LiveData<ApiResponse<BatikResponse>>
	
	fun UploadImage(upload : ImageUploadModel)
}

