package com.b21cap0051.naratik.dataresource.remotedata.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MotifResponseItem(
	@field:SerializedName("motifName")
	@Expose
	var motifName : String? = null,
	
	@field:SerializedName("value")
	@Expose
	var valueMotif : Double? = null,
							) : Parcelable
