package com.b21cap0051.naratik.util

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ExecutedApp constructor(
	private val DiskIO : Executor ,
	private val NetworkThread : Executor,
                             )
{
	
	constructor() : this(
		Executors.newSingleThreadExecutor() ,
		Executors.newFixedThreadPool(THREAD_COUNT),
	                    )
	
	fun DiskIO() = this.DiskIO
	fun NetworkThread() = this.NetworkThread
	
	
	companion object
	{
		private val THREAD_COUNT = 3
	}
	
	private class MainThreadExecutor : Executor
	{
		
		private val mainThread = Handler(Looper.getMainLooper())
		
		override fun execute(command : Runnable)
		{
			mainThread.post(command)
		}
		
	}
	
}