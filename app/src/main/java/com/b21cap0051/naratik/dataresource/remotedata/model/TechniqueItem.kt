package com.b21cap0051.naratik.dataresource.remotedata.model

import com.google.gson.annotations.SerializedName

data class TechniqueItem
	(
	@field:SerializedName("techniqueName")
	val techniqueName: String? = null,
	
	@field:SerializedName("value")
	val value: Double? = null
	)