package com.b21cap0051.naratik.dataresource.remotedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.b21cap0051.naratik.dataresource.remotedata.api.APIConfig
import com.b21cap0051.naratik.dataresource.remotedata.model.BatikResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataSourceService:DataSourceInterface
{
	companion object
	{
		@Volatile
		private var Instance : DataSourceService? = null
		
		fun GetInstance() : DataSourceService =
			Instance ?: synchronized(this) {
				Instance ?: DataSourceService().apply {
					Instance = this
				}
			}
	}
	
	override fun GetAllBatikResponse() : LiveData<ApiResponse<BatikResponse>>
	{
		val data = MutableLiveData<ApiResponse<BatikResponse>>()
		val GetBatik = APIConfig.ApiData().GetAllBatik()
		GetBatik.enqueue(object: Callback<BatikResponse>{
			override fun onResponse(call : Call<BatikResponse> , response : Response<BatikResponse>)
			{
				if (response.isSuccessful){
					val DataResponse = response.body()
					data.value = ApiResponse.success(DataResponse!!)
				}
			}
			
			override fun onFailure(call : Call<BatikResponse> , t : Throwable)
			{
			
			}
			
		})
		
		return data
	}
	
	
}