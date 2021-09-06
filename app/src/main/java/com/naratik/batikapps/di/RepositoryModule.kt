package com.naratik.batikapps.di

import com.naratik.batikapps.domain.repository.NaratikRepository
import com.naratik.batikapps.domain.usecase.NaratikInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [DatabaseModule::class , RemoteSourceModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule
{
	@Binds
	abstract fun providesNaratikRepository(naratikRepo : NaratikRepository) : NaratikInterface
	
}