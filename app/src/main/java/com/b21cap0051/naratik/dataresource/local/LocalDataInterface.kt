package com.b21cap0051.naratik.dataresource.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.dataresource.local.model.HistoryEntity
import com.b21cap0051.naratik.dataresource.local.model.PopularBatikEntity
import com.b21cap0051.naratik.dataresource.local.model.ShopEntity
import com.b21cap0051.naratik.util.vo.Resource

interface LocalDataInterface
{
	//Batik
	fun getAllBatik() : LiveData<List<BatikEntity>>
	fun getAllBatikRandomLimit() : LiveData<List<BatikEntity>>
	fun insertBatik(value : List<BatikEntity>)
	
	
	
	//Popular Batik
	fun getAllPopularBatik() : LiveData<List<PopularBatikEntity>>
	fun insertPopularBatik(value : List<PopularBatikEntity>)
	
	//Favorite
	fun checkFavouriteBatik() : LiveData<List<BatikEntity>>
	fun setFavoriteBatik(value : BatikEntity)
	
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