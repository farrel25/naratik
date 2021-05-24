package com.b21cap0051.naratik.util

import com.b21cap0051.naratik.dataresource.datamodellist.BatikModel
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity

interface ItemBatikCallBack {
    fun itemBatikClick(model : BatikEntity)
}