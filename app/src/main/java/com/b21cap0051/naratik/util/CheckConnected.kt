package com.b21cap0051.naratik.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class CheckConnected(private val ctx : Context)
{
	companion object
	{
		@Volatile
		private var Instance : CheckConnected? = null
		
		fun CreateInstance(ctx : Context) : CheckConnected =
			Instance ?: synchronized(this) {
				Instance ?: CheckConnected(ctx).apply {
					Instance = this
				}
			}
	}
	
	fun IsConnected() : Int
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
			if (cm != null)
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
		}
		return 0
	}
	
	
}