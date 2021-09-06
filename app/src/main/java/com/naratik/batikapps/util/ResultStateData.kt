package com.naratik.batikapps.util

sealed class ResultStateData<out Any>
{
	data class Success<out Any>(val data : Any, val message : String?):ResultStateData<Any>()
	data class Failure<out Any>(val data : Any?, val message : String):ResultStateData<Any>()
	class Loading<Nothing>():ResultStateData<Nothing>()
	data class Idle<out Any>(val data : Any?): ResultStateData<Any>()
}