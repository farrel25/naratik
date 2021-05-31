package com.b21cap0051.naratik.dataresource.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class ProductEntity(
	
	var id : Int = 0 ,
	var name : String = "" ,
	var motif : String = "" ,
	var category : String = "",
	var image : String = ""  ,
	var rating : Double = 0.0 ,
	var price : Int = 0,
	var isFavorite : Int = 0
                       )