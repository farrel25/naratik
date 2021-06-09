package com.b21cap0051.naratik.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.b21cap0051.naratik.dataresource.NaratikRepository
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.dataresource.local.model.PopularBatikEntity
import com.b21cap0051.naratik.util.vo.Resource

class BatikMainView(private val Repository : NaratikRepository) : ViewModel()
{
	
	fun getAllbatik() : LiveData<Resource<List<BatikEntity>>> =
		Repository.GetAllBatik()
	
	fun getPopularbatik() : LiveData<Resource<List<PopularBatikEntity>>> =
		Repository.GetPopular()
	
	fun getAllbatikRandom() : LiveData<Resource<List<BatikEntity>>> =
		Repository.GetAllBatikRandomDb()
	
	fun setFavorite(model : BatikEntity) = Repository.updateLikedBatik(model)
	
	fun getAllFavoriteDb() : LiveData<Resource<List<BatikEntity>>> = Repository.GetAllFavorite()
	
}