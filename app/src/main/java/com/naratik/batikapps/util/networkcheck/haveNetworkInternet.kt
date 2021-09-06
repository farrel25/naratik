package com.naratik.batikapps.util.networkcheck

import android.util.Log
import java.io.IOException
import java.net.InetSocketAddress
import javax.net.SocketFactory

object haveNetworkInternet
{
	fun execute(socketFactory : SocketFactory) : Boolean
	{
		return try
		{
			Log.d("SOCKET STATUS" , "Ping Google")
			val socket = socketFactory.createSocket() ?: throw IOException("null")
			socket.connect(InetSocketAddress("8.8.8.8" , 53) , 1500)
			socket.close()
			true
		} catch (e : Exception)
		{
			Log.e("SOCKET STATUS" , "No internet connection. ${e.message}")
			false
		}
	}
}