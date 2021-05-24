package com.b21cap0051.naratik.dataresource.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.dataresource.local.model.PopularBatikEntity

@Dao
interface NaratikDAO
{
	@Query("SELECT * FROM Table_Batik ORDER BY id ASC")
	fun GetAllBatikDb() : LiveData<List<BatikEntity>>
	
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
	
}