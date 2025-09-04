package com.jobtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jobtracker.ui.screens.EditJobScreen
import com.jobtracker.ui.screens.JobsListScreen
import com.jobtracker.ui.screens.SummaryScreen
import com.jobtracker.ui.navigation.Routes
import com.jobtracker.util.CsvExporter

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent { JobTrackerApp() }
	}
}

@Composable
fun JobTrackerApp() {
	MaterialTheme {
		Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
			val nav = rememberNavController()
			NavHost(navController = nav, startDestination = Routes.Jobs) {
				composable(Routes.Jobs) {
					JobsListScreen(
						onAddJob = { nav.navigate("${Routes.Edit}?id=0") },
						onEditJob = { id -> nav.navigate("${Routes.Edit}?id=$id") },
						onOpenSummary = { nav.navigate(Routes.Summary) },
						onExportCsv = { jobs -> CsvExporter.exportAndShare(nav.context, jobs) }
					)
				}
				composable("${Routes.Edit}?id={id}") { backStackEntry ->
					val id = backStackEntry.arguments?.getString("id")?.toLongOrNull()
					EditJobScreen(jobId = id, onSaved = { nav.popBackStack() })
				}
				composable(Routes.Summary) { SummaryScreen() }
			}
		}
	}
}