package com.naratik.batikapps.util.networkcheck

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkRequest
import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConnectedLivedata(ctx : Context) : LiveData<Boolean>()
{
	
	private lateinit var networkCallBack : ConnectivityManager.NetworkCallback
	private val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
	private val validNetwork : MutableSet<Network> = HashSet()
	
	private fun checkValidNetwork()
	{
		postValue(validNetwork.size > 0)
	}
	
	override fun onActive()
	{
		networkCallBack = createNetworkCallback()
		val networkRequest = NetworkRequest.Builder()
			.addCapability(NET_CAPABILITY_INTERNET)
			.build()
		cm.registerNetworkCallback(networkRequest , networkCallBack)
	}
	
	override fun onInactive()
	{
		cm.unregisterNetworkCallback(networkCallBack)
	}
	
	private fun createNetworkCallback() = object : ConnectivityManager.NetworkCallback()
	{
		override fun onAvailable(network : Network)
		{
			val networkCapabilities = cm.getNetworkCapabilities(network)
			val hasInternetCapability = networkCapabilities?.hasCapability(NET_CAPABILITY_INTERNET)
			if (hasInternetCapability == true)
			{
				CoroutineScope(Dispatchers.IO).launch {
					val hasInternet = haveNetworkInternet.execute(network.socketFactory)
					if (hasInternet)
					{
						withContext(Dispatchers.Main) {
							Log.d("NETWORK STATUS" , "onAvailable: adding network. ${network}")
							validNetwork.add(network)
							checkValidNetwork()
						}
					}
				}
			}
		}
		
		override fun onLost(network : Network)
		{
			Log.d("NETWORK STATUS", "onLost: ${network}")
			validNetwork.remove(network)
			checkValidNetwork()
		}
	}
	
	
	
}