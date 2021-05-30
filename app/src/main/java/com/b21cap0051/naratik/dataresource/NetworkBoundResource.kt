package com.b21cap0051.naratik.dataresource

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.b21cap0051.naratik.util.ExecutedApp
import com.b21cap0051.naratik.util.vo.Resource
import com.b21cap0051.naratik.util.voapi.ApiResponse
import com.b21cap0051.naratik.util.voapi.StatusResponse

abstract class NetworkBoundResource<ResultType , RequestType>(private val mExecutor : ExecutedApp)
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
		result.addSource(apiResponse) { response ->
			result.removeSource(apiResponse)
			result.removeSource(dbSource)
			when (response.statusResponse)
			{
				StatusResponse.SUCCESS ->
				{
					mExecutor.DiskIO().execute {
						saveCallResult(processResponse(response))
						mExecutor.MainThread().execute {
							result.addSource(loadfromDb()) { newData ->
								setValue(Resource.Success(newData))
							}
						}
					}
				}
				
				StatusResponse.EMPTY   ->
				{
					mExecutor.MainThread().execute {
						result.addSource(loadfromDb()) { newData ->
							setValue(Resource.Success(newData))
						}
					}
				}
				StatusResponse.ERROR   ->
				{
					onFetchFailed()
					result.addSource(dbSource) { newData ->
						setValue(Resource.Error(response.message , newData))
					}
				}
				
			}
		}
	}
	
	fun asLiveData() : LiveData<Resource<ResultType>> = result
	
	@WorkerThread
	protected abstract fun saveCallResult(item : RequestType)
	
	@MainThread
	protected abstract fun shouldFetch(data : ResultType?) : Boolean
	
	@WorkerThread
	protected open fun processResponse(response : ApiResponse<RequestType>) = response.body
	
	@MainThread
	protected abstract fun loadfromDb() : LiveData<ResultType>
	
	@MainThread
	protected abstract fun createCall() : LiveData<ApiResponse<RequestType>>
	
	protected open fun onFetchFailed()
	{
	}
}