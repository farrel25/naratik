package com.naratik.batikapps.di

import android.content.Context
import com.naratik.batikapps.MyApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ConnectManager
{
	
	@Singleton
	@Provides
	fun provideApplication(@ApplicationContext app: Context): MyApplication{
		return app as MyApplication
	}
	
}