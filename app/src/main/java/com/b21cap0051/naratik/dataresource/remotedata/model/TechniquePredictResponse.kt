package com.b21cap0051.naratik.dataresource.remotedata.model

import com.google.gson.annotations.SerializedName

data class TechniquePredictResponse
	(
	@field:SerializedName("img_url")
	val imgUrl: String? = null,
	
	@field:SerializedName("technique")
	val technique: List<TechniqueItem>? = null,
	
	@field:SerializedName("id")
	val id: String? = null
	
	)