package com.naratik.batikapps.di

import com.naratik.batikapps.core.remote.api.PredictService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module
@InstallIn(SingletonComponent::class)
class RemotePredictModule
{
	
	@Provides
	fun predictServicesAPI() : PredictService
	{
		val client = OkHttpClient.Builder()
			.addInterceptor(object : Interceptor
			{
				override fun intercept(chain : Interceptor.Chain) : Response
				{
					val original = chain.request()
					val builder = original.newBuilder()
						.addHeader("Auth" , "B21-CAP0051")
					return chain.proceed(builder.build())
				}
			})
			.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
			.connectTimeout(60 , TimeUnit.SECONDS)
			.readTimeout(60 , TimeUnit.SECONDS)
			.writeTimeout(60 , TimeUnit.SECONDS)
			.build()
		val retro = Retrofit.Builder()
			.addConverterFactory(GsonConverterFactory.create())
			.baseUrl("http://34.101.167.207:8080/")
			.client(client)
			.build()
		return retro.create(PredictService::class.java)
	}
	
}