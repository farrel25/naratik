package com.b21cap0051.naratik.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.b21cap0051.naratik.dataresource.NaratikRepository
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.util.vo.Resource

class FavoriteMainView(private val repo : NaratikRepository) : ViewModel()
{
	
	fun getAllFavourite() : LiveData<Resource<List<BatikEntity>>> = repo.GetAllFavorite()
	
	fun setFavorite(model : BatikEntity) = repo.updateLikedBatik(model)
	
	
}