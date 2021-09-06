package com.naratik.batikapps.domain.repository

import android.content.Context
import com.naratik.batikapps.core.local.LocalSource
import com.naratik.batikapps.core.local.model.BatikEntity
import com.naratik.batikapps.core.local.model.HistoryEntity
import com.naratik.batikapps.core.local.model.PopularBatikEntity
import com.naratik.batikapps.core.local.model.ShopEntity
import com.naratik.batikapps.core.remote.RemoteSource
import com.naratik.batikapps.core.remote.model.*
import com.naratik.batikapps.domain.usecase.NaratikInterface
import com.naratik.batikapps.util.DataMapper
import com.naratik.batikapps.util.NetworkBoundResource
import com.naratik.batikapps.util.ResultStateData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NaratikRepository @Inject constructor(
	private val remoteData : RemoteSource ,
	private val local : LocalSource ,
                                           ) : NaratikInterface
{
	override fun getAllBatik() : Flow<ResultStateData<List<BatikEntity>>>
	{
		return object : NetworkBoundResource<List<BatikEntity> , BatikResponse>()
		{
			override suspend fun saveNetworkResult(data : BatikResponse)
			{
				val dataResponse = DataMapper.apiToBatikEntity(data)
				local.insertBatik(dataResponse)
			}
			
			override suspend fun shouldFetch(data : List<BatikEntity>?) : Boolean
			{
				return true
			}
			
			override fun loadFromDb() : Flow<List<BatikEntity>>
			{
				return local.getAllBatik()
			}
			
			override suspend fun fetchFromNetwork() : Flow<ResultStateData<BatikResponse>>
			{
				return remoteData.GetAllBatikResponse()
			}
			
		}.asFlowData()
	}
	
	override fun getAllBatikRandomDb() : Flow<ResultStateData<List<BatikEntity>>>
	{
		return object : NetworkBoundResource<List<BatikEntity> , BatikResponse>()
		{
			override suspend fun saveNetworkResult(data : BatikResponse)
			{
				val cacheData = DataMapper.apiToBatikEntity(data)
				local.insertBatik(cacheData)
			}
			
			override suspend fun shouldFetch(data : List<BatikEntity>?) : Boolean
			{
				return true
			}
			
			override fun loadFromDb() : Flow<List<BatikEntity>>
			{
				return local.getAllBatikRandomLimit()
			}
			
			override suspend fun fetchFromNetwork() : Flow<ResultStateData<BatikResponse>>
			{
				return remoteData.GetAllBatikResponse()
			}
			
		}.asFlowData()
	}
	
	override fun getAllFavorite() : Flow<ResultStateData<List<BatikEntity>>>
	{
		return object : NetworkBoundResource<List<BatikEntity> , BatikResponse>()
		{
			override suspend fun saveNetworkResult(data : BatikResponse)
			{
				val cacheData = DataMapper.apiToBatikEntity(data)
				local.insertBatik(cacheData)
			}
			
			override suspend fun shouldFetch(data : List<BatikEntity>?) : Boolean
			{
				return true
			}
			
			override fun loadFromDb() : Flow<List<BatikEntity>>
			{
				return local.checkFavouriteBatik()
			}
			
			override suspend fun fetchFromNetwork() : Flow<ResultStateData<BatikResponse>>
			{
				return remoteData.GetAllBatikResponse()
			}
			
		}.asFlowData()
	}
	
	override fun getPopular() : Flow<ResultStateData<List<PopularBatikEntity>>>
	{
		return object : NetworkBoundResource<List<PopularBatikEntity> , BatikResponse>()
		{
			override suspend fun saveNetworkResult(data : BatikResponse)
			{
				val cacheData = DataMapper.apiToBatikEntityPopuler(data)
				local.insertPopularBatik(cacheData)
			}
			
			override suspend fun shouldFetch(data : List<PopularBatikEntity>?) : Boolean
			{
				return true
			}
			
			override fun loadFromDb() : Flow<List<PopularBatikEntity>>
			{
				return local.getAllPopularBatik()
			}
			
			override suspend fun fetchFromNetwork() : Flow<ResultStateData<BatikResponse>>
			{
				return remoteData.GetPopularBatikResponse()
			}
			
		}.asFlowData()
	}
	
	override suspend fun updateLikedBatik(value : BatikEntity)
	{
		return local.setFavoriteBatik(value)
	}
	
	override fun getAllShop() : Flow<ResultStateData<List<ShopEntity>>>
	{
		return object : NetworkBoundResource<List<ShopEntity>,ShopResponse>(){
			override suspend fun saveNetworkResult(data : ShopResponse)
			{
				val cacheData = DataMapper.apiToEntityShop(data)
				local.insertShop(cacheData)
			}
			
			override suspend fun shouldFetch(data : List<ShopEntity>?) : Boolean
			{
				return true
			}
			
			override fun loadFromDb() : Flow<List<ShopEntity>>
			{
				return local.getAllShop()
			}
			
			override suspend fun fetchFromNetwork() : Flow<ResultStateData<ShopResponse>>
			{
				return remoteData.GetAllShop()
			}
			
		}.asFlowData()
	}
	
	override suspend fun insertUploadImage(ctx : Context , upload : ImageUploadModel)
	{
		remoteData.UploadImage(ctx,upload)
	}
	
	override fun getInternetConnect() : StateFlow<ResultStateData<Boolean>>
	{
		return remoteData.internetIsconnected()
	}
	
	override fun isDone() : StateFlow<ResultStateData<Boolean>>
	{
		return remoteData.getIsDone()
	}
	
	override suspend fun searchBatik(id : String) : Flow<ResultStateData<List<BatikEntity>>>
	{
		val listSearch = ArrayList<BatikEntity>()
		val dataSearch = local.searchData(id)
		dataSearch.collect {
			listSearch.addAll(listSearch)
		}
		
		return flow {
			try
			{
				emit(ResultStateData.Loading())
				if (listSearch.isNotEmpty()) emit(ResultStateData.Success(listSearch , null))
			} catch (e : Exception)
			{
				emit(ResultStateData.Failure(null , "Data Not Found !"))
			}
		}.debounce(1000).flowOn(Dispatchers.Default)
		
	}
	
	override suspend fun getPredictMotif(id : String) : Flow<ResultStateData<PredictResponse>>
	{
		return remoteData.GetPredictMotif(id)
	}
	
	override suspend fun getTechniquePredict(id : String) : Flow<ResultStateData<TechniquePredictResponse>>
	{
		return remoteData.GetPredicTechnique(id)
	}
	
	override fun isDoneMotif() : StateFlow<ResultStateData<Boolean>>
	{
		return remoteData.loadMotif
	}
	
	override fun isDoneTechnique() : StateFlow<ResultStateData<Boolean>>
	{
		return remoteData.loadTechnique
	}
	
	override suspend fun getAllHistory() : Flow<ResultStateData<List<HistoryEntity>>>
	{
		val dataHistory = ArrayList<HistoryEntity>()
		val historyList = local.getAllHistory()
		historyList.collect {
			dataHistory.addAll(it)
		}
		return flow {
			try
			{
				if (dataHistory.isNotEmpty()) emit(ResultStateData.Success(dataHistory , null))
			} catch (e : Exception)
			{
				emit(ResultStateData.Failure(null , "Data Not Found !"))
			}
		}.flowOn(Dispatchers.Default)
	}
	
	override suspend fun insertHistory(value : HistoryEntity)
	{
		local.insertHistory(value)
	}
	
	override suspend fun deleteHistory(value : HistoryEntity)
	{
		local.deleteHistory(value)
	}
	
	override suspend fun deleteAllHistory()
	{
		local.deleteAllHistory()
	}
	
	
}