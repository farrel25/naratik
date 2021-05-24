package com.b21cap0051.naratik.dataresource

import android.content.Context
import androidx.lifecycle.LiveData
import com.b21cap0051.naratik.dataresource.local.LocalDataSource
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.dataresource.local.model.PopularBatikEntity
import com.b21cap0051.naratik.dataresource.remotedata.ApiResponse
import com.b21cap0051.naratik.dataresource.remotedata.DataSourceService
import com.b21cap0051.naratik.dataresource.remotedata.model.BatikResponse
import com.b21cap0051.naratik.util.ExecutedApp
import com.b21cap0051.naratik.util.Resource

class NaratikRepository constructor(
	private val ctx : Context,
	private val RemoteData : DataSourceService,
	private val LocalData :LocalDataSource,
	private val Executer : ExecutedApp
								   ):NaratikRepoInterface
{
	
	companion object{
		
		@Volatile
		private var Instance : NaratikRepository? = null
		
		fun GetInstance(ctx : Context ,remote : DataSourceService, local : LocalDataSource,execute : ExecutedApp ):NaratikRepository =
			Instance?: synchronized(this){
				Instance?: NaratikRepository(ctx,remote,local,execute).apply {
					 Instance = this
				}
			}
		
		
		
	}
	
	override fun GetAllBatik() : LiveData<Resource<List<BatikEntity>>>
	{
		return object : NetworkBoundResource<List<BatikEntity>,BatikResponse>(Executer){
			override fun saveCallResult(item : BatikResponse)
			{
			  val result = ArrayList<BatikEntity>()
				val dataResponse = item.hasil
				for (i in 0 until dataResponse?.size!!){
					val dataDB = BatikEntity(
						dataResponse[i].id,
						dataResponse[i].namaBatik,
						dataResponse[i].maknaBatik,
						dataResponse[i].linkBatik,
						dataResponse[i].daerahBatik
											)
					result.add(dataDB)
				}
				LocalData.InsertBatik(result)
			}
			
			override fun shouldFetch(data : List<BatikEntity>?) : Boolean =
				data == null || data.isEmpty()
			
			override fun loadfromDb() : LiveData<List<BatikEntity>> =
				LocalData.GetAllBatik()
			
			override fun createCall() : LiveData<ApiResponse<BatikResponse>>
			{
				return RemoteData.GetAllBatikResponse()
			}
			
		}.asLiveData()
	}
	
	override fun GetPopular() : LiveData<Resource<List<PopularBatikEntity>>>
	{
	    return object : NetworkBoundResource<List<PopularBatikEntity>,BatikResponse>(Executer){
		    override fun saveCallResult(item : BatikResponse)
		    {
			   val dataResponse = item?.hasil
			    val dataDb = ArrayList<PopularBatikEntity>()
			    for (i in 0 until dataResponse?.size!!){
			    	val dataBatik = PopularBatikEntity(
					    dataResponse[i].id,
					    dataResponse[i].namaBatik,
					    dataResponse[i].maknaBatik,
					    dataResponse[i].linkBatik,
					    dataResponse[i].daerahBatik
			    									  )
				    dataDb.add(dataBatik)
			    }
			    LocalData.insertPopularBatik(dataDb)
		    }
		
		    override fun shouldFetch(data : List<PopularBatikEntity>?) : Boolean =
		    	data == null || data.isEmpty()
		
		    override fun loadfromDb() : LiveData<List<PopularBatikEntity>> =
		    	LocalData.GetAllPopularBatik()
		
		    override fun createCall() : LiveData<ApiResponse<BatikResponse>> = RemoteData.GetPopularBatikResponse()
		
	    }.asLiveData()
	}
	
	
}