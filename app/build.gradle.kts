plugins {
	id("com.android.application")
	kotlin("android")
	id("org.jetbrains.kotlin.kapt")
}

android {
	namespace = "com.jobtracker"
	compileSdk = 34

	defaultConfig {
		applicationId = "com.jobtracker"
		minSdk = 24
		targetSdk = 34
		versionCode = 1
		versionName = "1.0.0"

		vectorDrawables.useSupportLibrary = true
	}

	buildTypes {
		release {
			isMinifyEnabled = true
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
		debug {
			isMinifyEnabled = false
		}
	}

	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
		isCoreLibraryDesugaringEnabled = true
	}

	kotlinOptions {
		jvmTarget = "17"
		freeCompilerArgs += listOf("-Xjvm-default=all")
	}

	buildFeatures { compose = true }

	composeOptions { kotlinCompilerExtensionVersion = "1.5.14" }

	packaging { resources.excludes += "/META-INF/{AL2.0,LGPL2.1}" }
}

dependencies {
	implementation(platform("androidx.compose:compose-bom:2024.06.00"))
	implementation("androidx.compose.ui:ui")
	implementation("androidx.compose.ui:ui-tooling-preview")
	implementation("androidx.compose.material3:material3")
	implementation("androidx.compose.material:material-icons-extended:1.6.8")
	debugImplementation("androidx.compose.ui:ui-tooling")

	implementation("androidx.activity:activity-compose:1.9.0")
	implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.2")
	implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.2")
	implementation("androidx.navigation:navigation-compose:2.7.7")

	implementation("androidx.room:room-runtime:2.6.1")
	implementation("androidx.room:room-ktx:2.6.1")
	kapt("androidx.room:room-compiler:2.6.1")

	coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")
}