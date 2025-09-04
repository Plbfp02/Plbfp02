package com.jobtracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "jobs")
data class JobEntity(
	@PrimaryKey(autoGenerate = true) val id: Long = 0L,
	val clientName: String,
	val jobDate: LocalDate,
	val hoursWorked: Double,
	val hourlyRate: Double,
	val isPaid: Boolean,
	val notes: String?
) {
	val earnings: Double get() = hoursWorked * hourlyRate
}