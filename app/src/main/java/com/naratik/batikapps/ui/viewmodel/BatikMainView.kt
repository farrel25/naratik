package com.naratik.batikapps.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.naratik.batikapps.core.local.model.BatikEntity
import com.naratik.batikapps.core.local.model.PopularBatikEntity
import com.naratik.batikapps.domain.usecase.NaratikUseCase
import com.naratik.batikapps.util.ResultStateData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BatikMainView @Inject constructor(private val useCase : NaratikUseCase) : ViewModel()
{
	
	fun getAllbatik() : LiveData<ResultStateData<List<BatikEntity>>> =
		useCase.getAllBatik().asLiveData()
	
	fun getPopularbatik() : LiveData<ResultStateData<List<PopularBatikEntity>>> =
		useCase.getPopular().asLiveData()
	
	fun getAllbatikRandom() : LiveData<ResultStateData<List<BatikEntity>>> =
		useCase.getAllBatikRandomDb().asLiveData()
	
	fun setFavorite(model : BatikEntity)
	{
		viewModelScope.launch(Dispatchers.IO) {
			useCase.updateLikedBatik(model)
		}
	}
	
	fun getAllFavoriteDb() : LiveData<ResultStateData<List<BatikEntity>>> =
		useCase.getAllFavorite().asLiveData()
	
}