import org.jetbrains.compose.compose
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.library")
    id("kotlin-multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    jvm("desktop")
    android()

    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(project(":common:main"))
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.ui)
                implementation(compose.materialIconsExtended)
            }
        }

        named("androidMain") {
            dependencies {
                implementation("androidx.core:core-ktx:1.6.0")
            }
        }

        named("desktopMain") {
            dependencies {
                implementation(compose.desktop.common)
            }
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}
