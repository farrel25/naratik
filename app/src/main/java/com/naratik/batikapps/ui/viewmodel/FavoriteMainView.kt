package com.naratik.batikapps.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.naratik.batikapps.core.local.model.BatikEntity
import com.naratik.batikapps.domain.usecase.NaratikUseCase
import com.naratik.batikapps.util.ResultStateData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteMainView @Inject constructor(private val useCase : NaratikUseCase) : ViewModel()
{
	
	fun getAllFavourite() : LiveData<ResultStateData<List<BatikEntity>>> = useCase.getAllFavorite().asLiveData()
	
	fun setFavorite(model : BatikEntity) {
		viewModelScope.launch(Dispatchers.IO){
			useCase.updateLikedBatik(model)
		}
	}
	
	
}