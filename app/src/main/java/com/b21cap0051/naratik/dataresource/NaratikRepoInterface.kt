package com.b21cap0051.naratik.dataresource

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.dataresource.local.model.HistoryEntity
import com.b21cap0051.naratik.dataresource.local.model.PopularBatikEntity
import com.b21cap0051.naratik.dataresource.local.model.ShopEntity
import com.b21cap0051.naratik.dataresource.remotedata.model.ImageUploadModel
import com.b21cap0051.naratik.dataresource.remotedata.model.PredictResponse
import com.b21cap0051.naratik.dataresource.remotedata.model.TechniquePredictResponse
import com.b21cap0051.naratik.util.vo.Resource
import com.b21cap0051.naratik.util.voapi.ApiResponse

interface NaratikRepoInterface
{
	//Batik
	fun GetAllBatik() : LiveData<Resource<List<BatikEntity>>>
	
	fun GetAllBatikRandomDb() : LiveData<Resource<List<BatikEntity>>>
	
	fun GetAllFavorite() : LiveData<Resource<List<BatikEntity>>>
	
	fun GetPopular() : LiveData<Resource<List<PopularBatikEntity>>>
	
	fun updateLikedBatik(value : BatikEntity)
	
	//Shop
	fun GetAllShop() : LiveData<Resource<PagedList<ShopEntity>>>
	
	//Upload
	fun InsertUploadImage(upload : ImageUploadModel)
	
	fun getInternetConnect() : LiveData<Resource<Boolean>>
	
	fun isDone() : LiveData<Resource<Boolean>>
	
	//Search
	fun searchBatik(id : String) : LiveData<Resource<List<BatikEntity>>>
	
	//Result prediction
	fun getPredictMotif(id : String) : LiveData<ApiResponse<PredictResponse>>
	
	fun getTechniquePredict(id : String) : LiveData<ApiResponse<TechniquePredictResponse>>
	
	fun isDoneMotif() : LiveData<Resource<Boolean>>
	
	fun isDoneTechnique() : LiveData<Resource<Boolean>>
	
	//History
	fun GetAllHistory() : LiveData<List<HistoryEntity>>
	
	fun insertHistory(value : HistoryEntity)
	
	fun deleteHistory(value : HistoryEntity)
	
	fun deleteAllHistory()
	
}