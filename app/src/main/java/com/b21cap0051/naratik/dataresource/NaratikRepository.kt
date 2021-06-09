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
	
	override fun GetAllBatik() : LiveData<Resource<List<BatikEntity>>>
	{
		return object : NetworkBoundResource<List<BatikEntity> , BatikResponse>(Executer)
		{
			override fun saveCallResult(item : BatikResponse)
			{
				val batikResponse = item.hasil
				val tempArr = ArrayList<BatikEntity>()
				for (i in 0 until batikResponse?.size!!)
				{
					val model = BatikEntity(
						batikResponse[i].id ,
						batikResponse[i].namaBatik ,
						batikResponse[i].maknaBatik ,
						batikResponse[i].linkBatik ,
						batikResponse[i].daerahBatik ,
						0
					                       )
					tempArr.add(model)
				}
				Executer.DiskIO().execute {
					LocalData.insertBatik(tempArr)
				}
			}
			
			override fun loadfromDb() : LiveData<List<BatikEntity>> = LocalData.getAllBatik()
			
			override fun createCall() : LiveData<ApiResponse<BatikResponse>> =
				RemoteData.GetAllBatikResponse()
			
			override fun shouldFetch(data : List<BatikEntity>?) : Boolean =
				data?.isEmpty()!!
			
		}.asLiveData()
	}
	
	override fun GetAllBatikRandomDb() : LiveData<Resource<List<BatikEntity>>>
	{
		return object : NetworkBoundResource<List<BatikEntity> , BatikResponse>(Executer)
		{
			override fun saveCallResult(item : BatikResponse)
			{
				val batikResponse = item.hasil
				val tempArr = ArrayList<BatikEntity>()
				for (i in 0 until batikResponse?.size!!)
				{
					val model = BatikEntity(
						batikResponse[i].id ,
						batikResponse[i].namaBatik ,
						batikResponse[i].maknaBatik ,
						batikResponse[i].linkBatik ,
						batikResponse[i].daerahBatik ,
						0
					                       )
					tempArr.add(model)
				}
				Executer.DiskIO().execute {
					LocalData.insertBatik(tempArr)
				}
			}
			
			override fun loadfromDb() : LiveData<List<BatikEntity>> =
				LocalData.getAllBatikRandomLimit()
			
			override fun createCall() : LiveData<ApiResponse<BatikResponse>> =
				RemoteData.GetAllBatikResponse()
			
			override fun shouldFetch(data : List<BatikEntity>?) : Boolean =
				data == null || data.isEmpty()
			
		}.asLiveData()
	}
	
	override fun GetAllFavorite() : LiveData<Resource<List<BatikEntity>>>
	{
		return object : NetworkBoundResource<List<BatikEntity> , BatikResponse>(Executer)
		{
			override fun saveCallResult(item : BatikResponse)
			{
				val batikResponse = item.hasil
				val tempArr = ArrayList<BatikEntity>()
				for (i in 0 until batikResponse?.size!!)
				{
					val model = BatikEntity(
						batikResponse[i].id ,
						batikResponse[i].namaBatik ,
						batikResponse[i].maknaBatik ,
						batikResponse[i].linkBatik ,
						batikResponse[i].daerahBatik ,
						0
					                       )
					tempArr.add(model)
				}
				Executer.DiskIO().execute {
					LocalData.insertBatik(tempArr)
				}
			}
			
			override fun shouldFetch(data : List<BatikEntity>?) : Boolean =
				false
			
			
			override fun loadfromDb() : LiveData<List<BatikEntity>>
			{
				return LocalData.checkFavouriteBatik()
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
				val responseData = item.hasil
				val arrTemp = ArrayList<PopularBatikEntity>()
				for (i in 0 until responseData?.size!!)
				{
					val data = PopularBatikEntity(
						responseData[i].id ,
						responseData[i].namaBatik ,
						responseData[i].maknaBatik ,
						responseData[i].linkBatik ,
						responseData[i].daerahBatik ,
						0
					                             )
					arrTemp.add(data)
				}
				Executer.DiskIO().execute {
					LocalData.insertPopularBatik(arrTemp)
				}
			}
			
			override fun shouldFetch(data : List<PopularBatikEntity>?) : Boolean =
				data?.isEmpty()!!
			
			override fun loadfromDb() : LiveData<List<PopularBatikEntity>> =
				LocalData.getAllPopularBatik()
			
			override fun createCall() : LiveData<ApiResponse<BatikResponse>> =
				RemoteData.GetPopularBatikResponse()
			
			
		}.asLiveData()
	}
	
	override fun updateLikedBatik(value : BatikEntity) = Executer.DiskIO().execute {
		LocalData.setFavoriteBatik(value)
	}
	
	override fun GetAllShop() : LiveData<Resource<PagedList<ShopEntity>>>
	{
		return object : NetworkBoundResource<PagedList<ShopEntity> , ShopResponse>(Executer)
		{
			override fun saveCallResult(item : ShopResponse)
			{
				val responseData = item.shopList
				val arrTemp = ArrayList<ShopEntity>()
				for (i in 0 until responseData?.size!!)
				{
					val data = ShopEntity(
						null ,
						responseData[i].namaToko ,
						responseData[i].product ,
						responseData[i].alamatToko
					                     )
					arrTemp.add(data)
				}
				Executer.DiskIO().execute {
					LocalData.insertShop(arrTemp)
				}
			}
			
			override fun shouldFetch(data : PagedList<ShopEntity>?) : Boolean =
				data?.isEmpty()!!
			
			
			override fun loadfromDb() : LiveData<PagedList<ShopEntity>>
			{
				val config = PagedList.Config.Builder()
					.setInitialLoadSizeHint(20)
					.setEnablePlaceholders(true)
					.setPageSize(25)
					.setPrefetchDistance(10)
					.build()
				return LivePagedListBuilder(LocalData.getAllShop() , config).build()
			}
			
			override fun createCall() : LiveData<ApiResponse<ShopResponse>> =
				RemoteData.GetAllShop()
			
			
		}.asLiveData()
	}
	
	override fun InsertUploadImage(upload : ImageUploadModel) = RemoteData.UploadImage(upload)
	
	override fun getInternetConnect() : LiveData<Resource<Boolean>> =
		RemoteData.InternetIsconnected()
	
	override fun isDone() : LiveData<Resource<Boolean>> = RemoteData.getIsDone()
	
	override fun searchBatik(id : String) : LiveData<Resource<List<BatikEntity>>>
	{
		return object : NetworkBoundResource<List<BatikEntity> , BatikResponse>(Executer)
		{
			override fun saveCallResult(item : BatikResponse)
			{
				val batikResponse = item.hasil
				val tempArr = ArrayList<BatikEntity>()
				for (i in 0 until batikResponse?.size!!)
				{
					val model = BatikEntity(
						batikResponse[i].id ,
						batikResponse[i].namaBatik ,
						batikResponse[i].maknaBatik ,
						batikResponse[i].linkBatik ,
						batikResponse[i].daerahBatik ,
						0
					                       )
					tempArr.add(model)
				}
				Executer.DiskIO().execute {
					LocalData.insertBatik(tempArr)
				}
			}
			
			override fun shouldFetch(data : List<BatikEntity>?) : Boolean = false
			
			override fun loadfromDb() : LiveData<List<BatikEntity>> = LocalData.searchData(id)
			
			override fun createCall() : LiveData<ApiResponse<BatikResponse>> =
				RemoteData.GetAllBatikResponse()
			
			
		}.asLiveData()
	}
	
	override fun getPredictMotif(id : String) : LiveData<ApiResponse<PredictResponse>> =
		RemoteData.GetPredictMotif(id)
	
	override fun getTechniquePredict(id : String) : LiveData<ApiResponse<TechniquePredictResponse>> =
		RemoteData.GetPredicTechnique(id)
	
	override fun isDoneMotif() : LiveData<Resource<Boolean>> = RemoteData.loadMotif
	
	override fun isDoneTechnique() : LiveData<Resource<Boolean>> = RemoteData.loadTechnique
	
	override fun GetAllHistory() : LiveData<List<HistoryEntity>> = LocalData.getAllHistory()
	
	override fun insertHistory(value : HistoryEntity)
	{
		Executer.DiskIO().execute {
			LocalData.insertHistory(value)
		}
	}
	
	override fun deleteHistory(value : HistoryEntity)
	{
		Executer.DiskIO().execute {
			LocalData.deleteHistory(value)
		}
	}
	
	override fun deleteAllHistory()
	{
		Executer.DiskIO().execute {
			LocalData.deleteAllHistory()
		}
	}
	
	
}