package com.b21cap0051.naratik.dataresource.remotedata.model

import com.google.gson.annotations.SerializedName

data class BatikResponse(
	@field:SerializedName("hasil")
	val hasil : List<BatikResponseItem>? = null
                        )


