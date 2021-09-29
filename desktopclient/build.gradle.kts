import org.jetbrains.compose.desktop.application.dsl.TargetFormat


plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
}


dependencies {
    implementation(project(":common"))
    implementation(compose.desktop.currentOs)
}


compose.desktop {
    application {
        mainClass = "com.example.samsara.desktop.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "SamsaraDesktop"
            packageVersion = "1.0.0"

            modules("java.sql")

            windows {
                menuGroup = "Compose Examples"
                // see https://wixtoolset.org/documentation/manual/v3/howtos/general/generate_guids.html
                upgradeUuid = "7f73d4cd-91bb-4ca1-9982-2ee6633ec43e"
            }
        }
    }
}
