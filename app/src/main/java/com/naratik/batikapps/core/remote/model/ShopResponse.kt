package com.naratik.batikapps.core.remote.model

import com.google.gson.annotations.SerializedName

data class ShopResponse(
	@field:SerializedName("shopList")
	val shopList : List<ShopListItem>? = null
                       )


