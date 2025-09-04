package com.jobtracker.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jobtracker.data.JobEntity
import com.jobtracker.data.JobRepository
import com.jobtracker.di.AppModule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class JobsViewModel(application: Application) : AndroidViewModel(application) {
	private val repository: JobRepository = AppModule.provideRepository(application)

	data class UiState(
		val jobs: List<JobEntity> = emptyList(),
		val isLoading: Boolean = false,
		val errorMessage: String? = null
	)

	val uiState: StateFlow<UiState> = repository.observeJobs()
		.map { UiState(jobs = it) }
		.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), UiState(isLoading = true))

	fun addOrUpdate(
		id: Long = 0L,
		client: String,
		date: java.time.LocalDate,
		hours: Double,
		rate: Double,
		paid: Boolean,
		notes: String?
	) {
		viewModelScope.launch {
			repository.upsert(
				JobEntity(
					id = id,
					clientName = client,
					jobDate = date,
					hoursWorked = hours,
					hourlyRate = rate,
					isPaid = paid,
					notes = notes
				)
			)
		}
	}

	fun delete(id: Long) {
		viewModelScope.launch { repository.deleteById(id) }
	}
}