//Thank you to Arkadii Ivanov for demonstrating this gradle multiplatform set up
// github.com/JetBrains/compose-jb

//Note: buildSrc is a reserved project name, which is why this module doesn't need to be included
//in settings.gradle

plugins {
    id("org.gradle.kotlin.kotlin-dsl") version "2.1.7"
}

repositories {
    mavenLocal()
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation(Deps.JetBrains.Compose.gradlePlugin)
    implementation(Deps.JetBrains.Kotlin.gradlePlugin)
    implementation(Deps.Android.Tools.Build.gradlePlugin)
    implementation(Deps.Squareup.SQLDelight.gradlePlugin)
}

kotlin {
    // Add Deps to compilation, so it will become available in main project
    sourceSets.getByName("main").kotlin.srcDir("buildSrc/src/main/kotlin")
}
