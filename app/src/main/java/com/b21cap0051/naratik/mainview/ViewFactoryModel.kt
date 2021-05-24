package com.b21cap0051.naratik.mainview

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.b21cap0051.naratik.dataresource.NaratikRepository
import com.b21cap0051.naratik.util.naratikDependencys

class ViewFactoryModel constructor(private val mRepo : NaratikRepository) :
	ViewModelProvider.NewInstanceFactory()
{
	
	companion object
	{
		@Volatile
		private var Instance : ViewFactoryModel? = null
		
		fun GetInstance(ctx : Context) : ViewFactoryModel =
			Instance ?: synchronized(this) {
				Instance ?: ViewFactoryModel(naratikDependencys.injectRepository(ctx)).apply {
					Instance = this
				}
			}
	}
	
	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel?> create(modelClass : Class<T>) : T
	{
		return when
		{
			modelClass.isAssignableFrom(ExploreMainView::class.java) ->
			{
				ExploreMainView(mRepo) as T
			}
			
			else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
		}
	}
	
}