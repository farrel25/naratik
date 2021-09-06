package com.naratik.batikapps.ui.adapter.modellist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultModel(
	var id : String? = null ,
	var motif : String? = null ,
	var percentage : Int? = null ,
                      ) : Parcelable