package com.jobtracker.di

import android.content.Context
import com.jobtracker.data.AppDatabase
import com.jobtracker.data.JobRepository

object AppModule {
	@Volatile private var repositoryInstance: JobRepository? = null

	fun provideRepository(context: Context): JobRepository = repositoryInstance ?: synchronized(this) {
		repositoryInstance ?: JobRepository(AppDatabase.getInstance(context).jobDao()).also {
			repositoryInstance = it
		}
	}
}