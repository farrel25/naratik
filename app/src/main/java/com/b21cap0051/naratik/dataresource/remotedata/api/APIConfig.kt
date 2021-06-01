package com.b21cap0051.naratik.dataresource.remotedata.api

import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIConfig
{
	companion object
	{
		fun ApiPredict() : PredictService
		{
			val interceptor = HttpLoggingInterceptor()
			val Client = OkHttpClient.Builder()
				.addInterceptor(object : Interceptor{
					override fun intercept(chain : Interceptor.Chain) : Response
					{
						val original = chain.request()
						val builder = original.newBuilder()
							.addHeader("Auth","B21-CAP0051")
						return chain.proceed(builder.build())
					}

				})
				.addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
				.connectTimeout(30 , TimeUnit.SECONDS)
				.readTimeout(30 , TimeUnit.SECONDS)
				.writeTimeout(30  , TimeUnit.SECONDS)
				.build()
			
			val API = Retrofit.Builder()
				.client(Client)
				.baseUrl("https://causal-folder-315209.et.r.appspot.com")
				.addConverterFactory(GsonConverterFactory.create())
				.build()
			
			return API.create(PredictService::class.java)
		}
		
		fun ApiData() : DataServices
		{
			val Logging = HttpLoggingInterceptor()
			val client = OkHttpClient.Builder()
				.addInterceptor(Logging.setLevel(HttpLoggingInterceptor.Level.BODY))
				.connectTimeout(1 , TimeUnit.MINUTES)
				.readTimeout(1 , TimeUnit.MINUTES)
				.writeTimeout(1 , TimeUnit.MINUTES)
				.build()
			
			val retro = Retrofit.Builder()
				.baseUrl("http://batikita.herokuapp.com/index.php/")
				.addConverterFactory(GsonConverterFactory.create())
				.client(client)
				.build()
			
			return retro.create(DataServices::class.java)
			
		}
		
	}
	
	
}