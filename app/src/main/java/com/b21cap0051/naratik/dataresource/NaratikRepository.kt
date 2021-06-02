package com.b21cap0051.naratik.dataresource

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.b21cap0051.naratik.dataresource.local.LocalDataSource
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.dataresource.local.model.HistoryEntity
import com.b21cap0051.naratik.dataresource.local.model.PopularBatikEntity
import com.b21cap0051.naratik.dataresource.local.model.ShopEntity
import com.b21cap0051.naratik.dataresource.remotedata.DataSourceService
import com.b21cap0051.naratik.dataresource.remotedata.model.*
import com.b21cap0051.naratik.util.ExecutedApp
import com.b21cap0051.naratik.util.vo.Resource
import com.b21cap0051.naratik.util.voapi.ApiResponse

class NaratikRepository constructor(
	private val ctx : Context ,
	private val RemoteData : DataSourceService ,
	private val LocalData : LocalDataSource ,
	private val Executer : ExecutedApp
                                   ) : NaratikRepoInterface
{
	
	companion object
	{
		
		@Volatile
		private var Instance : NaratikRepository? = null
		
		fun GetInstance(
			ctx : Context ,
			remote : DataSourceService ,
			local : LocalDataSource ,
			execute : ExecutedApp
		               ) : NaratikRepository =
			Instance ?: synchronized(this) {
				Instance ?: NaratikRepository(ctx , remote , local , execute).apply {
					Instance = this
				}
			}
		
		
	}
	
	override fun GetAllBatik() : LiveData<Resource<PagedList<BatikEntity>>>
	{
		return object : NetworkBoundResource<PagedList<BatikEntity> , BatikResponse>(Executer)
		{
			override fun saveCallResult(item : BatikResponse)
			{
				val result = ArrayList<BatikEntity>()
				val dataResponse = item.hasil
				for (i in 0 until dataResponse?.size!!)
				{
					val dataDB = BatikEntity(
						dataResponse[i].id ,
						dataResponse[i].namaBatik ,
						dataResponse[i].maknaBatik ,
						dataResponse[i].linkBatik ,
						dataResponse[i].daerahBatik
					                        )
					result.add(dataDB)
				}
				Executer.DiskIO().execute {
					LocalData.InsertBatik(result)
				}
			}
			
			override fun shouldFetch(data : PagedList<BatikEntity>?) : Boolean =
				data == null || data.isEmpty()
			
			
			override fun loadfromDb() : LiveData<PagedList<BatikEntity>>
			{
				val buildPaged = PagedList.Config.Builder()
					.setEnablePlaceholders(true)
					.setInitialLoadSizeHint(10)
					.setPageSize(10)
					.build()
				return LivePagedListBuilder(LocalData.GetAllBatik() , buildPaged).build()
			}
			
			
			override fun createCall() : LiveData<ApiResponse<BatikResponse>>
			{
				return RemoteData.GetAllBatikResponse()
			}
			
		}.asLiveData()
	}
	
	override fun GetAllShop() : LiveData<Resource<PagedList<ShopEntity>>>
	{
		return object : NetworkBoundResource<PagedList<ShopEntity> , ShopResponse>(Executer)
		{
			override fun saveCallResult(item : ShopResponse)
			{
				val result = ArrayList<ShopEntity>()
				val dataResponse = item.shopList
				for (i in 0 until dataResponse?.size!!)
				{
					val dataSource = ShopEntity(
						0 ,
						dataResponse[i].namaToko ,
						dataResponse[i].product ,
						dataResponse[i].alamatToko
					                           )
					result.add(dataSource)
				}
				Executer.DiskIO().execute {
					LocalData.InsertShop(result)
				}
				
			}
			
			override fun shouldFetch(data : PagedList<ShopEntity>?) : Boolean =
				data == null || data.isEmpty()
			
			override fun loadfromDb() : LiveData<PagedList<ShopEntity>>
			{
				val buildPaged = PagedList.Config.Builder()
					.setEnablePlaceholders(true)
					.setInitialLoadSizeHint(20)
					.setPageSize(20)
					.build()
				return LivePagedListBuilder(LocalData.GetAllShop() , buildPaged).build()
			}
			
			override fun createCall() : LiveData<ApiResponse<ShopResponse>> =
				RemoteData.GetAllShop()
			
		}.asLiveData()
	}
	
	override fun GetLimitedBatik() : LiveData<Resource<PagedList<BatikEntity>>>
	{
		return object : NetworkBoundResource<PagedList<BatikEntity> , BatikResponse>(Executer)
		{
			override fun saveCallResult(item : BatikResponse)
			{
				val result = ArrayList<BatikEntity>()
				val dataResponse = item.hasil
				for (i in 0 until dataResponse?.size!!)
				{
					val dataDB = BatikEntity(
						dataResponse[i].id ,
						dataResponse[i].namaBatik ,
						dataResponse[i].maknaBatik ,
						dataResponse[i].linkBatik ,
						dataResponse[i].daerahBatik
					                        )
					result.add(dataDB)
				}
				Executer.DiskIO().execute {
					LocalData.InsertBatik(result)
				}
			}
			
			override fun shouldFetch(data : PagedList<BatikEntity>?) : Boolean =
				data == null || data.isEmpty()
			
			
			override fun loadfromDb() : LiveData<PagedList<BatikEntity>>
			{
				val buildPaged = PagedList.Config.Builder()
					.setEnablePlaceholders(true)
					.setInitialLoadSizeHint(4)
					.setPageSize(4)
					.build()
				return LivePagedListBuilder(LocalData.GetLimitedBatik() , buildPaged).build()
			}
			
			
			override fun createCall() : LiveData<ApiResponse<BatikResponse>>
			{
				return RemoteData.GetAllBatikResponse()
			}
			
		}.asLiveData()
	}
	
	
	override fun GetPopular() : LiveData<Resource<List<PopularBatikEntity>>>
	{
		return object : NetworkBoundResource<List<PopularBatikEntity> , BatikResponse>(Executer)
		{
			override fun saveCallResult(item : BatikResponse)
			{
				val dataResponse = item?.hasil
				val dataDb = ArrayList<PopularBatikEntity>()
				for (i in 0 until dataResponse?.size!!)
				{
					val dataBatik = PopularBatikEntity(
						dataResponse[i].id ,
						dataResponse[i].namaBatik ,
						dataResponse[i].maknaBatik ,
						dataResponse[i].linkBatik ,
						dataResponse[i].daerahBatik
					                                  )
					dataDb.add(dataBatik)
				}
				Executer.DiskIO().execute {
					LocalData.insertPopularBatik(dataDb)
				}
			}
			
			override fun shouldFetch(data : List<PopularBatikEntity>?) : Boolean =
				data == null || data.isEmpty()
			
			override fun loadfromDb() : LiveData<List<PopularBatikEntity>> =
				LocalData.GetAllPopularBatik()
			
			override fun createCall() : LiveData<ApiResponse<BatikResponse>> =
				RemoteData.GetPopularBatikResponse()
			
		}.asLiveData()
	}
	
	override fun InsertUploadImage(
		upload : ImageUploadModel
	                              ) = RemoteData.UploadImage(upload)
	
	override fun getInternetConnect() : LiveData<Resource<Boolean>> =
		RemoteData.InternetIsconnected()
	
	
	override fun IsDone() : LiveData<Resource<Boolean>> = RemoteData.getIsDone()
	
	override fun searchBatik(id : String) : LiveData<Resource<List<BatikEntity>>>
	{
		return object : NetworkBoundResource<List<BatikEntity> , BatikResponse>(Executer)
		{
			override fun saveCallResult(item : BatikResponse)
			{
				val dataResponse = item?.hasil
				val dataDb = ArrayList<PopularBatikEntity>()
				for (i in 0 until dataResponse?.size!!)
				{
					val dataBatik = PopularBatikEntity(
						dataResponse[i].id ,
						dataResponse[i].namaBatik ,
						dataResponse[i].maknaBatik ,
						dataResponse[i].linkBatik ,
						dataResponse[i].daerahBatik
					                                  )
					dataDb.add(dataBatik)
				}
				Executer.DiskIO().execute {
					LocalData.insertPopularBatik(dataDb)
				}
			}
			
			override fun shouldFetch(data : List<BatikEntity>?) : Boolean =
				data == null || data.isEmpty()
			
			override fun loadfromDb() : LiveData<List<BatikEntity>> = LocalData.searchData(id)
			
			override fun createCall() : LiveData<ApiResponse<BatikResponse>> =
				RemoteData.GetAllBatikResponse()
			
		}.asLiveData()
	}
	
	override fun GetPredictMotif(id : String) : LiveData<ApiResponse<PredictResponse>> =
		RemoteData.GetPredictMotif(id)
	
	override fun GetTechniquePredict(id : String) : LiveData<ApiResponse<TechniquePredictResponse>> =
		RemoteData.GetPredicTechnique(id)
	
	override fun IsDoneMotif() : LiveData<Resource<Boolean>> = RemoteData.loadMotif
	
	override fun IsDoneTechnique() : LiveData<Resource<Boolean>> = RemoteData.loadTechnique
	
	override fun InsertHistory(value : HistoryEntity) = Executer.DiskIO().execute {
		LocalData.InsertHistory(value)
	}
	
	override fun DeleteHistory(value : HistoryEntity) = Executer.DiskIO().execute {
		if (LocalData.GetAllHistory().value?.size == 1)
		{
			LocalData.DeleteAllHistory()
		}
		LocalData.DeleteHistory(value)
	}
	
	override fun DeleteAllHistory() = Executer.DiskIO().execute {
		LocalData.DeleteAllHistory()
	}
	
	override fun AddLikedBatik(value : BatikEntity) = Executer.DiskIO().execute {
		LocalData.updateBatik(value)
	}
	
	override fun DelLikeBatik(value : BatikEntity) = Executer.DiskIO().execute {
		LocalData.updateBatik(value)
	}
	
	override fun GetAllHistory() : LiveData<List<HistoryEntity>> = LocalData.GetAllHistory()
	
	
}