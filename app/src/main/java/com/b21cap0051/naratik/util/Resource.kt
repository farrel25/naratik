package com.b21cap0051.naratik.util

import com.b21cap0051.naratik.dataresource.remotedata.StatusResponse


data class Resource<T>(
	val Status : StatusResponse,
	val Data : T? = null ,
	val message : String? = null
						)
{
	companion object{
		fun <T> Success(Data : T):Resource<T> = Resource(StatusResponse.SUCCESS , Data, null)
		fun <T> Loading(Data : T? = null):Resource<T> = Resource(StatusResponse.EMPTY , Data, null)
		fun <T> Error(message:String?,Data : T? = null):Resource<T> = Resource(StatusResponse.ERROR , Data, message)
	}
	
}