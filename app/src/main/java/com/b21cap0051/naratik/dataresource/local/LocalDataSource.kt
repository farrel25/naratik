package com.b21cap0051.naratik.dataresource.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.dataresource.local.model.HistoryEntity
import com.b21cap0051.naratik.dataresource.local.model.PopularBatikEntity
import com.b21cap0051.naratik.dataresource.local.model.ShopEntity

class LocalDataSource(private val mNaratikDao : NaratikDAO) : LocalDataInterface
{
	
	companion object
	{
		@Volatile
		private var Instance : LocalDataSource? = null
		
		fun getInstance(dao : NaratikDAO) : LocalDataSource =
			Instance ?: synchronized(this) {
				Instance ?: LocalDataSource(dao).apply {
					Instance = this
				}
			}
	}
	
	//Batik
	override fun getAllBatik() : DataSource.Factory<Int , BatikEntity> = mNaratikDao.getAllBatikDb()
	
	override fun getLimitedBatik() : DataSource.Factory<Int , BatikEntity> =
		mNaratikDao.getLimitedBatik()
	
	
	override fun getAllPopularBatik() : LiveData<List<PopularBatikEntity>> =
		mNaratikDao.getAllPopularBatikDb()
	
	override fun insertBatik(value : List<BatikEntity>)
	{
		mNaratikDao.addBatikDb(value)
	}
	
	override fun updatePopularBatik(value : PopularBatikEntity)
	{
		mNaratikDao.updateBatikPopularDb(value)
	}
	
	override fun updateBatik(value : BatikEntity)
	{
		mNaratikDao.updateBatikDb(value)
	}
	
	
	//Favorite
	override fun checkFavouriteBatik() : LiveData<List<BatikEntity>> = mNaratikDao.getCheckFavoriteBatik()
	
	override fun getAllFavorite() : DataSource.Factory<Int , BatikEntity> = mNaratikDao.getAllFavoriteBatikDb()
	
	
	
	//Article
	override fun insertPopularBatik(value : List<PopularBatikEntity>)
	{
		mNaratikDao.addBatikPopularDb(value)
	}
	
	//Store
	override fun insertShop(value : List<ShopEntity>) = mNaratikDao.insertShop(value)
	
	//Search
	override fun searchData(value : String) : LiveData<List<BatikEntity>> = mNaratikDao.searchBatik(value)
	
	override fun insertHistory(value : HistoryEntity) = mNaratikDao.addHistory(value)
	
	override fun deleteHistory(value : HistoryEntity) = mNaratikDao.delHistory(value)
	
	override fun deleteAllHistory() = mNaratikDao.delAllHistory()
	
	override fun getAllHistory() = mNaratikDao.getAllQueryHistory()
	
	override fun getAllShop() : DataSource.Factory<Int , ShopEntity> =mNaratikDao.getAllQueryShop()
	
	
	
}