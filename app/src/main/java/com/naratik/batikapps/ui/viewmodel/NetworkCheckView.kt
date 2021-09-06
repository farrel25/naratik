package com.naratik.batikapps.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.naratik.batikapps.util.SingleEvent
import com.naratik.batikapps.util.networkcheck.ConnectedLivedata
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NetworkCheckView @Inject constructor(application: Application){
	private val connectionLivedata = ConnectedLivedata(application)

	private var _isNetworkAvaible = MutableLiveData<SingleEvent<Boolean?>>(SingleEvent(null))
	val isNetworkAvaible : LiveData<SingleEvent<Boolean?>> = _isNetworkAvaible
	
	
	fun registerConnectionObserver(lifecycleOwner : LifecycleOwner){
		connectionLivedata.observe(lifecycleOwner,{
			it?.let {
				_isNetworkAvaible.postValue(SingleEvent(it))
			}
		})
	}
	
	fun unregisterConnectionObserver(lifecycleOwner : LifecycleOwner){
		connectionLivedata.removeObservers(lifecycleOwner)
	}
}
