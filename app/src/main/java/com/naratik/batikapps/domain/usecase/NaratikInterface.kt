package com.naratik.batikapps.domain.usecase

import android.content.Context
import com.naratik.batikapps.core.local.model.BatikEntity
import com.naratik.batikapps.core.local.model.HistoryEntity
import com.naratik.batikapps.core.local.model.PopularBatikEntity
import com.naratik.batikapps.core.local.model.ShopEntity
import com.naratik.batikapps.core.remote.model.ImageUploadModel
import com.naratik.batikapps.core.remote.model.PredictResponse
import com.naratik.batikapps.core.remote.model.TechniquePredictResponse
import com.naratik.batikapps.util.ResultStateData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface NaratikInterface
{
	//Batik
	fun getAllBatik() : Flow<ResultStateData<List<BatikEntity>>>
	
	fun getAllBatikRandomDb() : Flow<ResultStateData<List<BatikEntity>>>
	
	fun getAllFavorite() : Flow<ResultStateData<List<BatikEntity>>>
	
	fun getPopular() : Flow<ResultStateData<List<PopularBatikEntity>>>
	
	suspend fun updateLikedBatik(value : BatikEntity)
	
	//Shop
	fun getAllShop() : Flow<ResultStateData<List<ShopEntity>>>
	
	//Upload
	suspend fun insertUploadImage(ctx : Context , upload : ImageUploadModel)
	
	fun getInternetConnect() : StateFlow<ResultStateData<Boolean>>
	
	fun isDone() : StateFlow<ResultStateData<Boolean>>
	
	//Search
	suspend fun searchBatik(id : String) : Flow<ResultStateData<List<BatikEntity>>>
	
	//Result prediction
	suspend fun getPredictMotif(id : String) : Flow<ResultStateData<PredictResponse>>
	
	suspend fun getTechniquePredict(id : String) : Flow<ResultStateData<TechniquePredictResponse>>
	
	fun isDoneMotif() : StateFlow<ResultStateData<Boolean>>
	
	fun isDoneTechnique() : StateFlow<ResultStateData<Boolean>>
	
	//History
	suspend fun getAllHistory() : Flow<ResultStateData<List<HistoryEntity>>>
	
	suspend fun insertHistory(value : HistoryEntity)
	
	suspend fun deleteHistory(value : HistoryEntity)
	
	suspend fun deleteAllHistory()
	
}