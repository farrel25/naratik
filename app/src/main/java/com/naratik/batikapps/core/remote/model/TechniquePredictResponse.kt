package com.naratik.batikapps.core.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TechniquePredictResponse
	(
	@field:SerializedName("img_url")
	val imgUrl: String? = null,
	
	@field:SerializedName("technique")
	val technique: List<TechniqueItem?>? = null,
	
	@field:SerializedName("id")
	val id: String? = null
	
	):Parcelable