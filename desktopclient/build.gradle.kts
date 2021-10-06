import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform") // kotlin("jvm") doesn't work well in IDEA/AndroidStudio (https://github.com/JetBrains/compose-jb/issues/22)
    id("org.jetbrains.compose")
}

kotlin {
    jvm {
        withJava()
    }

    sourceSets {
        named("jvmMain") {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(project(":common:compose-ui"))
                implementation(project(":common:database"))
                implementation(project(":common:main"))
                Deps.JetBrains.Kotlin.coroutinesCore
                Deps.JetBrains.Kotlin.coroutinesJVM
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "com.example.samsarakmm.desktop.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "SamsaraDesktop"
            packageVersion = "1.0.0"

            modules("java.sql")

            windows {
                menuGroup = "Samsara Day Planner"
                upgradeUuid = "7f73d4cd-91bb-4ca1-9982-2ee6633ec43e"
            }
        }
    }
}
