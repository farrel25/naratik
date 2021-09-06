package com.naratik.batikapps.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naratik.batikapps.core.remote.model.PredictResponse
import com.naratik.batikapps.core.remote.model.TechniquePredictResponse
import com.naratik.batikapps.domain.usecase.NaratikUseCase
import com.naratik.batikapps.util.ResultStateData
import com.naratik.batikapps.util.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PredictMainView @Inject constructor(private val useCase : NaratikUseCase) : ViewModel()
{
	fun getMotif(id : String) : LiveData<ResultStateData<PredictResponse>>
	{
		val resultMotif =
			MutableLiveData<ResultStateData<PredictResponse>>()
		viewModelScope.launch {
			
			val data = useCase.getPredictMotif(id)
			data.collect {
				try
				{
					resultMotif.postValue(it)
				} catch (e : Exception)
				{
					resultMotif.postValue(ResultStateData.Failure(null , "${e.message}"))
				}
				
			}
			
		}
		return resultMotif
	}
	
	fun getTechnique(id : String) : LiveData<ResultStateData<TechniquePredictResponse>>
	{
		val resultTechnique = MutableLiveData<ResultStateData<TechniquePredictResponse>>()
		viewModelScope.launch {
			val data = useCase.getTechniquePredict(id)
			data.collect {
				try
				{
					resultTechnique.value = it
				} catch (e : Exception)
				{
					resultTechnique.value = ResultStateData.Failure(null , "${e.message}")
				}
			}
			
		}
		return resultTechnique
	}
	
	
	fun isDoneMotif() : LiveData<SingleEvent<ResultStateData<Boolean>>>
	{
		val dataSingle = MutableLiveData<SingleEvent<ResultStateData<Boolean>>>()
		viewModelScope.launch(Dispatchers.IO) {
			useCase.isDoneMotif().collect {
				dataSingle.postValue(SingleEvent(it))
			}
		}
		return dataSingle
	}
	
	fun isDoneTechnique() : LiveData<SingleEvent<ResultStateData<Boolean>>>
	{
		val dataSingle = MutableLiveData<SingleEvent<ResultStateData<Boolean>>>()
		viewModelScope.launch(Dispatchers.IO) {
			useCase.isDoneTechnique().collect {
				dataSingle.postValue(SingleEvent(it))
			}
			
		}
		return dataSingle
	}
	
}