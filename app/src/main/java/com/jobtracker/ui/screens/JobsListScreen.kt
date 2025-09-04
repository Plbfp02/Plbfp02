package com.jobtracker.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jobtracker.data.JobEntity
import com.jobtracker.ui.JobsViewModel

@Composable
fun JobsListScreen(
	onAddJob: () -> Unit,
	onEditJob: (Long) -> Unit,
	viewModel: JobsViewModel = viewModel()
) {
	val state by viewModel.uiState.collectAsState()

	Scaffold(
		floatingActionButton = {
			FloatingActionButton(onClick = onAddJob) {
				Icon(Icons.Default.Add, contentDescription = "Add Job")
			}
		}
	) { padding ->
		Column(Modifier.fillMaxSize().padding(padding)) {
			Text(
				text = "Jobs",
				style = MaterialTheme.typography.headlineSmall,
				modifier = Modifier.padding(16.dp)
			)
			LazyColumn(Modifier.fillMaxSize()) {
				items(state.jobs, key = { it.id }) { job ->
					JobRow(job = job, onClick = { onEditJob(job.id) }, onDelete = { viewModel.delete(job.id) })
				}
			}
		}
	}
}

@Composable
private fun JobRow(job: JobEntity, onClick: () -> Unit, onDelete: () -> Unit) {
	Column(
		Modifier.fillMaxWidth().clickable { onClick() }.padding(horizontal = 16.dp, vertical = 12.dp)
	) {
		Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
			Text(job.clientName, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
			Text("${job.jobDate}", style = MaterialTheme.typography.bodyMedium)
		}
		Spacer(Modifier.padding(2.dp))
		Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
			Text("${job.hoursWorked}h @ ${formatMoney(job.hourlyRate)}")
			Row {
				Text(formatMoney(job.earnings), fontWeight = FontWeight.Bold)
				IconButton(onClick = onDelete) { Icon(Icons.Default.Delete, contentDescription = "Delete") }
			}
		}
		if (job.isPaid) {
			Text("PAID", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
		}
	}
}

private fun formatMoney(amount: Double): String = "$" + String.format("%.2f", amount)