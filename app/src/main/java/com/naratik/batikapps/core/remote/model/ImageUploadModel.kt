package com.naratik.batikapps.core.remote.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ImageUploadModel(
	var uri : Uri
                           ) : Parcelable
