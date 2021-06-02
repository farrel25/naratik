package com.b21cap0051.naratik.dataresource.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.dataresource.local.model.HistoryEntity
import com.b21cap0051.naratik.dataresource.local.model.PopularBatikEntity

class LocalDataSource(private val mNaratikDao : NaratikDAO) : LocalDataInterface
{
	
	companion object
	{
		@Volatile
		private var Instance : LocalDataSource? = null
		
		fun GetIntansce(dao : NaratikDAO) : LocalDataSource =
			Instance ?: synchronized(this) {
				Instance ?: LocalDataSource(dao).apply {
					Instance = this
				}
			}
	}
	
	override fun GetAllBatik() : DataSource.Factory<Int , BatikEntity> = mNaratikDao.GetAllBatikDb()
	override fun GetLimitedBatik() : DataSource.Factory<Int , BatikEntity> =
		mNaratikDao.GetLimitedBatik()
	
	
	override fun GetAllPopularBatik() : LiveData<List<PopularBatikEntity>> =
		mNaratikDao.GetAllPopularBatikDb()
	
	override fun InsertBatik(value : List<BatikEntity>)
	{
		mNaratikDao.AddBatikDb(value)
	}
	
	override fun insertPopularBatik(value : List<PopularBatikEntity>)
	{
		mNaratikDao.AddBatikPopularDb(value)
	}
	
	override fun updateBatik(value : BatikEntity)
	{
		mNaratikDao.UpdateBatikDb(value)
	}
	
	override fun updatePopularBatik(value : PopularBatikEntity)
	{
		mNaratikDao.UpdateBatikPopularDb(value)
	}
	
	override fun searchData(value : String) : LiveData<List<BatikEntity>> =
		mNaratikDao.searchBatik(value)
	
	override fun InsertHistory(value : HistoryEntity) = mNaratikDao.AddHisstory(value)
	
	override fun DeleteHistory(value : HistoryEntity) = mNaratikDao.DelHistory(value)
	
	override fun DeleteAllHistory() = mNaratikDao.DelAllHistory()
	
	override fun GetAllHistory() = mNaratikDao.GetAllQueryHistory()
	
	
}