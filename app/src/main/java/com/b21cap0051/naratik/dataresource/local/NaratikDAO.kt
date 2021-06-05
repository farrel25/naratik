package com.b21cap0051.naratik.dataresource.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.dataresource.local.model.HistoryEntity
import com.b21cap0051.naratik.dataresource.local.model.PopularBatikEntity
import com.b21cap0051.naratik.dataresource.local.model.ShopEntity

@Dao
interface NaratikDAO
{
	@Query("SELECT * FROM Table_Batik ORDER BY id ASC")
	fun getAllBatikDb() : DataSource.Factory<Int , BatikEntity>
	
	@Query("SELECT * FROM Table_Batik WHERE favourite = 1")
	fun getAllFavoriteBatikDb() : DataSource.Factory<Int , BatikEntity>
	
	@Query("SELECT * FROM Table_Batik WHERE favourite = 1")
	fun getCheckFavoriteBatik() : LiveData<List<BatikEntity>>
	
	@Query("SELECT * FROM Table_Batik ORDER BY id ASC LIMIT 4")
	fun getLimitedBatik() : DataSource.Factory<Int , BatikEntity>
	
	@Query("SELECT * FROM Shop_Table ORDER BY id")
	fun getAllQueryShop():DataSource.Factory<Int,ShopEntity>
	
	
	@Query("SELECT * FROM Popular_Batik_Table ORDER BY id ASC")
	fun getAllPopularBatikDb() : LiveData<List<PopularBatikEntity>>
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun addBatikDb(value : List<BatikEntity>)
	
	@Update
	fun updateBatikDb(Value : BatikEntity)
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun addBatikPopularDb(value : List<PopularBatikEntity>)
	
	@Update
	fun updateBatikPopularDb(Value : PopularBatikEntity)
	
	@Query("SELECT * FROM Table_Batik WHERE nama_batik LIKE '%' || :namabatik || '%'")
	fun searchBatik(namabatik : String) : LiveData<List<BatikEntity>>
	
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun addHistory(value : HistoryEntity)
	
	@Delete
	fun delHistory(value : HistoryEntity)
	
	@Query("DELETE FROM history_table")
	fun delAllHistory()
	
	@Query("SELECT * FROM history_table ORDER BY id")
	fun getAllQueryHistory():LiveData<List<HistoryEntity>>
	
	
	@Insert(onConflict =  OnConflictStrategy.REPLACE)
	fun insertShop(value : List<ShopEntity>)
	
	
}