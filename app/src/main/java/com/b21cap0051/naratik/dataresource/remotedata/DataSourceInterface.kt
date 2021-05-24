package com.b21cap0051.naratik.dataresource.remotedata

import androidx.lifecycle.LiveData
import com.b21cap0051.naratik.dataresource.remotedata.model.BatikResponse
import com.b21cap0051.naratik.util.Resource

interface DataSourceInterface{
	fun GetAllBatikResponse():LiveData<ApiResponse<BatikResponse>>
}

