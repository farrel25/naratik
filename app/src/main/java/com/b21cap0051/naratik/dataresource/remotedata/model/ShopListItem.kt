package com.b21cap0051.naratik.dataresource.remotedata.model

import com.google.gson.annotations.SerializedName

data class ShopListItem(
	
	@field:SerializedName("product")
	val product: String? = null,
	
	@field:SerializedName("alamatToko")
	val alamatToko: String? = null,
	
	@field:SerializedName("namaToko")
	val namaToko: String? = null
                       )