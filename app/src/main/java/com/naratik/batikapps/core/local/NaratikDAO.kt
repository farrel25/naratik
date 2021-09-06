package com.naratik.batikapps.core.local


import androidx.room.*
import com.naratik.batikapps.core.local.model.BatikEntity
import com.naratik.batikapps.core.local.model.HistoryEntity
import com.naratik.batikapps.core.local.model.PopularBatikEntity
import com.naratik.batikapps.core.local.model.ShopEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NaratikDAO
{
	@Query("SELECT * FROM Table_Batik ORDER BY id ASC")
	fun getAllBatikDb() : Flow<List<BatikEntity>>
	
	@Query("SELECT * FROM Table_Batik ORDER BY id LIMIT 4")
	fun getAllBatikRandomDb() : Flow<List<BatikEntity>>
	
	
	@Query("SELECT * FROM Table_Batik WHERE favourite = 1")
	fun getCheckFavoriteBatik() : Flow<List<BatikEntity>>
	
	
	@Query("SELECT * FROM Shop_Table ORDER BY id")
	fun getAllQueryShop() : Flow<List<ShopEntity>>
	
	
	@Query("SELECT * FROM Popular_Batik_Table ORDER BY id ASC")
	fun getAllPopularBatikDb() : Flow<List<PopularBatikEntity>>
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun addBatikDb(value : List<BatikEntity>)
	
	@Update
	suspend fun updateBatikDb(value : BatikEntity)
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun addBatikPopularDb(value : List<PopularBatikEntity>)
	
	
	@Query("SELECT * FROM Table_Batik WHERE nama_batik LIKE '%' || :namabatik || '%'")
	fun searchBatik(namabatik : String) : Flow<List<BatikEntity>>
	
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun addHistory(value : HistoryEntity)
	
	@Delete
	suspend fun delHistory(value : HistoryEntity)
	
	@Query("DELETE FROM history_table")
	suspend fun delAllHistory()
	
	@Query("SELECT * FROM history_table ORDER BY id DESC")
	fun getAllQueryHistory() : Flow<List<HistoryEntity>>
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertShop(value : List<ShopEntity>)
	
}