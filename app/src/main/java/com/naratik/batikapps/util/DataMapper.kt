package com.naratik.batikapps.util

import com.naratik.batikapps.core.local.model.BatikEntity
import com.naratik.batikapps.core.local.model.PopularBatikEntity
import com.naratik.batikapps.core.local.model.ShopEntity
import com.naratik.batikapps.core.remote.model.BatikResponse
import com.naratik.batikapps.core.remote.model.ShopResponse

object DataMapper
{
	fun apiToBatikEntity(data : BatikResponse) : List<BatikEntity>
	{
		val batikChange = ArrayList<BatikEntity>()
		
		val sizeData = data.hasil?.size
		val response = data.hasil
		for (i in 0 until sizeData!!)
		{
			val input = BatikEntity(
				response!![i]?.id ,
				response!![i]?.namaBatik ,
				response!![i]?.maknaBatik ,
				response!![i]?.linkBatik ,
				response!![i]?.daerahBatik ,
				0 ,
				
				)
			batikChange.add(input)
		}
		
		return batikChange
	}
	
	
	fun apiToBatikEntityPopuler(data : BatikResponse) : List<PopularBatikEntity>
	{
		
		
		val batikChange = ArrayList<PopularBatikEntity>()
		val sizeData = data.hasil?.size
		val response = data.hasil
		for (i in 0 until sizeData!!)
		{
			val input = PopularBatikEntity(
				response!![i]?.id ,
				response!![i]?.namaBatik ,
				response!![i]?.maknaBatik ,
				response!![i]?.linkBatik ,
				response!![i]?.daerahBatik ,
				0 ,
				
				)
			batikChange.add(input)
		}
		return batikChange
	}
	
	fun apiToEntityShop(data : ShopResponse) : List<ShopEntity>
	{
		val shopChange = ArrayList<ShopEntity>()
		val sizeData = data.shopList?.size
		val response = data.shopList
		for (i in 0 until sizeData!!)
		{
			val input = ShopEntity(
				null ,
				response!![i]?.namaToko!! ,
				response!![i]?.product ,
				response!![i]?.alamatToko ,
			                      )
			shopChange.add(input)
		}
		return shopChange
	}
	
}