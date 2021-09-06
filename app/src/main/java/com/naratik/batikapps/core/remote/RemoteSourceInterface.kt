package com.naratik.batikapps.core.remote

import android.content.Context
import com.naratik.batikapps.core.remote.model.*
import com.naratik.batikapps.util.ResultStateData
import kotlinx.coroutines.flow.Flow

interface RemoteSourceInterface
{
	suspend fun GetAllBatikResponse() : Flow<ResultStateData<BatikResponse>>
	
	suspend fun GetPopularBatikResponse() : Flow<ResultStateData<BatikResponse>>
	
	suspend fun UploadImage(ctx : Context,upload : ImageUploadModel)
	
	suspend fun GetPredictMotif(id : String) : Flow<ResultStateData<PredictResponse>>
	
	suspend fun GetPredicTechnique(id : String) : Flow<ResultStateData<TechniquePredictResponse>>
	
	suspend fun GetAllShop() : Flow<ResultStateData<ShopResponse>>
}

