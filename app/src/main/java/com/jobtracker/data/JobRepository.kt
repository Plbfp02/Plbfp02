package com.jobtracker.data

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class JobRepository(private val jobDao: JobDao) {
	fun observeJobs(): Flow<List<JobEntity>> = jobDao.observeAll()

	suspend fun getJob(id: Long): JobEntity? = jobDao.getById(id)

	suspend fun upsert(job: JobEntity): Long {
		return if (job.id == 0L) jobDao.insert(job) else {
			jobDao.update(job)
			job.id
		}
	}

	suspend fun delete(job: JobEntity) = jobDao.delete(job)
	suspend fun deleteById(id: Long) = jobDao.deleteById(id)

	fun observeEarningsBetween(start: LocalDate, end: LocalDate): Flow<Double> =
		jobDao.observeEarningsBetween(start, end)

	fun observeHoursBetween(start: LocalDate, end: LocalDate): Flow<Double> =
		jobDao.observeHoursBetween(start, end)
}