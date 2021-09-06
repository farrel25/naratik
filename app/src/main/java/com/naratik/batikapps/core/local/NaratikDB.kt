package com.naratik.batikapps.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.naratik.batikapps.core.local.model.BatikEntity
import com.naratik.batikapps.core.local.model.HistoryEntity
import com.naratik.batikapps.core.local.model.PopularBatikEntity
import com.naratik.batikapps.core.local.model.ShopEntity


@Database(
	entities = [BatikEntity::class , PopularBatikEntity::class , HistoryEntity::class , ShopEntity::class] ,
	version = 1
         )
abstract class NaratikDB : RoomDatabase()
{
	abstract fun PrimaryDAO() : NaratikDAO
}