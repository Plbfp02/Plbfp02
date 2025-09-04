package com.jobtracker.data

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

object Converters {
	@TypeConverter
	@JvmStatic
	fun fromEpochDay(value: Long?): LocalDate? = value?.let { LocalDate.ofEpochDay(it) }

	@TypeConverter
	@JvmStatic
	fun localDateToEpochDay(date: LocalDate?): Long? = date?.toEpochDay()
}