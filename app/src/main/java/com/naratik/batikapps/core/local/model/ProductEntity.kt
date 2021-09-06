package com.naratik.batikapps.core.local.model

data class ProductEntity(
	
	var id : Int = 0 ,
	var name : String = "" ,
	var motif : String = "" ,
	var category : String = "" ,
	var image : String = "" ,
	var rating : Double = 0.0 ,
	var price : String = "" ,
	var isFavorite : Int = 0
                        )