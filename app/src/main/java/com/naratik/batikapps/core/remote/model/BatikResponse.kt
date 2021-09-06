package com.naratik.batikapps.core.remote.model

import com.google.gson.annotations.SerializedName

data class BatikResponse(
	@field:SerializedName("hasil")
	val hasil : List<BatikResponseItem>? = null
                        )


