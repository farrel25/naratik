package com.b21cap0051.naratik.dataresource.remotedata.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ImageUploadModel(
	var uri : Uri
                           ) : Parcelable
