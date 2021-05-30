package com.b21cap0051.naratik.util.vo


data class Resource<T>(
	val Status : Status ,
	val Data : T? = null ,
	val message : String? = null
                      )
{
	companion object
	{
		fun <T> Success(Data : T) : Resource<T> = Resource(Status.SUCCESS , Data , null)
		fun <T> Loading(Data : T? = null) : Resource<T> = Resource(Status.LOADING , Data , null)
		fun <T> Error(message : String? , Data : T? = null) : Resource<T> =
			Resource(Status.ERROR , Data , message)
	}
	
}