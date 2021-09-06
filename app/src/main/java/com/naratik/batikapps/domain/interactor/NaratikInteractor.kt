package com.naratik.batikapps.domain.interactor

import android.content.Context
import com.naratik.batikapps.core.local.model.BatikEntity
import com.naratik.batikapps.core.local.model.HistoryEntity
import com.naratik.batikapps.core.local.model.PopularBatikEntity
import com.naratik.batikapps.core.local.model.ShopEntity
import com.naratik.batikapps.core.remote.model.ImageUploadModel
import com.naratik.batikapps.core.remote.model.PredictResponse
import com.naratik.batikapps.core.remote.model.TechniquePredictResponse
import com.naratik.batikapps.domain.repository.NaratikRepository
import com.naratik.batikapps.domain.usecase.NaratikUseCase
import com.naratik.batikapps.util.ResultStateData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


class NaratikInteractor @Inject constructor(private val NaratikRepo : NaratikRepository) : NaratikUseCase
{
	override fun getAllBatik() : Flow<ResultStateData<List<BatikEntity>>> =
		NaratikRepo.getAllBatik()
	
	
	override fun getAllBatikRandomDb() : Flow<ResultStateData<List<BatikEntity>>> =
		NaratikRepo.getAllBatikRandomDb()
	
	override fun getAllFavorite() : Flow<ResultStateData<List<BatikEntity>>> =
		NaratikRepo.getAllFavorite()
	
	override fun getPopular() : Flow<ResultStateData<List<PopularBatikEntity>>> =
		NaratikRepo.getPopular()
	
	override suspend fun updateLikedBatik(value : BatikEntity)
	{
		NaratikRepo.updateLikedBatik(value)
	}
	
	override fun getAllShop() : Flow<ResultStateData<List<ShopEntity>>>
	{
		return NaratikRepo.getAllShop()
	}
	
	override suspend fun insertUploadImage(ctx : Context , upload : ImageUploadModel)
	{
		return NaratikRepo.insertUploadImage(ctx,upload)
	}
	
	override fun getInternetConnect() : StateFlow<ResultStateData<Boolean>>
	{
		return NaratikRepo.getInternetConnect()
	}
	
	override fun isDone() : StateFlow<ResultStateData<Boolean>>
	{
		return NaratikRepo.isDone()
	}
	
	override suspend fun searchBatik(id : String) : Flow<ResultStateData<List<BatikEntity>>>
	{
		return NaratikRepo.searchBatik(id)
	}
	
	override suspend fun getPredictMotif(id : String) : Flow<ResultStateData<PredictResponse>>
	{
		return NaratikRepo.getPredictMotif(id)
	}
	
	override suspend fun getTechniquePredict(id : String) : Flow<ResultStateData<TechniquePredictResponse>>
	{
		return NaratikRepo.getTechniquePredict(id)
	}
	
	override fun isDoneMotif() : StateFlow<ResultStateData<Boolean>>
	{
		return NaratikRepo.isDoneMotif()
	}
	
	override fun isDoneTechnique() : StateFlow<ResultStateData<Boolean>>
	{
		return NaratikRepo.isDoneTechnique()
	}
	
	override suspend fun getAllHistory() : Flow<ResultStateData<List<HistoryEntity>>>
	{
		return NaratikRepo.getAllHistory()
	}
	
	override suspend fun insertHistory(value : HistoryEntity)
	{
		NaratikRepo.insertHistory(value)
	}
	
	override suspend fun deleteHistory(value : HistoryEntity)
	{
		NaratikRepo.deleteHistory(value)
	}
	
	override suspend fun deleteAllHistory()
	{
		NaratikRepo.deleteAllHistory()
	}
}