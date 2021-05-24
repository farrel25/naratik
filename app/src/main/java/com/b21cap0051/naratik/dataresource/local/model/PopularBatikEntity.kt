package com.b21cap0051.naratik.dataresource.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Popular_Batik_Table")
data class PopularBatikEntity(

	@PrimaryKey
	@ColumnInfo(name = "id")
	var batik_id : Int? = 0,
	
	@ColumnInfo(name = "nama_batik")
	var name_batik : String? = null,
	
	@ColumnInfo(name = "makna_batik")
	var makna_batik : String? = null,
	
	@ColumnInfo(name = "batik_pic")
	var Image : String? = null,
	
	@ColumnInfo(name = "daerah_batik")
	var daerah_batik : String? = null
							 )