package com.naratik.batikapps.core.remote

import android.content.Context
import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageMetadata
import com.google.firebase.storage.ktx.storage
import com.naratik.batikapps.core.remote.api.DataServices
import com.naratik.batikapps.core.remote.api.PredictService
import com.naratik.batikapps.core.remote.model.*
import com.naratik.batikapps.util.ResultStateData
import com.naratik.batikapps.util.networkcheck.CheckConnected
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteSource @Inject constructor(
	private val dataServices : DataServices ,
	private val predictService : PredictService
                                      ) : RemoteSourceInterface
{
	
	private val TAG = RemoteSource::class.java.simpleName
	
	
	private var _loadMotif = MutableStateFlow<ResultStateData<Boolean>>(ResultStateData.Loading())
	val loadMotif : StateFlow<ResultStateData<Boolean>> = _loadMotif
	
	private var _loadTechnique =
		MutableStateFlow<ResultStateData<Boolean>>(ResultStateData.Loading())
	val loadTechnique : StateFlow<ResultStateData<Boolean>> = _loadTechnique
	
	
	private val _progress = MutableStateFlow<ResultStateData<Boolean>>(ResultStateData.Idle(null))
	fun getIsDone() : StateFlow<ResultStateData<Boolean>> = _progress
	
	private val _checkInternet =
		MutableStateFlow<ResultStateData<Boolean>>(ResultStateData.Idle(null))
	
	fun internetIsconnected() : StateFlow<ResultStateData<Boolean>> = _checkInternet
	
	
	override suspend fun GetAllBatikResponse() : Flow<ResultStateData<BatikResponse>>
	{
		return flow {
			try
			{
				emit(ResultStateData.Loading())
				val dataResponse = dataServices.GetAllBatik()
				emit(ResultStateData.Success(dataResponse , null))
			} catch (e : Exception)
			{
				emit(ResultStateData.Failure(null , "${e.message}"))
			} catch (e : HttpException)
			{
				emit(ResultStateData.Failure(null , "${e.message}"))
			}
			
		}.flowOn(Dispatchers.IO)
	}
	
	override suspend fun GetPopularBatikResponse() : Flow<ResultStateData<BatikResponse>>
	{
		return flow {
			try
			{
				emit(ResultStateData.Loading())
				val dataResponse = dataServices.GetPopularBatik()
				emit(ResultStateData.Success(dataResponse , null))
			} catch (e : Exception)
			{
				emit(ResultStateData.Failure(null , "${e.message}"))
			} catch (e : HttpException)
			{
				emit(ResultStateData.Failure(null , "${e.message}"))
			}
		}.flowOn(Dispatchers.IO)
		
		
	}
	
	
	override suspend fun UploadImage(ctx : Context , upload : ImageUploadModel)
	{
		when (CheckConnected.isConnected(ctx))
		{
			0    ->
			{
				_checkInternet.value = ResultStateData.Failure(null , "Internet Connection Lost!")
			}
			else ->
			{
				_checkInternet.value = ResultStateData.Success(true , "")
				
				val storage = Firebase.storage("gs://test-image-13242")
				val storageRef = storage.reference
				
				_progress.value = ResultStateData.Loading()
				
				val imageRef = storageRef.child("${upload.uri.lastPathSegment}")
				
				var metadata = StorageMetadata.Builder()
					.setContentType("image/jpg")
					.build()
				
				val uploadTask = imageRef.putFile(upload.uri , metadata)
				
				uploadTask.addOnProgressListener { snapshot ->
					val progress = (100.0 * snapshot.bytesTransferred) / snapshot.totalByteCount
					Log.d(TAG , "Progress $progress")
				}
				
				uploadTask.addOnSuccessListener {
					_progress.value = ResultStateData.Success(true , "Upload Successfully!")
				}
				
				uploadTask.addOnFailureListener { p0 ->
					_progress.value = ResultStateData.Failure(false , "${p0.message}")
					Log.d(TAG , "Failure because ${p0.message}")
				}
			}
		}
	}
	
	
	override suspend fun GetPredictMotif(id : String) : Flow<ResultStateData<PredictResponse>>
	{
		return flow {
			try
			{
				emit(ResultStateData.Loading())
				var count = 0
				do
				{
					kotlinx.coroutines.delay(2000)
					val dataResponse = predictService.getPredictBatik(id)
					if (dataResponse.isSuccessful)
					{
						if (dataResponse.body() != null)
						{
							emit(ResultStateData.Success(dataResponse.body()!! , null))
							_loadMotif.value = ResultStateData.Success(true , null)
							break
						} else
						{
							continue
						}
					}
					count += 1
				} while (count <= 3)
				
			} catch (e : Exception)
			{
				emit(ResultStateData.Failure(null , "${e.message}"))
				_loadMotif.value = ResultStateData.Failure(true , "${e.message}")
			} catch (e : HttpException)
			{
				emit(ResultStateData.Failure(null , "${e.message}"))
				_loadMotif.value = ResultStateData.Failure(true , "${e.message}")
			}
		}.flowOn(Dispatchers.IO)
	}
	
	override suspend fun GetPredicTechnique(id : String) : Flow<ResultStateData<TechniquePredictResponse>>
	{
		
		return flow {
			try
			{
				emit(ResultStateData.Loading())
				var count = 0
				do
				{
					kotlinx.coroutines.delay(2000)
					val dataResponse = predictService.getPredictTechnique(id)
					if (dataResponse.isSuccessful)
					{
						if (dataResponse.body() != null)
						{
							emit(ResultStateData.Success(dataResponse.body()!! , null))
							_loadTechnique.value = ResultStateData.Success(true , "Please Wait!!!")
							break
						} else
						{
							continue
						}
					}
					count += 1
				} while (count <= 3)
				
			} catch (e : Exception)
			{
				emit(ResultStateData.Failure(null , "${e.message}"))
				_loadTechnique.value = ResultStateData.Failure(true , "${e.message}")
				
			} catch (e : HttpException)
			{
				emit(ResultStateData.Failure(null , "${e.message}"))
				_loadTechnique.value = ResultStateData.Failure(true , "${e.message}")
			}
		}.flowOn(Dispatchers.IO)
		
	}
	
	override suspend fun GetAllShop() : Flow<ResultStateData<ShopResponse>>
	{
		return flow {
			try
			{
				val dataResponse = predictService.getShopList()
				emit(ResultStateData.Success(dataResponse , null))
			} catch (e : Exception)
			{
				emit(ResultStateData.Failure(null , "${e.message}"))
			} catch (e : HttpException)
			{
				emit(ResultStateData.Failure(null , "${e.message}"))
			}
		}.flowOn(Dispatchers.IO)
	}
	
}