package com.naratik.batikapps

import com.naratik.batikapps.domain.interactor.NaratikInteractor
import com.naratik.batikapps.domain.usecase.NaratikUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule
{
	@Binds
	@ViewModelScoped
	abstract fun ViewModelProvide(naratikInteractor : NaratikInteractor) : NaratikUseCase
}