package com.naratik.batikapps.di

import android.content.Context
import androidx.room.Room
import com.naratik.batikapps.core.local.NaratikDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule
{
	
	@Singleton
	@Provides
	fun createDatabaseProvide(@ApplicationContext ctx : Context) : NaratikDB
	{
		return Room.databaseBuilder(
			ctx ,
			NaratikDB::class.java ,
			"naratik.db"
		                           ).fallbackToDestructiveMigration().build()
		
	}
	
	@Provides
	fun provideDaoNaratik(db : NaratikDB) = db.PrimaryDAO()
	
}


