package com.jobtracker.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jobtracker.ui.JobsViewModel
import java.time.LocalDate

@Composable
fun EditJobScreen(
	jobId: Long?,
	onSaved: () -> Unit,
	viewModel: JobsViewModel = viewModel()
) {
	val client = remember { mutableStateOf("") }
	val date = remember { mutableStateOf(LocalDate.now().toString()) }
	val hours = remember { mutableStateOf("1.0") }
	val rate = remember { mutableStateOf("50.0") }
	val paid = remember { mutableStateOf(false) }
	val notes = remember { mutableStateOf("") }

	LaunchedEffect(jobId) {
		if (jobId != null && jobId > 0) {
			viewModel.addOrUpdate // no-op to keep reference
			val existing = viewModel.repositoryRef().getJob(jobId)
			if (existing != null) {
				client.value = existing.clientName
				date.value = existing.jobDate.toString()
				hours.value = existing.hoursWorked.toString()
				rate.value = existing.hourlyRate.toString()
				paid.value = existing.isPaid
				notes.value = existing.notes ?: ""
			}
		}
	}

	Column(Modifier.fillMaxSize().padding(16.dp)) {
		Text(if ((jobId ?: 0L) > 0) "Edit Job" else "Add Job")
		OutlinedTextField(value = client.value, onValueChange = { client.value = it }, label = { Text("Client") }, modifier = Modifier.fillMaxWidth().padding(top = 8.dp))
		OutlinedTextField(value = date.value, onValueChange = { date.value = it }, label = { Text("Date (yyyy-mm-dd)") }, modifier = Modifier.fillMaxWidth().padding(top = 8.dp))
		OutlinedTextField(value = hours.value, onValueChange = { hours.value = it }, label = { Text("Hours") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal), modifier = Modifier.fillMaxWidth().padding(top = 8.dp))
		OutlinedTextField(value = rate.value, onValueChange = { rate.value = it }, label = { Text("Hourly Rate") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal), modifier = Modifier.fillMaxWidth().padding(top = 8.dp))
		Row(Modifier.padding(top = 8.dp)) {
			Checkbox(checked = paid.value, onCheckedChange = { paid.value = it })
			Text("Paid")
		}
		OutlinedTextField(value = notes.value, onValueChange = { notes.value = it }, label = { Text("Notes") }, modifier = Modifier.fillMaxWidth().padding(top = 8.dp))
		Spacer(Modifier.weight(1f))
		Button(onClick = {
			val parsedDate = LocalDate.parse(date.value)
			val parsedHours = hours.value.toDoubleOrNull() ?: 0.0
			val parsedRate = rate.value.toDoubleOrNull() ?: 0.0
			viewModel.addOrUpdate(
				id = jobId ?: 0L,
				client = client.value.trim(),
				date = parsedDate,
				hours = parsedHours,
				rate = parsedRate,
				paid = paid.value,
				notes = notes.value.trim().ifEmpty { null }
			)
			onSaved()
		}, modifier = Modifier.fillMaxWidth()) {
			Text("Save")
		}
	}
}