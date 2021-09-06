package com.naratik.batikapps.di

import com.naratik.batikapps.core.remote.RemoteSource
import com.naratik.batikapps.core.remote.RemoteSourceInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module(includes = [RemoteDataModule::class,RemotePredictModule::class])
abstract class RemoteSourceModule
{
	
	@Binds
	abstract fun provideRemoteSource(mRemote : RemoteSource):RemoteSourceInterface
}