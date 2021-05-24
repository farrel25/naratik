package com.b21cap0051.naratik.dataresource.remotedata

import com.b21cap0051.naratik.dataresource.remotedata.StatusResponse.*

class ApiResponse<T>(val statusResponse : StatusResponse,val body : T,val message : String?)
{
	companion object{
		fun <T> success(body : T):ApiResponse<T> = ApiResponse(SUCCESS,body,null )
		fun <T> empty(message : String,body : T):ApiResponse<T> = ApiResponse(EMPTY,body,message)
		fun <T> error(message : String,body : T):ApiResponse<T> = ApiResponse(ERROR,body,message)
	}
}