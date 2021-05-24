package com.b21cap0051.naratik.dataresource

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.b21cap0051.naratik.dataresource.remotedata.ApiResponse
import com.b21cap0051.naratik.dataresource.remotedata.StatusResponse
import com.b21cap0051.naratik.util.ExecutedApp
import com.b21cap0051.naratik.util.Resource

abstract class NetworkBoundResource<ResultType , RequestType>(private val Execute : ExecutedApp)
{
	
	private val result = MediatorLiveData<Resource<ResultType>>()
	
	init
	{
		result.value = Resource.Loading(null)
		@Suppress("LeakingThis")
		val dbSource = loadfromDb()
		result.addSource(dbSource) { data ->
			result.removeSource(dbSource)
			if (shouldFetch(data))
			{
				fetchFromNetwork(dbSource)
			} else
			{
				result.addSource(dbSource) { newData ->
					setValue(Resource.Success(newData))
				}
			}
		}
		
	}
	
	@MainThread
	private fun setValue(newValue : Resource<ResultType>)
	{
		if (result.value != newValue)
		{
			result.value = newValue
		}
	}
	
	private fun fetchFromNetwork(dbSource : LiveData<ResultType>)
	{
		val apiResponse = createCall()
		result.addSource(dbSource) { newData ->
			setValue(Resource.Loading(newData))
		}
		result.addSource(apiResponse) { Response ->
			result.removeSource(apiResponse)
			result.removeSource(dbSource)
			when (Response.statusResponse)
			{
				StatusResponse.SUCCESS ->
				{
					Execute.DiskIO().execute {
						saveCallResult(Response.body)
						Execute.MainThread().execute {
							result.addSource(loadfromDb()) { newData ->
								result.value = Resource.Success(newData)
							}
						}
					}
				}
				
				StatusResponse.EMPTY   ->
				{
					Execute.MainThread().execute {
						result.addSource(loadfromDb()) { newData ->
							result.value = Resource.Success(newData)
						}
					}
				}
				StatusResponse.ERROR   ->
				{
					onFetchFailed()
					result.addSource(dbSource) { newData ->
						result.value = Resource.Error(Response.message , newData)
					}
				}
				
			}
		}
	}
	
	fun asLiveData():LiveData<Resource<ResultType>> = result
	
	@WorkerThread
	protected abstract fun saveCallResult(item : RequestType)
	
	@MainThread
	protected abstract fun shouldFetch(data : ResultType?) : Boolean
	
	@MainThread
	protected abstract fun loadfromDb() : LiveData<ResultType>
	
	@MainThread
	protected abstract fun createCall() : LiveData<ApiResponse<RequestType>>
	
	protected open fun onFetchFailed()
	{
	}
}