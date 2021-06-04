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
	fun GetAllBatikDb() : DataSource.Factory<Int , BatikEntity>
	
	@Query("SELECT * FROM Table_Batik WHERE favourite = 1")
	fun GetAllFavoriteBatikDb() : DataSource.Factory<Int , BatikEntity>
	
	@Query("SELECT * FROM Table_Batik WHERE favourite = 1")
	fun GetCheckFavoriteBatik() : LiveData<List<BatikEntity>>
	
	@Query("SELECT * FROM Table_Batik ORDER BY id ASC LIMIT 4")
	fun GetLimitedBatik() : DataSource.Factory<Int , BatikEntity>
	
	@Query("SELECT * FROM Shop_Table ORDER BY id")
	fun GetAllQueryShop():DataSource.Factory<Int,ShopEntity>
	
	
	@Query("SELECT * FROM Popular_Batik_Table ORDER BY id ASC")
	fun GetAllPopularBatikDb() : LiveData<List<PopularBatikEntity>>
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun AddBatikDb(value : List<BatikEntity>)
	
	@Update
	fun UpdateBatikDb(Value : BatikEntity)
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun AddBatikPopularDb(value : List<PopularBatikEntity>)
	
	@Update
	fun UpdateBatikPopularDb(Value : PopularBatikEntity)
	
	@Query("SELECT * FROM Table_Batik WHERE nama_batik LIKE '%' || :namabatik || '%'")
	fun searchBatik(namabatik : String) : LiveData<List<BatikEntity>>
	
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun AddHisstory(value : HistoryEntity)
	
	@Delete
	fun DelHistory(value : HistoryEntity)
	
	@Query("DELETE FROM history_table")
	fun DelAllHistory()
	
	@Query("SELECT * FROM history_table ORDER BY id")
	fun GetAllQueryHistory():LiveData<List<HistoryEntity>>
	
	
	@Insert(onConflict =  OnConflictStrategy.REPLACE)
	fun InsertShop(value : List<ShopEntity>)
	
	
}