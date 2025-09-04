pluginManagement {
	repositories {
		google()
		mavenCentral()
		gradlePluginPortal()
	}
	plugins {
		id("com.android.application") version "8.4.1"
		kotlin("android") version "1.9.24"
		id("org.jetbrains.kotlin.kapt") version "1.9.24"
	}
}

dependencyResolutionManagement {
	repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
	repositories {
		google()
		mavenCentral()
	}
}

rootProject.name = "JobTracker"
include(":app")