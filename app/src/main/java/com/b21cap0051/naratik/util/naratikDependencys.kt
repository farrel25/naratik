package com.b21cap0051.naratik.util

import android.content.Context
import com.b21cap0051.naratik.dataresource.NaratikRepository
import com.b21cap0051.naratik.dataresource.local.LocalDataSource
import com.b21cap0051.naratik.dataresource.local.NaratikDB
import com.b21cap0051.naratik.dataresource.remotedata.DataSourceService

object naratikDependencys
{
	fun injectRepository(ctx : Context) : NaratikRepository
	{
		val db = NaratikDB.CreateDB(ctx)
		val remote = DataSourceService.GetInstance(ctx)
		val local = LocalDataSource.GetIntansce(db.PrimaryDAO())
		val ExecuteThread = ExecutedApp()
		
		return NaratikRepository(ctx , remote , local , ExecuteThread)
	}
}