package com.b21cap0051.naratik.mainview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.b21cap0051.naratik.dataresource.NaratikRepository
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.util.vo.Resource

class SearchMainView(private val repo : NaratikRepository):ViewModel(){
	
	fun GetSearch(text : String):LiveData<Resource<List<BatikEntity>>> = repo.searchBatik(text)
	
}