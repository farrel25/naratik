package com.b21cap0051.naratik.dataresource.remotedata

import android.net.Uri
import androidx.lifecycle.LiveData
import com.b21cap0051.naratik.dataresource.remotedata.model.BatikResponse
import com.b21cap0051.naratik.dataresource.remotedata.model.ImageUploadModel
import com.b21cap0051.naratik.util.Resource

interface DataSourceInterface{
	fun GetAllBatikResponse():LiveData<ApiResponse<BatikResponse>>
	
	fun GetPopularBatikResponse():LiveData<ApiResponse<BatikResponse>>
	
	fun UploadImage(upload : ImageUploadModel)
}

