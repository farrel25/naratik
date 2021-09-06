package com.naratik.batikapps.core.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PredictResponse(
	@field:SerializedName("img_url")
	val imgUrl: String? = null,
	
	@field:SerializedName("id")
	val id: String? = null,
	
	@field:SerializedName("motif")
	val motif: List<MotifResponseItem?>? = null
                          ):Parcelable