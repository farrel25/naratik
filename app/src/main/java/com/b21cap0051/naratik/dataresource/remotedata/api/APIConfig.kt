package com.b21cap0051.naratik.dataresource.remotedata.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIConfig
{
	companion object
	{
		fun ApiPredict(): PredictService
		{
			val interceptor = HttpLoggingInterceptor()
			val Client = OkHttpClient.Builder()
				.addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
				.connectTimeout(2,TimeUnit.MINUTES)
				.readTimeout(2,TimeUnit.MINUTES)
				.writeTimeout(2,TimeUnit.MINUTES)
				.build()
			
			val API = Retrofit.Builder()
				.baseUrl("")
				.addConverterFactory(GsonConverterFactory.create())
				.client(Client)
				.build()
			
			return API.create(PredictService::class.java)
		}
		
		fun ApiData():DataServices{
			val Logging = HttpLoggingInterceptor()
			val client = OkHttpClient.Builder()
				.addInterceptor(Logging.setLevel(HttpLoggingInterceptor.Level.BODY))
				.connectTimeout(2,TimeUnit.MINUTES)
				.readTimeout(2,TimeUnit.MINUTES)
				.writeTimeout(2,TimeUnit.MINUTES)
				.build()
			
			val retro = Retrofit.Builder()
				.baseUrl("http://batikita.herokuapp.com/index.php")
				.addConverterFactory(GsonConverterFactory.create())
				.client(client)
				.build()
			
			return retro.create(DataServices::class.java)
			
		}
		
	}
	
	
}