package com.b21cap0051.naratik.dataresource.remotedata

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.b21cap0051.naratik.dataresource.remotedata.api.APIConfig
import com.b21cap0051.naratik.dataresource.remotedata.model.BatikResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataSourceService(private val ctx : Context):DataSourceInterface
{
	companion object
	{
		@Volatile
		private var Instance : DataSourceService? = null
		
		fun GetInstance(ctx : Context) : DataSourceService =
			Instance ?: synchronized(this) {
				Instance ?: DataSourceService(ctx).apply {
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
				Log.e("ERROR!","$t.message")
				Toast.makeText(ctx,"Error : ${t.message}",Toast.LENGTH_SHORT).show()
			}
			
		})
		
		return data
	}
	
	override fun GetPopularBatikResponse() : LiveData<ApiResponse<BatikResponse>>
	{
		val _Data = MutableLiveData<ApiResponse<BatikResponse>>()
		val GetPopular = APIConfig.ApiData().GetPopularBatik()
		GetPopular.enqueue(object : Callback<BatikResponse>{
			override fun onResponse(call : Call<BatikResponse> , response : Response<BatikResponse>)
			{
				if(response.isSuccessful){
					val DataResponse = response.body()
					_Data.value = ApiResponse.success(DataResponse!!)
				}
			}
			
			override fun onFailure(call : Call<BatikResponse> , t : Throwable)
			{
				Log.e("ERROR","${t.message}")
				Toast.makeText(ctx,"Error : ${t.message}",Toast.LENGTH_SHORT).show()
			}
			
		})
		
		return _Data
	}
	
	
}