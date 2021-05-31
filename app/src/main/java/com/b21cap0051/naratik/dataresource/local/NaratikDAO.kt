package com.b21cap0051.naratik.dataresource.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.dataresource.local.model.PopularBatikEntity

@Dao
interface NaratikDAO
{
	@Query("SELECT * FROM Table_Batik ORDER BY id ASC")
	fun GetAllBatikDb() : DataSource.Factory<Int , BatikEntity>
	
	@Query("SELECT * FROM Table_Batik ORDER BY id ASC LIMIT 4")
	fun GetLimitedBatik() : DataSource.Factory<Int , BatikEntity>
	
	
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
	
}