package com.b21cap0051.naratik.dataresource.remotedata.model

import com.google.gson.annotations.SerializedName

data class PredictResponse(
	@field:SerializedName("id")
	var id_predict : String? = null,
	
	@field:SerializedName("img_url")
	var img_url : String? = null,
	
	@field:SerializedName("motif")
	var motifResult : List<MotifResponseItem>
						  )