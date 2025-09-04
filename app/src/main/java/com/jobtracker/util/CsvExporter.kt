package com.jobtracker.util

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import com.jobtracker.data.JobEntity
import java.io.File

object CsvExporter {
	fun exportAndShare(context: Context, jobs: List<JobEntity>) {
		val csv = buildString {
			appendLine("id,client,date,hours,rate,paid,earnings,notes")
			jobs.forEach { job ->
				append(job.id)
				append(',')
				append(escape(job.clientName))
				append(',')
				append(job.jobDate.toString())
				append(',')
				append(job.hoursWorked)
				append(',')
				append(job.hourlyRate)
				append(',')
				append(job.isPaid)
				append(',')
				append(job.earnings)
				append(',')
				appendLine(escape(job.notes ?: ""))
			}
		}

		// Share as plain text to avoid file provider setup complexity
		val shareIntent = Intent(Intent.ACTION_SEND).apply {
			type = "text/csv"
			putExtra(Intent.EXTRA_SUBJECT, "JobTracker Export")
			putExtra(Intent.EXTRA_TEXT, csv)
		}
		context.startActivity(Intent.createChooser(shareIntent, "Share CSV"))
	}

	private fun escape(value: String): String {
		val needsQuotes = value.contains(',') || value.contains('\n') || value.contains('"')
		var v = value.replace("\"", "\"\"")
		return if (needsQuotes) "\"$v\"" else v
	}
}