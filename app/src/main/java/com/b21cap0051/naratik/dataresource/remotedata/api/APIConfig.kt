package com.b21cap0051.naratik.dataresource.remotedata.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIConfig
{
	companion object
	{
		fun getBatikService(): ApiService
		{
			val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
			val Client = OkHttpClient.Builder()
				.addInterceptor(interceptor)
				.build()
			
			val API = Retrofit.Builder()
				.baseUrl("")
				.addConverterFactory(GsonConverterFactory.create())
				.client(Client)
				.build()
			
			return API.create(ApiService::class.java)
		}
		
	}
	
	
}