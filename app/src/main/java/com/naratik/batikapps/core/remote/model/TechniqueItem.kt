package com.naratik.batikapps.core.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TechniqueItem
	(
	@field:SerializedName("techniqueName")
	val techniqueName: String? = null,
	
	@field:SerializedName("value")
	val value: Double? = null
	):Parcelable