package com.b21cap0051.naratik.util

import com.b21cap0051.naratik.dataresource.local.model.ProductEntity

interface ItemProductCallback
{
	fun itemProductClick(model : ProductEntity)
}