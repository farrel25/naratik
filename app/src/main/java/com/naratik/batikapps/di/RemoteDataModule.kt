package com.naratik.batikapps.di

import com.naratik.batikapps.core.remote.api.DataServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule
{
	@Provides
	fun dataServicesAPI() : DataServices
	{
		
		val client = OkHttpClient.Builder()
			.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
			.connectTimeout(1 , TimeUnit.MINUTES)
			.readTimeout(1 , TimeUnit.MINUTES)
			.writeTimeout(1 , TimeUnit.MINUTES)
			.build()
		
		val retro = Retrofit.Builder()
			.addConverterFactory(GsonConverterFactory.create())
			.baseUrl("http://batikita.herokuapp.com/index.php/")
			.client(client)
			.build()
		return retro.create(DataServices::class.java)
	}
	
}