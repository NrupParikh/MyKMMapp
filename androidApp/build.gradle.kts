plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    id("com.google.devtools.ksp") version "1.9.23-1.0.19"

    //Kotlin Serialization
    kotlin("plugin.serialization") version "1.9.10"
}

android {
    namespace = "com.nrup.mykmmapp.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.nrup.mykmmapp.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    applicationVariants.all{
        addJavaSourceFoldersToModel(
            File(buildDir,"generated/ksp/$name/kotlin")
        )
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
//    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.material3.android)
    debugImplementation(libs.compose.ui.tooling)

    // For Navigation
    implementation("io.github.raamcosta.compose-destinations:core:1.8.38-beta")
    ksp("io.github.raamcosta.compose-destinations:ksp:1.8.38-beta")

    // For splash screen
    implementation("androidx.core:core-splashscreen:1.0.1")

    // For shared preference : We used proto buff
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // For Koin DI
    implementation("io.insert-koin:koin-androidx-compose:3.4.1")

    // For Compose Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")

    // Collection of extension lib for Compose
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.28.0")

    // For image loading
    implementation("io.coil-kt:coil-compose:2.4.0")

    // For serialization and de-serialization
    implementation("io.ktor:ktor-serialization-kotlinx-json:$kotlin")
}