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
	fun GetAllBatik() : LiveData<Resource<PagedList<BatikEntity>>>
	
	fun GetAllShop():LiveData<Resource<PagedList<ShopEntity>>>
	
	fun GetAllFavorite():LiveData<Resource<PagedList<BatikEntity>>>
	
	fun GetCheckFavorite():LiveData<List<BatikEntity>>
	
	
	fun GetLimitedBatik() : LiveData<Resource<PagedList<BatikEntity>>>
	
	fun GetPopular() : LiveData<Resource<List<PopularBatikEntity>>>
	
	fun InsertUploadImage(upload : ImageUploadModel)
	
	fun getInternetConnect() : LiveData<Resource<Boolean>>
	
	fun IsDone() : LiveData<Resource<Boolean>>
	
	fun searchBatik(id : String) : LiveData<Resource<List<BatikEntity>>>
	
	fun GetPredictMotif(id : String) : LiveData<ApiResponse<PredictResponse>>
	
	fun GetTechniquePredict(id : String) : LiveData<ApiResponse<TechniquePredictResponse>>
	
	fun IsDoneMotif() : LiveData<Resource<Boolean>>
	
	fun IsDoneTechnique() : LiveData<Resource<Boolean>>
	
	fun InsertHistory(value : HistoryEntity)
	
	fun DeleteHistory(value : HistoryEntity)
	
	fun DeleteAllHistory()
	
	fun AddLikedBatik(value : BatikEntity)
	
	fun DelLikeBatik(value : BatikEntity)
	
	fun GetAllHistory():LiveData<List<HistoryEntity>>
	
	
	
	
}