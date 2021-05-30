package com.b21cap0051.naratik.dataresource.datamodellist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BatikModel(
	var id : String? = null ,
	var image : Int? = 0 ,
	var name : String? = null ,
	var filosofi : String? = null ,
                     ) : Parcelable