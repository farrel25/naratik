package com.naratik.batikapps.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.naratik.batikapps.core.remote.model.ImageUploadModel
import com.naratik.batikapps.domain.usecase.NaratikUseCase
import com.naratik.batikapps.util.ResultStateData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UploadMainView @Inject constructor(private val useCase : NaratikUseCase) : ViewModel()
{
	fun uploadFile(ctx : Context, model : ImageUploadModel)
	{
		viewModelScope.launch(Dispatchers.IO) {
			useCase.insertUploadImage(ctx,model)
		}
	}
	
	fun isConnectedNetwork() : LiveData<ResultStateData<Boolean>> = useCase.getInternetConnect().asLiveData()
	
	fun isDoneUpload() : LiveData<ResultStateData<Boolean>> = useCase.isDone().asLiveData()
}