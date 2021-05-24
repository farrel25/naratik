package com.b21cap0051.naratik.dataresource.local

import androidx.lifecycle.LiveData
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.dataresource.local.model.PopularBatikEntity

interface LocalDataInterface
{
	fun GetAllBatik():LiveData<List<BatikEntity>>
	fun GetAllPopularBatik():LiveData<List<PopularBatikEntity>>
	fun InsertBatik(value : List<BatikEntity>)
	fun insertPopularBatik(value : List<PopularBatikEntity>)
	fun updateBatik(value : BatikEntity)
	fun updatePopularBatik(value : PopularBatikEntity)
}