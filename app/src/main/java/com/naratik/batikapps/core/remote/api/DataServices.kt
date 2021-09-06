package com.naratik.batikapps.core.remote.api

import com.naratik.batikapps.core.remote.model.BatikResponse
import retrofit2.http.GET

interface DataServices
{
	@GET("batik/all")
	suspend fun GetAllBatik() : BatikResponse
	
	@GET("batik/popular")
	suspend fun GetPopularBatik() : BatikResponse
	
}