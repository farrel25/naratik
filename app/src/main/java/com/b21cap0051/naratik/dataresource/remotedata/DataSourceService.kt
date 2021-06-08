package com.b21cap0051.naratik.dataresource.remotedata

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.b21cap0051.naratik.dataresource.remotedata.api.APIConfig
import com.b21cap0051.naratik.dataresource.remotedata.model.*
import com.b21cap0051.naratik.util.CheckConnected
import com.b21cap0051.naratik.util.vo.Resource
import com.b21cap0051.naratik.util.vo.Status
import com.b21cap0051.naratik.util.voapi.ApiResponse
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.OnProgressListener
import com.google.firebase.storage.StorageMetadata
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
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
	
	
	private val _progress = MutableLiveData<Resource<Boolean>>()
	fun getIsDone() : LiveData<Resource<Boolean>> = _progress
	
	private val _checkInternet = MutableLiveData<Resource<Boolean>>()
	fun InternetIsconnected() : LiveData<Resource<Boolean>> = _checkInternet
	
	override fun UploadImage(upload : ImageUploadModel)
	{
		_checkInternet.value = Resource(Status.SUCCESS , false , "")
		
		val checkInet = CheckConnected.CreateInstance(ctx)
		
		if (checkInet.IsConnected() == 0)
		{
			_checkInternet.value = Resource(Status.ERROR , true , "Internet Connection Lost")
		} else
		{
			_checkInternet.value = Resource(Status.SUCCESS , false , "")
			
			val storage = Firebase.storage("gs://b21-cap0051-image")
			val storageRef = storage.reference
			
			_progress.value = Resource(Status.LOADING , false)
			
			val imageRef = storageRef.child("${upload.uri.lastPathSegment}")
			
			var metadata = StorageMetadata.Builder()
				.setContentType("image/jpg")
				.build()
			
			val uploadTask = imageRef.putFile(upload.uri , metadata)
			
			uploadTask.addOnProgressListener(object : OnProgressListener<UploadTask.TaskSnapshot>
			{
				override fun onProgress(snapshot : UploadTask.TaskSnapshot)
				{
					val progress = (100.0 * snapshot.bytesTransferred) / snapshot.totalByteCount
					Log.d(TAG , "Progress $progress")
				}
			})
			
			uploadTask.addOnSuccessListener(object : OnSuccessListener<UploadTask.TaskSnapshot>
			{
				override fun onSuccess(p0 : UploadTask.TaskSnapshot?)
				{
					_progress.value = Resource(Status.SUCCESS , true , "Upload Succesfully")
				}
				
			})
			
			uploadTask.addOnFailureListener(object : OnFailureListener
			{
				override fun onFailure(p0 : Exception)
				{
					_progress.value = Resource(Status.ERROR , true , "${p0.message}")
					Log.d(TAG , "Failure because ${p0.message}")
				}
				
			})
		}
		
	}
	
	private var _loadMotif = MutableLiveData<Resource<Boolean>>()
	val loadMotif : LiveData<Resource<Boolean>> = _loadMotif
	
	private var _loadTechnique = MutableLiveData<Resource<Boolean>>()
	val loadTechnique : LiveData<Resource<Boolean>> = _loadTechnique
	
	override fun GetPredictMotif(id : String) : LiveData<ApiResponse<PredictResponse>>
	{
		_loadMotif.postValue(Resource(Status.LOADING , false))
		val mutableData = MutableLiveData<ApiResponse<PredictResponse>>()
		
		Handler(Looper.getMainLooper()).postDelayed({
			val retro = APIConfig.ApiPredict().GetPredictBatik(id)
			retro.enqueue(object : Callback<PredictResponse>
			{
				override fun onResponse(
					call : Call<PredictResponse> ,
					response : Response<PredictResponse>
				                       )
				{
					if (response.isSuccessful)
					{
						Log.d("MYTAG" , response.errorBody().toString())
						if (response.body() != null)
						{
							val dataResponse = response.body()
							mutableData.postValue(ApiResponse.success(dataResponse!!))
							_loadMotif.postValue(Resource(Status.SUCCESS , true))
						} else
						{
							call.clone().enqueue(this)
						}
					}
				}
				
				override fun onFailure(call : Call<PredictResponse> , t : Throwable)
				{
					call.clone().enqueue(this)
					_loadMotif.postValue(Resource(Status.ERROR , false))
					Log.e("ERROR" , "${t.message}")
					Toast.makeText(ctx , "Error : ${t.message}" , Toast.LENGTH_SHORT).show()
				}
			})
		} , 10000L)
		
		return mutableData
	}
	
	override fun GetPredicTechnique(id : String) : LiveData<ApiResponse<TechniquePredictResponse>>
	{
		_loadTechnique.postValue(Resource(Status.LOADING , false))
		val MutableData = MutableLiveData<ApiResponse<TechniquePredictResponse>>()
		Handler(Looper.getMainLooper()).postDelayed({
			val getApi = APIConfig.ApiPredict().GetPredictTechnique(id)
			getApi.enqueue(object : Callback<TechniquePredictResponse>
			{
				override fun onResponse(
					call : Call<TechniquePredictResponse> ,
					response : Response<TechniquePredictResponse>
				                       )
				{
					if (response.isSuccessful)
					{
						if (response.body() != null)
						{
							val dataResponse = response.body()
							Log.d("MyTAG" , "$dataResponse")
							MutableData.postValue(ApiResponse.success(dataResponse!!))
							_loadTechnique.postValue(Resource(Status.SUCCESS , true))
						} else
						{
							call.clone().enqueue(this)
						}
						
						Log.d("MyTAG" , "${response.body()}")
					}
				}
				
				override fun onFailure(call : Call<TechniquePredictResponse> , t : Throwable)
				{
					call.clone().enqueue(this)
					_loadTechnique.postValue(Resource(Status.ERROR , false))
					Log.e("ERROR" , "${t.message}")
					Toast.makeText(ctx , "Error : ${t.message}" , Toast.LENGTH_SHORT).show()
				}
				
			})
		} , 10000L)
		
		return MutableData
	}
	
	override fun GetAllShop() : LiveData<ApiResponse<ShopResponse>>
	{
		
		val MutableData = MutableLiveData<ApiResponse<ShopResponse>>()
		
		val retro = APIConfig.ApiPredict().GetShopList()
		retro.enqueue(object : Callback<ShopResponse>
		{
			override fun onResponse(call : Call<ShopResponse> , response : Response<ShopResponse>)
			{
				if (response.isSuccessful)
				{
					if (response.body() != null)
					{
						val responData = response.body()
						MutableData.postValue(ApiResponse.success(responData!!))
					}
				}
			}
			
			override fun onFailure(call : Call<ShopResponse> , t : Throwable)
			{
				Log.d(DataSourceService::class.java.simpleName , "${t.message}")
			}
		})
		return MutableData
	}
	
}