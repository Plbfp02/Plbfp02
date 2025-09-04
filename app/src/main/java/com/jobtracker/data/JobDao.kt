package com.jobtracker.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface JobDao {
	@Query("SELECT * FROM jobs ORDER BY jobDate DESC, id DESC")
	fun observeAll(): Flow<List<JobEntity>>

	@Query("SELECT * FROM jobs WHERE id = :id LIMIT 1")
	suspend fun getById(id: Long): JobEntity?

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(job: JobEntity): Long

	@Update
	suspend fun update(job: JobEntity)

	@Delete
	suspend fun delete(job: JobEntity)

	@Query("DELETE FROM jobs WHERE id = :id")
	suspend fun deleteById(id: Long)

	@Query(
		"SELECT IFNULL(SUM(hoursWorked * hourlyRate), 0) FROM jobs WHERE jobDate BETWEEN :start AND :end"
	)
	fun observeEarningsBetween(start: LocalDate, end: LocalDate): Flow<Double>

	@Query("SELECT IFNULL(SUM(hoursWorked), 0) FROM jobs WHERE jobDate BETWEEN :start AND :end")
	fun observeHoursBetween(start: LocalDate, end: LocalDate): Flow<Double>
}