package com.b21cap0051.naratik.mainview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.b21cap0051.naratik.dataresource.NaratikRepository
import com.b21cap0051.naratik.dataresource.local.model.ShopEntity
import com.b21cap0051.naratik.util.vo.Resource

class ShopMainView(private val repo : NaratikRepository) : ViewModel()
{
	fun getAllShop() : LiveData<Resource<PagedList<ShopEntity>>> = repo.GetAllShop()
}