package com.b21cap0051.naratik.dataresource.remotedata.api

import com.b21cap0051.naratik.dataresource.remotedata.model.BatikResponse
import retrofit2.Call
import retrofit2.http.GET

interface DataServices
{
	@GET("batik/all")
	fun GetAllBatik():Call<BatikResponse>
	
	@GET("batik/popular")
	fun GetPopularBatik():Call<BatikResponse>
	
}