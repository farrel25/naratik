package com.b21cap0051.naratik.mainview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.b21cap0051.naratik.dataresource.NaratikRepository
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.util.vo.Resource

class BatikMainView(private val Repository : NaratikRepository) : ViewModel()
{
	
	fun getAllbatik() : LiveData<Resource<PagedList<BatikEntity>>> =
		Repository.GetAllBatik()
	
	fun getLimitedBatik() : LiveData<Resource<PagedList<BatikEntity>>> =
		Repository.GetLimitedBatik()
	
	fun addFavor(model : BatikEntity) = Repository.AddLikedBatik(model)

	fun checkFavorite():LiveData<List<BatikEntity>> = Repository.GetCheckFavorite()
	
	fun getFavourAllbatik() : LiveData<Resource<PagedList<BatikEntity>>> =
		Repository.GetAllFavorite()
	
}