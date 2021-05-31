package com.b21cap0051.naratik.dataresource.remotedata.model

import com.google.gson.annotations.SerializedName

data class MotifResponseItem(
	@field:SerializedName("motifName")
	var motifName : String? = null,
	
	@field:SerializedName("values")
	var valueMotif : Double? = 0.0,
							)
