package com.b21cap0051.naratik.dataresource.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_table")
data class HistoryEntity(
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	var id : Int = 0 ,
	
	@ColumnInfo(name = "history")
	var history : String? = null
                        )