import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    id("org.jetbrains.compose.hot-reload")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.nomanr.plugin.lumo")
    kotlin("plugin.serialization")
    id("com.google.devtools.ksp")
}

group = "io.github.smfdrummer"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs) {
        exclude(group = "org.jetbrains.compose.material", module = "material-desktop")
    }
    implementation(compose.components.resources)
    implementation(compose.material3)
    implementation(compose.materialIconsExtended)
    api("io.github.smfdrummer:medal-core:0.6")
    api("com.nomanr:composables:1.1.1")
    implementation("io.github.fornewid:material-motion-compose-core:2.0.1")

    implementation(project.dependencies.platform("io.insert-koin:koin-bom:4.1.0"))
    implementation("io.insert-koin:koin-core")
    implementation("io.insert-koin:koin-compose")
    implementation("io.insert-koin:koin-compose-viewmodel")
    implementation("io.insert-koin:koin-compose-viewmodel-navigation")
    implementation("io.insert-koin:koin-ktor")
    implementation("io.insert-koin:koin-logger-slf4j")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.9.0")

    implementation(platform("io.arrow-kt:arrow-stack:2.1.2"))
    implementation("io.arrow-kt:arrow-core")
    implementation("io.arrow-kt:arrow-fx-coroutines")
    implementation("io.arrow-kt:arrow-optics")
    ksp("io.arrow-kt:arrow-optics-ksp-plugin:2.1.0")

    // implementation("com.squareup.okio:okio:3.10.2")
}

compose.desktop {
    application {
        mainClass = "io.github.smfdrummer.medal_app_desktop.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb, TargetFormat.Exe)
            packageName = "Medal-app-desktop"
            packageVersion = "1.0.0"
            vendor = "SMFDrummer"
            description = "An all-purpose Plants vs Zombies 2 Chinese version tool. Compose for desktop distribution."
            copyright = "Copyright © 2025 SMFDrummer. All rights reserved."
            licenseFile.set(project.file("LICENSE"))

            macOS {
                bundleID = "io.github.smfdrummer.medal"
                dockName = "Medal-app-desktop"
                iconFile.set(project.file("icons/ic_launcher.icns"))
            }

            windows {
                shortcut = true
                perUserInstall = true
                dirChooser = true
                upgradeUuid = "f3711667-91a9-4535-8c0b-d61ac7499a2d"
                iconFile.set(project.file("icons/ic_launcher.ico"))
            }

            linux {
                iconFile.set(project.file("icons/ic_launcher.png"))
            }
        }
    }
}
