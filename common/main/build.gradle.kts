plugins {
    id("multiplatform-setup")
    id("android-setup")
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
            }
        }
    }

    sourceSets {
        named("androidMain") {
            dependencies {
                implementation(Deps.JetBrains.Kotlin.coroutinesCore)
            }
        }
    }

    sourceSets {
        named("desktopMain") {
            dependencies {
                implementation(Deps.JetBrains.Kotlin.coroutinesJVM)

            }
        }
    }
}
