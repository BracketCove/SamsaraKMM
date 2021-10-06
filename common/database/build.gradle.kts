plugins {
    id("multiplatform-setup")
    id("android-setup")
    id("com.squareup.sqldelight")
}

sqldelight {
    database("AppDatabase") {
        packageName = "com.example.samsarakmm.common.database"
    }
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:main"))
                implementation(Deps.JetBrains.Kotlin.coroutinesCore)
            }
        }

        androidMain {
            dependencies {
                implementation(Deps.Squareup.SQLDelight.androidDriver)
                implementation(Deps.Squareup.SQLDelight.sqliteDriver)
            }
        }

        desktopMain {
            dependencies {
                implementation(Deps.Squareup.SQLDelight.sqliteDriver)

            }
        }
    }
}
