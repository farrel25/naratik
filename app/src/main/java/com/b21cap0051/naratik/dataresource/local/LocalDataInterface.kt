package com.b21cap0051.naratik.dataresource.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.dataresource.local.model.HistoryEntity
import com.b21cap0051.naratik.dataresource.local.model.PopularBatikEntity
import com.b21cap0051.naratik.dataresource.local.model.ShopEntity

interface LocalDataInterface
{
	fun GetAllBatik() : DataSource.Factory<Int , BatikEntity>
	fun CheckFavouriteBatik() : LiveData<List<BatikEntity>>
	fun GetAllFavorite():DataSource.Factory<Int,BatikEntity>
	fun GetLimitedBatik() : DataSource.Factory<Int , BatikEntity>
	fun GetAllPopularBatik() : LiveData<List<PopularBatikEntity>>
	fun InsertBatik(value : List<BatikEntity>)
	fun insertPopularBatik(value : List<PopularBatikEntity>)
	fun updateBatik(value : BatikEntity)
	fun updatePopularBatik(value : PopularBatikEntity)
	fun searchData(value : String) : LiveData<List<BatikEntity>>
	fun InsertHistory(value : HistoryEntity)
	fun DeleteHistory(value : HistoryEntity)
	fun DeleteAllHistory()
	fun GetAllHistory():LiveData<List<HistoryEntity>>
	fun GetAllShop(): DataSource.Factory<Int,ShopEntity>
	fun InsertShop(value : List<ShopEntity>)
}