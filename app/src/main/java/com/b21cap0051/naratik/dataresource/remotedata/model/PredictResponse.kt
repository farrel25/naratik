package com.b21cap0051.naratik.dataresource.remotedata.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PredictResponse(
	@field:SerializedName("id")
	@Expose
	var id_predict : String? = null ,
	
	@field:SerializedName("img_url")
	@Expose
	var img_url : String? = null ,
	
	@field:SerializedName("motif")
	@Expose
	var motifResult : List<MotifResponseItem>
                          )