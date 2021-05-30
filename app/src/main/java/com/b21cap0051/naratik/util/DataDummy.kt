package com.b21cap0051.naratik.util

import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.dataresource.datamodellist.ArticleModel
import com.b21cap0051.naratik.dataresource.datamodellist.BatikModel
import com.b21cap0051.naratik.dataresource.datamodellist.ResultModel

object DataDummy
{
	fun generateDummyArticle() : ArrayList<ArticleModel>
	{
		
		val articles = ArrayList<ArticleModel>()
		
		articles.add(
			ArticleModel(
				"id" ,
				R.drawable.img_dummy ,
				"Title" ,
				"5 August 2022" ,
				"Admin" ,
				"overview" ,
			            )
		            )
		articles.add(
			ArticleModel(
				"id" ,
				R.drawable.img_dummy ,
				"Title" ,
				"5 August 2022" ,
				"Admin" ,
				"overview" ,
			            )
		            )
		articles.add(
			ArticleModel(
				"id" ,
				R.drawable.img_dummy ,
				"Title" ,
				"5 August 2022" ,
				"Admin" ,
				"overview" ,
			            )
		            )
		articles.add(
			ArticleModel(
				"id" ,
				R.drawable.img_dummy ,
				"Title" ,
				"5 August 2022" ,
				"Admin" ,
				"overview" ,
			            )
		            )
		
		return articles
	}
	
	fun generateDummyBatik() : ArrayList<BatikModel>
	{
		val batiks = ArrayList<BatikModel>()
		
		batiks.add(
			BatikModel(
				"id" ,
				R.drawable.img_batik ,
				"Name" ,
				"Filosofi"
			          )
		          )
		batiks.add(
			BatikModel(
				"id" ,
				R.drawable.img_batik ,
				"Name" ,
				"Filosofi"
			          )
		          )
		batiks.add(
			BatikModel(
				"id" ,
				R.drawable.img_batik ,
				"Name" ,
				"Filosofi"
			          )
		          )
		batiks.add(
			BatikModel(
				"id" ,
				R.drawable.img_batik ,
				"Name" ,
				"Filosofi"
			          )
		          )
		batiks.add(
			BatikModel(
				"id" ,
				R.drawable.img_batik ,
				"Name" ,
				"Filosofi"
			          )
		          )
		batiks.add(
			BatikModel(
				"id" ,
				R.drawable.img_batik ,
				"Name" ,
				"Filosofi"
			          )
		          )
		batiks.add(
			BatikModel(
				"id" ,
				R.drawable.img_batik ,
				"Name" ,
				"Filosofi"
			          )
		          )
		batiks.add(
			BatikModel(
				"id" ,
				R.drawable.img_batik ,
				"Name" ,
				"Filosofi"
			          )
		          )
		return batiks
	}
	
	fun generateDummyResult() : ArrayList<ResultModel>
	{
		val results = ArrayList<ResultModel>()
		
		results.add(
			ResultModel(
				"id" ,
				"Kawung" ,
				10
			           )
		           )
		results.add(
			ResultModel(
				"id" ,
				"Parang" ,
				20
			           )
		           )
		results.add(
			ResultModel(
				"id" ,
				"Mega Mendung" ,
				34
			           )
		           )
		
		return results
	}
}