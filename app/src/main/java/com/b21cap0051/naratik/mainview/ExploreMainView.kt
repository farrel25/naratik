package com.b21cap0051.naratik.mainview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.b21cap0051.naratik.dataresource.NaratikRepository
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.dataresource.remotedata.model.BatikResponse
import com.b21cap0051.naratik.util.Resource

class ExploreMainView(private val Repository : NaratikRepository) : ViewModel()
{
	
	fun getAllbatik():LiveData<Resource<List<BatikEntity>>> =
		Repository.GetAllBatik()
	
}