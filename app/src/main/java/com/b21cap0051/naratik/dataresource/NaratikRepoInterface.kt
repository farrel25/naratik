package com.b21cap0051.naratik.dataresource

import androidx.lifecycle.LiveData
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.dataresource.local.model.PopularBatikEntity
import com.b21cap0051.naratik.util.Resource

interface NaratikRepoInterface
{
	fun GetAllBatik():LiveData<Resource<List<BatikEntity>>>
	
	fun GetPopular():LiveData<Resource<List<PopularBatikEntity>>>
	
}