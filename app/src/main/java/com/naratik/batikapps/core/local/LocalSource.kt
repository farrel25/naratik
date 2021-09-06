package com.naratik.batikapps.core.local

import com.naratik.batikapps.core.local.model.BatikEntity
import com.naratik.batikapps.core.local.model.HistoryEntity
import com.naratik.batikapps.core.local.model.PopularBatikEntity
import com.naratik.batikapps.core.local.model.ShopEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalSource @Inject constructor(private val mNaratikDao : NaratikDAO) :
	LocalSourceInterface
{
	override fun getAllBatik() : Flow<List<BatikEntity>>
	{
		return mNaratikDao.getAllBatikDb()
	}
	
	override fun getAllBatikRandomLimit() : Flow<List<BatikEntity>>
	{
		return mNaratikDao.getAllBatikRandomDb()
	}
	
	override suspend fun insertBatik(value : List<BatikEntity>)
	{
		mNaratikDao.addBatikDb(value)
	}
	
	override fun getAllPopularBatik() : Flow<List<PopularBatikEntity>>
	{
		return mNaratikDao.getAllPopularBatikDb()
	}
	
	override suspend fun insertPopularBatik(value : List<PopularBatikEntity>)
	{
		mNaratikDao.addBatikPopularDb(value)
	}
	
	override fun checkFavouriteBatik() : Flow<List<BatikEntity>>
	{
		return mNaratikDao.getCheckFavoriteBatik()
	}
	
	override suspend fun setFavoriteBatik(value : BatikEntity)
	{
		mNaratikDao.updateBatikDb(value)
	}
	
	override fun getAllShop() : Flow<List<ShopEntity>>
	{
		return mNaratikDao.getAllQueryShop()
	}
	
	override suspend fun insertShop(value : List<ShopEntity>)
	{
		mNaratikDao.insertShop(value)
	}
	
	override fun searchData(value : String) : Flow<List<BatikEntity>>
	{
		return mNaratikDao.searchBatik(value)
	}
	
	override suspend fun insertHistory(value : HistoryEntity)
	{
		mNaratikDao.addHistory(value)
	}
	
	override suspend fun deleteHistory(value : HistoryEntity)
	{
		mNaratikDao.delHistory(value)
	}
	
	override suspend fun deleteAllHistory()
	{
		mNaratikDao.delAllHistory()
	}
	
	override fun getAllHistory() : Flow<List<HistoryEntity>>
	{
		return mNaratikDao.getAllQueryHistory()
	}
}