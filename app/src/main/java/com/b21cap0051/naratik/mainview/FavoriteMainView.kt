package com.b21cap0051.naratik.mainview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.b21cap0051.naratik.dataresource.NaratikRepository
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.util.vo.Resource

class FavoriteMainView(private val repo : NaratikRepository):ViewModel()
{
	fun GetFavourite(): LiveData<Resource<PagedList<BatikEntity>>> = repo.GetAllFavorite()
	
	fun addFavor(model : BatikEntity) = repo.AddLikedBatik(model)
	
	fun checkFavorite():LiveData<List<BatikEntity>> = repo.GetCheckFavorite()
}