package com.b21cap0051.naratik.util

import com.b21cap0051.naratik.R
import com.b21cap0051.naratik.dataresource.datamodellist.ArticleModel
import com.b21cap0051.naratik.dataresource.datamodellist.ResultModel
import com.b21cap0051.naratik.dataresource.datamodellist.ShimmerModel
import com.b21cap0051.naratik.dataresource.local.model.BatikEntity
import com.b21cap0051.naratik.dataresource.local.model.ProductEntity
import com.b21cap0051.naratik.dataresource.local.model.StoreEntity

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
	
	fun generateDummyBatik() : ArrayList<BatikEntity>
	{
		val batiks = ArrayList<BatikEntity>()
		
		batiks.add(
			BatikEntity(
				139 ,
				"Name" ,
				"Filosofi" ,
				"https://i.pinimg.com/736x/30/7e/32/307e32aff223035590dbf69357d5a467.jpg" ,
				
				)
		          )
		batiks.add(
			BatikEntity(
				138 ,
				"Name" ,
				"Filosofi" ,
				"https://i1.wp.com/obatrindu.com/wp-content/uploads/2016/09/motif-batik-semen-rama-1.jpg"
			           )
		          )
		batiks.add(
			BatikEntity(
				137 ,
				"Name" ,
				"Filosofi" ,
				"https://i.pinimg.com/564x/ca/4d/62/ca4d6236c8fa7d056fda64b66fb1b0cd.jpg"
			           )
		          )
		batiks.add(
			BatikEntity(
				136 ,
				"Name" ,
				"Filosofi" ,
				"https://dunianyamaya.files.wordpress.com/2008/04/prabu-anomparang-tuding.jpg"
			           )
		          )
		batiks.add(
			BatikEntity(
				134 ,
				"Name" ,
				"Filosofi" ,
				"https://i0.wp.com/obatrindu.com/wp-content/uploads/2016/09/motif-batik-Sido-Drajad.JPG.jpg"
			           )
		          )
		batiks.add(
			BatikEntity(
				135 ,
				"Name" ,
				"Filosofi" ,
				"https://i0.wp.com/obatrindu.com/wp-content/uploads/2016/09/batik-tasikmalaya.jpg"
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
	
	fun generateDummyStore() : ArrayList<StoreEntity>
	{
		val stores = ArrayList<StoreEntity>()
		
		stores.add(
			StoreEntity(
				1 ,
				"https://i.pinimg.com/736x/30/7e/32/307e32aff223035590dbf69357d5a467.jpg" ,
				"https://jooinn.com/images/backdrop-1.jpg" ,
				"Name" ,
				"081234567890" ,
				8.0 ,
				"Semarang" ,
				5 ,
				))
		stores.add(
			StoreEntity(
				1 ,
				"https://i.pinimg.com/736x/30/7e/32/307e32aff223035590dbf69357d5a467.jpg" ,
				"https://jooinn.com/images/backdrop-1.jpg" ,
				"Name" ,
				"081234567890" ,
				8.0 ,
				"Semarang" ,
				5 ,
			           ))
		stores.add(
			StoreEntity(
				1 ,
				"https://i.pinimg.com/736x/30/7e/32/307e32aff223035590dbf69357d5a467.jpg" ,
				"https://jooinn.com/images/backdrop-1.jpg" ,
				"Name" ,
				"081234567890" ,
				8.0 ,
				"Semarang" ,
				5 ,
			           ))
		stores.add(
			StoreEntity(
				1 ,
				"https://i.pinimg.com/736x/30/7e/32/307e32aff223035590dbf69357d5a467.jpg" ,
				"https://jooinn.com/images/backdrop-1.jpg" ,
				"Name" ,
				"081234567890" ,
				8.0 ,
				"Semarang" ,
				5 ,
			           ))
		stores.add(
			StoreEntity(
				1 ,
				"https://i.pinimg.com/736x/30/7e/32/307e32aff223035590dbf69357d5a467.jpg" ,
				"https://jooinn.com/images/backdrop-1.jpg" ,
				"Name" ,
				"081234567890" ,
				8.0 ,
				"Semarang" ,
				5 ,
			           ))
		stores.add(
			StoreEntity(
				1 ,
				"https://i.pinimg.com/736x/30/7e/32/307e32aff223035590dbf69357d5a467.jpg" ,
				"https://jooinn.com/images/backdrop-1.jpg" ,
				"Name" ,
				"081234567890" ,
				8.0 ,
				"Semarang" ,
				5 ,
			           ))
		
		return stores
	}
	fun generateDummyProduct() : ArrayList<ProductEntity>
	{
		val products = ArrayList<ProductEntity>()
		
		products.add(
			ProductEntity(
				1 ,
				"Name" ,
				"Parang" ,
				"Kaos" ,
				"https://i.pinimg.com/736x/30/7e/32/307e32aff223035590dbf69357d5a467.jpg" ,
				8.0 ,
				"100.000" ,
				))
		products.add(
			ProductEntity(
				1 ,
				"Name" ,
				"Parang" ,
				"Kaos" ,
				"https://i.pinimg.com/736x/30/7e/32/307e32aff223035590dbf69357d5a467.jpg" ,
				8.0 ,
				"100.000" ,
			             ))
		products.add(
			ProductEntity(
				1 ,
				"Name" ,
				"Parang" ,
				"Kaos" ,
				"https://i.pinimg.com/736x/30/7e/32/307e32aff223035590dbf69357d5a467.jpg" ,
				8.0 ,
				"100.000" ,
			             ))
		products.add(
			ProductEntity(
				1 ,
				"Name" ,
				"Parang" ,
				"Kaos" ,
				"https://i.pinimg.com/736x/30/7e/32/307e32aff223035590dbf69357d5a467.jpg" ,
				8.0 ,
				"100.000" ,
			             ))
		products.add(
			ProductEntity(
				1 ,
				"Name" ,
				"Parang" ,
				"Kaos" ,
				"https://i.pinimg.com/736x/30/7e/32/307e32aff223035590dbf69357d5a467.jpg" ,
				8.0 ,
				"100.000" ,
			             ))
		products.add(
			ProductEntity(
				1 ,
				"Name" ,
				"Parang" ,
				"Kaos" ,
				"https://i.pinimg.com/736x/30/7e/32/307e32aff223035590dbf69357d5a467.jpg" ,
				8.0 ,
				"100.000" ,
			             ))
		return products
	}
	fun generateShimmerBatik() :  ArrayList<ShimmerModel>{
		val shimmers = ArrayList<ShimmerModel>()
		
		shimmers.add(
			ShimmerModel(
				R.drawable.img_shimmer
			            ))
		shimmers.add(
			ShimmerModel(
				R.drawable.img_shimmer
			            ))
		shimmers.add(
			ShimmerModel(
				R.drawable.img_shimmer
			            ))
		shimmers.add(
			ShimmerModel(
				R.drawable.img_shimmer
			            ))
		return shimmers
	}
}