package com.b21cap0051.naratik.dataresource.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Shop_Table")
data class ShopEntity(
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	var id : Int? = null ,
	
	@ColumnInfo(name = "namaToko")
	var namaToko : String? = null ,
	
	@ColumnInfo(name = "product")
	var productToko : String? = null ,
	
	@ColumnInfo(name = "alamatToko")
	var alamatToko : String? = null

                     )
