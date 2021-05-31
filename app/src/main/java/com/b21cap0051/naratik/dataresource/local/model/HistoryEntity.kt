package com.b21cap0051.naratik.dataresource.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class HistoryEntity(
	var id : Int = 0 ,
	var history : String = "")