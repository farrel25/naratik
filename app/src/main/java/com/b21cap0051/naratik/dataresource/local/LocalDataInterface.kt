package com.b21cap0051.naratik.dataresource.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.dataresource.local.model.HistoryEntity
import com.b21cap0051.naratik.dataresource.local.model.PopularBatikEntity
import com.b21cap0051.naratik.dataresource.local.model.ShopEntity

interface LocalDataInterface
{
	//Batik
	fun getAllBatik() : DataSource.Factory<Int , BatikEntity>
	fun getLimitedBatik() : DataSource.Factory<Int , BatikEntity>
	fun getAllPopularBatik() : LiveData<List<PopularBatikEntity>>
	fun insertBatik(value : List<BatikEntity>)
	fun insertPopularBatik(value : List<PopularBatikEntity>)
	fun updatePopularBatik(value : PopularBatikEntity)
	
	//Favorite
	fun checkFavouriteBatik() : LiveData<List<BatikEntity>>
	fun getAllFavoriteBatik():DataSource.Factory<Int,BatikEntity>
	fun setAllFavoriteBatik(value : BatikEntity)
	
	//Article
	
	//Store
	fun getAllShop(): DataSource.Factory<Int,ShopEntity>
	fun insertShop(value : List<ShopEntity>)
	
	//Search
	fun searchData(value : String) : LiveData<List<BatikEntity>>
	fun insertHistory(value : HistoryEntity)
	fun deleteHistory(value : HistoryEntity)
	fun deleteAllHistory()
	fun getAllHistory():LiveData<List<HistoryEntity>>
}