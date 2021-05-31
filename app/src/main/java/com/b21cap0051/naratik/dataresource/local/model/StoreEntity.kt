package com.b21cap0051.naratik.dataresource.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class StoreEntity(
	
	var id : Int = 0 ,
	var image : String = "" ,
	var backdropImage : String = "" ,
	var name : String = "" ,
	var phone : String = "" ,
	var rating : Double = 0.0 ,
	var location : String = "" ,
	var product_count : Int = 0 ,
                      )