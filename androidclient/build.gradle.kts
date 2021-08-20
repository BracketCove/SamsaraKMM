plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    val compose_version = "1.0.1"

    implementation(project(":common"))

    implementation("androidx.fragment:fragment-ktx:1.3.6")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.0-beta02")
    implementation("androidx.compose.ui:ui:$compose_version")
    implementation("androidx.activity:activity-ktx:1.3.1")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.activity:activity-compose:1.3.1")
    implementation("androidx.compose.ui:ui-tooling:$compose_version")
    implementation("androidx.compose.material:material:$compose_version")
    implementation("androidx.compose.material:material-icons-extended:$compose_version")

}

android {
    compileSdk = 30
    defaultConfig {
        applicationId = "com.example.samsarakmm.android"
        minSdk = 24
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        compose = true

        buildConfig = false
        aidl = false
        renderScript = false
        resValues = false
        shaders = false
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        //TODO figure out how to set this globally instead of hardcoding here
        kotlinCompilerExtensionVersion = "1.0.1"
    }
}