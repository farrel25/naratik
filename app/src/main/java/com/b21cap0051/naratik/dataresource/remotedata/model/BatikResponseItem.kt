package com.b21cap0051.naratik.dataresource.remotedata.model

import com.google.gson.annotations.SerializedName

data class BatikResponseItem(
	
	@field:SerializedName("daerah_batik")
	val daerahBatik : String? = null ,
	
	@field:SerializedName("nama_batik")
	val namaBatik : String? = null ,
	
	@field:SerializedName("makna_batik")
	val maknaBatik : String? = null ,
	
	@field:SerializedName("link_batik")
	val linkBatik : String? = null ,
	
	@field:SerializedName("id")
	val id : Int? = null
                            )
