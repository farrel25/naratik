package com.b21cap0051.naratik.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.b21cap0051.naratik.dataresource.NaratikRepository
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.dataresource.local.model.HistoryEntity
import com.b21cap0051.naratik.util.vo.Resource

class SearchMainView(private val repo : NaratikRepository) : ViewModel()
{
	
	fun GetSearch(text : String) : LiveData<Resource<List<BatikEntity>>> = repo.searchBatik(text)
	
	fun AddHistory(value : HistoryEntity) = repo.insertHistory(value)
	
	fun DelHistory(value : HistoryEntity) = repo.deleteHistory(value)
	
	fun setFavorite(model : BatikEntity) = repo.updateLikedBatik(model)
	
	fun GetALLHistory() : LiveData<List<HistoryEntity>> = repo.GetAllHistory()
	
}