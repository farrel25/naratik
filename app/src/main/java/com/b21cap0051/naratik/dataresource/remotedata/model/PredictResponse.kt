package com.b21cap0051.naratik.dataresource.remotedata.model

import com.google.gson.annotations.SerializedName

data class PredictResponse(
	@field:SerializedName("motif")
	var motifResult : List<MotifResponseItem>
						  )