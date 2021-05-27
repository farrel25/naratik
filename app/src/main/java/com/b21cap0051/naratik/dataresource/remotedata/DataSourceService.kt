package com.b21cap0051.naratik.dataresource.remotedata

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.b21cap0051.naratik.dataresource.remotedata.api.APIConfig
import com.b21cap0051.naratik.dataresource.remotedata.model.BatikResponse
import com.b21cap0051.naratik.dataresource.remotedata.model.ImageUploadModel
import com.b21cap0051.naratik.util.Resource
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.OnProgressListener
import com.google.firebase.storage.StorageMetadata
import com.google.firebase.storage.UploadTask
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataSourceService(private val ctx : Context) : DataSourceInterface
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
	
	private val TAG = DataSourceService::class.java.simpleName
	
	override fun GetAllBatikResponse() : LiveData<ApiResponse<BatikResponse>>
	{
		val data = MutableLiveData<ApiResponse<BatikResponse>>()
		val GetBatik = APIConfig.ApiData().GetAllBatik()
		GetBatik.enqueue(object : Callback<BatikResponse>
		{
			override fun onResponse(call : Call<BatikResponse> , response : Response<BatikResponse>)
			{
				if (response.isSuccessful)
				{
					val DataResponse = response.body()
					data.value = ApiResponse.success(DataResponse!!)
				}
			}
			
			override fun onFailure(call : Call<BatikResponse> , t : Throwable)
			{
				Log.e("ERROR!" , "$t.message")
				Toast.makeText(ctx , "Error : ${t.message}" , Toast.LENGTH_SHORT).show()
			}
			
		})
		
		return data
	}
	
	override fun GetPopularBatikResponse() : LiveData<ApiResponse<BatikResponse>>
	{
		val _Data = MutableLiveData<ApiResponse<BatikResponse>>()
		val GetPopular = APIConfig.ApiData().GetPopularBatik()
		GetPopular.enqueue(object : Callback<BatikResponse>
		{
			override fun onResponse(call : Call<BatikResponse> , response : Response<BatikResponse>)
			{
				if (response.isSuccessful)
				{
					val DataResponse = response.body()
					_Data.value = ApiResponse.success(DataResponse!!)
				}
			}
			
			override fun onFailure(call : Call<BatikResponse> , t : Throwable)
			{
				Log.e("ERROR" , "${t.message}")
				Toast.makeText(ctx , "Error : ${t.message}" , Toast.LENGTH_SHORT).show()
			}
			
		})
		
		return _Data
	}
	
	
	private val _data = MutableLiveData<Resource<Double>>()
	fun getProgress() : LiveData<Resource<Double>> = _data
	
	private val _progress = MutableLiveData<Resource<Boolean>>()
	fun getIsDone() : LiveData<Resource<Boolean>> = _progress
	
	override fun UploadImage(upload : ImageUploadModel)
	{
		val storage = FirebaseStorage.getInstance()
		
		val storageRef = storage.reference
		
		_progress.value = Resource(StatusResponse.EMPTY,false)
		
		val imageRef = storageRef.child("images/${upload.uri.lastPathSegment}")
		
		var metadata = StorageMetadata.Builder()
			.setContentType("image/jpg")
			.build()
		
		val uploadTask = imageRef.putFile(upload.uri , metadata)
		
		uploadTask.addOnProgressListener(object : OnProgressListener<UploadTask.TaskSnapshot>
		{
			override fun onProgress(snapshot : UploadTask.TaskSnapshot)
			{
				val progress = (100.0 * snapshot.bytesTransferred) / snapshot.totalByteCount
				_data.value = Resource(StatusResponse.EMPTY,progress)
				Log.d(TAG , "Progress $progress")
			}
		})
		
		uploadTask.addOnSuccessListener(object : OnSuccessListener<UploadTask.TaskSnapshot>
		{
			override fun onSuccess(p0 : UploadTask.TaskSnapshot?)
			{
				_data.value = Resource(StatusResponse.SUCCESS,100.0,"Upload Succesfully")
				_progress.value = Resource(StatusResponse.SUCCESS,true)
				Log.d(TAG , "Uploaded Sucessfully")
			}
			
		})
		
		uploadTask.addOnFailureListener(object : OnFailureListener
		{
			override fun onFailure(p0 : Exception)
			{
				_data.value = Resource(StatusResponse.ERROR,-1.0,"error : $p0")
				_progress.value = Resource(StatusResponse.ERROR,true)
				Log.d(TAG , "Failure because ${p0.message}")
			}
			
		})
	}
	
	
}