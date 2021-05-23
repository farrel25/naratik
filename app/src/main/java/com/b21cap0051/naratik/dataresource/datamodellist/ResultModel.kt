package com.b21cap0051.naratik.dataresource.datamodellist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultModel(
	var id : String? = null,
	var motif : String? = null,
	var percentage : Int? = null,
                      ): Parcelable