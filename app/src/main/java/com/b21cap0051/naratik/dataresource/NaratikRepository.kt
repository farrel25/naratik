package com.b21cap0051.naratik.dataresource

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.b21cap0051.naratik.dataresource.local.LocalDataSource
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.dataresource.local.model.PopularBatikEntity
import com.b21cap0051.naratik.dataresource.remotedata.DataSourceService
import com.b21cap0051.naratik.dataresource.remotedata.model.BatikResponse
import com.b21cap0051.naratik.dataresource.remotedata.model.ImageUploadModel
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
				LocalData.InsertBatik(result)
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
				LocalData.InsertBatik(result)
			}
			
			override fun shouldFetch(data : PagedList<BatikEntity>?) : Boolean =
				true
			
			
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
				LocalData.insertPopularBatik(dataDb)
			}
			
			override fun shouldFetch(data : List<PopularBatikEntity>?) : Boolean =
				true
			
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
		return object : NetworkBoundResource<List<BatikEntity>,BatikResponse>(Executer){
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
				LocalData.insertPopularBatik(dataDb)
			}
			
			override fun shouldFetch(data : List<BatikEntity>?) : Boolean =
				true
			
			override fun loadfromDb() : LiveData<List<BatikEntity>> = LocalData.searchData(id)
			
			override fun createCall() : LiveData<ApiResponse<BatikResponse>> =  RemoteData.GetAllBatikResponse()
			
		}.asLiveData()
	}
	
	
}