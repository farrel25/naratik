package com.naratik.batikapps.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naratik.batikapps.core.local.model.BatikEntity
import com.naratik.batikapps.core.local.model.HistoryEntity
import com.naratik.batikapps.domain.usecase.NaratikUseCase
import com.naratik.batikapps.util.ResultStateData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchMainView @Inject constructor(private val useCase : NaratikUseCase) : ViewModel()
{
	
	fun getSearch(text : String) : LiveData<ResultStateData<List<BatikEntity>>>
	{
		val searchResult = MutableLiveData<ResultStateData<List<BatikEntity>>>()
		
		viewModelScope.launch {
			val dataSearch = useCase.searchBatik(text)
			dataSearch.collect {
				try
				{
					searchResult.postValue(it)
				} catch (e : Exception)
				{
					searchResult.postValue(ResultStateData.Failure(null , "${e.message}"))
				}
			}
		}
		return searchResult
	}
	
	fun addHistory(value : HistoryEntity)
	{
		viewModelScope.launch(Dispatchers.IO) {
			useCase.insertHistory(value)
		}
	}
	
	fun delHistory(value : HistoryEntity)
	{
		viewModelScope.launch(Dispatchers.IO) {
			useCase.deleteHistory(value)
		}
	}
	
	fun setFavorite(model : BatikEntity)
	{
		viewModelScope.launch(Dispatchers.IO) {
			useCase.updateLikedBatik(model)
		}
	}
	
	fun getALLHistory() : LiveData<ResultStateData<List<HistoryEntity>>>
	{
		val historyResult = MutableLiveData<ResultStateData<List<HistoryEntity>>>()
		viewModelScope.launch(Dispatchers.IO) {
			val data = useCase.getAllHistory()
			data.collect {
				try
				{
					historyResult.postValue(it)
				} catch (e : Exception)
				{
					historyResult.postValue(ResultStateData.Failure(null , "${e.message}"))
				}
			}
		}
		return historyResult
	}
	
}