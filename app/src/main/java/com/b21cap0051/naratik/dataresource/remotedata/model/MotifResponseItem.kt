package com.b21cap0051.naratik.dataresource.remotedata.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MotifResponseItem(
	@field:SerializedName("motifName")
	@Expose
	var motifName : String? = null,
	
	@field:SerializedName("value")
	@Expose
	var valueMotif : Double? = null,
							)
