package com.b21cap0051.naratik.util

import android.widget.Toolbar
import com.b21cap0051.naratik.dataresource.datamodellist.ArticleModel

interface ItemArticleCallBack {
	fun itemArticleClick(model : ArticleModel)
}