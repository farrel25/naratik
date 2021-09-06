package com.naratik.batikapps.core.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MotifResponseItem(
	@field:SerializedName("motifName")
	val motifName: String? = null,
	
	@field:SerializedName("value")
	val value: Double? = null
                            ) : Parcelable
