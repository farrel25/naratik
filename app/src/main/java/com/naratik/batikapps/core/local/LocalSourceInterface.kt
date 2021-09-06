package com.naratik.batikapps.core.local

import com.naratik.batikapps.core.local.model.BatikEntity
import com.naratik.batikapps.core.local.model.HistoryEntity
import com.naratik.batikapps.core.local.model.PopularBatikEntity
import com.naratik.batikapps.core.local.model.ShopEntity
import kotlinx.coroutines.flow.Flow

interface LocalSourceInterface
{
	//Batik
	fun getAllBatik() : Flow<List<BatikEntity>>
	fun getAllBatikRandomLimit() : Flow<List<BatikEntity>>
	suspend fun insertBatik(value : List<BatikEntity>)
	
	
	//Popular Batik
	fun getAllPopularBatik() : Flow<List<PopularBatikEntity>>
	suspend fun insertPopularBatik(value : List<PopularBatikEntity>)
	
	//Favorite
	fun checkFavouriteBatik() : Flow<List<BatikEntity>>
	suspend fun setFavoriteBatik(value : BatikEntity)
	
	//Article
	
	//Store
	fun getAllShop() : Flow<List<ShopEntity>>
	suspend fun insertShop(value : List<ShopEntity>)
	
	//Search
	fun searchData(value : String) : Flow<List<BatikEntity>>
	suspend fun insertHistory(value : HistoryEntity)
	suspend fun deleteHistory(value : HistoryEntity)
	suspend fun deleteAllHistory()
	fun getAllHistory() : Flow<List<HistoryEntity>>
}