package com.naratik.batikapps.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.naratik.batikapps.core.local.model.ShopEntity
import com.naratik.batikapps.domain.usecase.NaratikUseCase
import com.naratik.batikapps.util.ResultStateData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShopMainView @Inject constructor(private val useCase : NaratikUseCase) : ViewModel()
{
	fun getAllShop():LiveData<ResultStateData<List<ShopEntity>>> = useCase.getAllShop().asLiveData()
}