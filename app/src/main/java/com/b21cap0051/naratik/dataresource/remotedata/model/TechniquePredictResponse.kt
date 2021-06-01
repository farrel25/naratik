package com.b21cap0051.naratik.dataresource.remotedata.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TechniquePredictResponse
	(
	@field:SerializedName("img_url")
	@Expose
	val imgUrl: String? = null,
	
	@field:SerializedName("technique")
	@Expose
	val technique: List<TechniqueItem>? = null,
	
	@field:SerializedName("id")
	@Expose
	val id: String? = null
	
	)