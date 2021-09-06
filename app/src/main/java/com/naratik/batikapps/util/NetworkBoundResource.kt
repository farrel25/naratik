package com.naratik.batikapps.util

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.*


abstract class NetworkBoundResource<ResultType , RequestType>
{
	
	fun asFlowData() : Flow<ResultStateData<ResultType>> = flow {
		val resourceDB = loadFromDb().first()
		if (shouldFetch(resourceDB))
		{
			emit(ResultStateData.Loading())
			when (val responseData = fetchFromNetwork().first())
			{
				is ResultStateData.Success ->
				{
					saveNetworkResult(responseData.data)
					emitAll(loadFromDb().map { ResultStateData.Success(it , null) })
				}
				
				is ResultStateData.Failure ->
				{
					onFetchFailed()
					emit(
						ResultStateData.Failure(
							null ,
							"Error Fetch Data!"
						                       )
					    )
				}
			}
		} else
		{
			emitAll(loadFromDb().map { ResultStateData.Success(it , null) })
		}
	}
	
	protected abstract suspend fun saveNetworkResult(data : RequestType)
	
	protected abstract suspend fun shouldFetch(data : ResultType?) : Boolean
	
	protected abstract fun loadFromDb() : Flow<ResultType>
	
	protected abstract suspend fun fetchFromNetwork() : Flow<ResultStateData<RequestType>>
	
	protected open fun onFetchFailed()
	{
	}
}