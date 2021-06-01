package com.b21cap0051.naratik.dataresource.remotedata

import androidx.lifecycle.LiveData
import com.b21cap0051.naratik.dataresource.remotedata.model.BatikResponse
import com.b21cap0051.naratik.dataresource.remotedata.model.ImageUploadModel
import com.b21cap0051.naratik.dataresource.remotedata.model.PredictResponse
import com.b21cap0051.naratik.dataresource.remotedata.model.TechniquePredictResponse
import com.b21cap0051.naratik.util.voapi.ApiResponse

interface DataSourceInterface
{
	fun GetAllBatikResponse() : LiveData<ApiResponse<BatikResponse>>
	
	fun GetPopularBatikResponse() : LiveData<ApiResponse<BatikResponse>>
	
	fun UploadImage(upload : ImageUploadModel)
	
	fun GetPredictMotif(id : String):LiveData<ApiResponse<PredictResponse>>
	
	fun GetPredicTechnique(id : String):LiveData<ApiResponse<TechniquePredictResponse>>
}

