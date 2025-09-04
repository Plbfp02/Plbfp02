package com.jobtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			JobTrackerApp()
		}
	}
}

@Composable
fun JobTrackerApp() {
	MaterialTheme {
		Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
			Text(text = "JobTracker")
		}
	}
}