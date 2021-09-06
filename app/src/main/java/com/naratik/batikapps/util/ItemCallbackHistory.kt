package com.naratik.batikapps.util

import com.naratik.batikapps.core.local.model.HistoryEntity

interface ItemCallbackHistory
{
	fun getItem(model : HistoryEntity)
	
	fun getPosition(Position : Int)
}