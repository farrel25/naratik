package com.b21cap0051.naratik.dataresource.remotedata.model

import com.google.gson.annotations.SerializedName

data class ShopResponse(
	@field:SerializedName("shopList")
	val shopList : List<ShopListItem>? = null
                       )


