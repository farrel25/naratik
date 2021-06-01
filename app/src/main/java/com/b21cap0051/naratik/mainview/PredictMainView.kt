package com.b21cap0051.naratik.mainview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.b21cap0051.naratik.dataresource.NaratikRepository
import com.b21cap0051.naratik.dataresource.remotedata.model.PredictResponse
import com.b21cap0051.naratik.dataresource.remotedata.model.TechniquePredictResponse
import com.b21cap0051.naratik.util.voapi.ApiResponse

class PredictMainView(private val repo : NaratikRepository) : ViewModel()
{
	
	fun GetMotif(id : String) : LiveData<ApiResponse<PredictResponse>> = repo.GetPredictMotif(id)
	
	fun GetTechnique(id : String) : LiveData<ApiResponse<TechniquePredictResponse>> =
		repo.GetTechniquePredict(id)
	
}