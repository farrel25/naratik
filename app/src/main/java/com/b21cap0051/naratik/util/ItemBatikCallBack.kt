package com.b21cap0051.naratik.util

import com.b21cap0051.naratik.databinding.ItemRowBatikBinding
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity

interface ItemBatikCallBack
{
	fun CheckIsFavor(model : BatikEntity) : Boolean
	
	fun itemBatikClick(model : BatikEntity)
	
	fun AddFavour(v : ItemRowBatikBinding , model : BatikEntity)
}
