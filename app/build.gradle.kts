import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}


android {
    val keystoreFile = project.rootProject.file("local.properties")
    val properties = Properties()
    properties.load(keystoreFile.inputStream())
    val apiKey = properties.getProperty("API_KEY") ?: ""

    namespace = "com.example.kotlinmoviesapp"
    compileSdk = 34

    buildFeatures {
        buildConfig = true
    }
    defaultConfig {
        buildConfigField("String", "API_KEY", "\"${apiKey}\"")
        applicationId = "com.example.kotlinmoviesapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //ROOM
    val room_version = "2.6.1"
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    kapt("androidx.room:room-compiler:$room_version")
    implementation(libs.androidx.room.ktx)
    //Retrofit
    implementation (libs.retrofit)

    //GSON
    implementation (libs.converter.gson)
    implementation (libs.gson)

    //Coroutines
    implementation(libs.kotlinx.coroutines.android)

    //Dagger Hilt
    implementation(libs.hilt.android)
    kapt (libs.hilt.android.compiler)

    //Coil
    implementation(libs.coil.compose)

    //LiveData
    implementation(libs.androidx.runtime.livedata)

    //ViewModel
    implementation(libs.androidx.fragment.ktx)

    //Navigation
    implementation(libs.androidx.navigation.compose)

    //Shimmer
    implementation(libs.shimmer)
}


kapt {
    correctErrorTypes = true
}