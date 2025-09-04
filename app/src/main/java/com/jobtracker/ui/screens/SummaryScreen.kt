package com.jobtracker.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jobtracker.ui.JobsViewModel
import kotlinx.coroutines.flow.combine
import java.time.LocalDate

@Composable
fun SummaryScreen(viewModel: JobsViewModel = viewModel()) {
	val now = LocalDate.now()
	val start = now.withDayOfMonth(1)
	val end = now.withDayOfMonth(now.lengthOfMonth())

	val totals by viewModel.monthTotals(start, end).collectAsState(initial = Pair(0.0, 0.0))

	Column(Modifier.fillMaxSize().padding(16.dp)) {
		Text("Summary (${start} to ${end})")
		Text("Hours: ${String.format("%.2f", totals.first)}", modifier = Modifier.padding(top = 8.dp))
		Text("Earnings: $${String.format("%.2f", totals.second)}", modifier = Modifier.padding(top = 4.dp))
	}
}