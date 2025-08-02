System.setProperty("https.protocols", "TLSv1.2")

pluginManagement {
    repositories {
        mavenLocal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
        mavenCentral()
    }

    plugins {
        kotlin("jvm").version(extra["kotlin.version"] as String)
        id("org.jetbrains.compose").version(extra["compose.version"] as String)
        id("org.jetbrains.kotlin.plugin.compose").version(extra["kotlin.version"] as String)
        id("org.jetbrains.compose.hot-reload").version(extra["compose.hot-reload"] as String)
        id("com.nomanr.plugin.lumo").version(extra["lumo.version"] as String)
        kotlin("plugin.serialization").version(extra["kotlin.version"] as String)
        id("com.google.devtools.ksp").version(extra["google.ksp"] as String)
        id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
    }
}

rootProject.name = "Medal-app-desktop"
includeBuild("../Medal-core") {
    dependencySubstitution {
        substitute(module("io.github.smfdrummer:medal-core"))
            .using(project(":"))
    }
}
