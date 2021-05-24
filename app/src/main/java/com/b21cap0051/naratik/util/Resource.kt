package com.b21cap0051.naratik.util

sealed class Resource<T>(
	val Data : T? = null,
	val message : String? = null
						)
{
	class Success<T>(Data : T):Resource<T>(Data)
	class Loading<T>(Data : T? = null): Resource<T>(Data)
	class Error<T>(message:String?,Data : T? = null):Resource<T>(Data,message)
}