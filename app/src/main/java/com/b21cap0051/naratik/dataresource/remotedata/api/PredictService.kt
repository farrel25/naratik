package com.b21cap0051.naratik.dataresource.remotedata.api

import com.b21cap0051.naratik.dataresource.remotedata.model.PredictResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PredictService
{
	
	@GET("motif/{unique_id}")
	fun GetPredictBatik(@Path("unique_id")unique_id : String): Call<PredictResponse>
	
}