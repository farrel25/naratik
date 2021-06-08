package com.b21cap0051.naratik.dataresource.remotedata.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TechniqueItem
	(
	@field:SerializedName("techniqueName")
	@Expose
	val techniqueName : String? = null ,
	
	@field:SerializedName("value")
	@Expose
	val value : Double? = null
	)