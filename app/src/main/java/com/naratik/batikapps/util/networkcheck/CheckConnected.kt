package com.naratik.batikapps.util.networkcheck

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object CheckConnected
{
	fun isConnected(ctx : Context) : Int
	{
		val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
		{
			if (cm != null)
			{
				val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
				if (capabilities != null)
				{
					if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
					{
						return 1
					} else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
					{
						return 2
					} else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN))
					{
						return 3
					}
				}
			}
		} else
		{
			val activeNetwork = cm.activeNetworkInfo
			if (activeNetwork != null)
			{
				when (activeNetwork.type)
				{
					ConnectivityManager.TYPE_WIFI   ->
					{
						return 1
					}
					ConnectivityManager.TYPE_MOBILE ->
					{
						return 2
					}
					ConnectivityManager.TYPE_VPN    ->
					{
						return 3
					}
				}
			}
		}
		return 0
	}
	
}