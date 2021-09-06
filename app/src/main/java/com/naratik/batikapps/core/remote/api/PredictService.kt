package com.naratik.batikapps.core.remote.api

import com.naratik.batikapps.core.remote.model.PredictResponse
import com.naratik.batikapps.core.remote.model.ShopResponse
import com.naratik.batikapps.core.remote.model.TechniquePredictResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface PredictService
{
	@GET("motif/{unique_id}")
	suspend fun getPredictBatik(@Path("unique_id") unique_id : String) : Response<PredictResponse>
	
	@GET("technique/{unique_id}")
	@Headers("Auth: B21-CAP0051")
	suspend fun getPredictTechnique(@Path("unique_id") unique_id : String) : Response<TechniquePredictResponse>
	
	@GET("shop")
	suspend fun getShopList() : ShopResponse
	
}