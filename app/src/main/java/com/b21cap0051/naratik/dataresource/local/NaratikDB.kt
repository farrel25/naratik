package com.b21cap0051.naratik.dataresource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.dataresource.local.model.HistoryEntity
import com.b21cap0051.naratik.dataresource.local.model.PopularBatikEntity


@Database(entities = [BatikEntity::class , PopularBatikEntity::class,HistoryEntity::class] , version = 1)
abstract class NaratikDB : RoomDatabase()
{
	abstract fun PrimaryDAO() : NaratikDAO
	
	companion object
	{
		@Volatile
		private var Instance : NaratikDB? = null
		
		fun CreateDB(ctx : Context) : NaratikDB =
			Instance ?: synchronized(this) {
				Room.databaseBuilder(
					ctx.applicationContext ,
					NaratikDB::class.java ,
					"NaratikDB"
				                    ).build().apply {
					Instance = this
				}
			}
	}
}