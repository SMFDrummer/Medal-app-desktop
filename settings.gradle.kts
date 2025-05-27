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
        id("com.nomanr.plugin.lumo").version(extra["lumo.version"] as String)
    }
}

rootProject.name = "Medal-app-desktop"
includeBuild("../Medal-core") {
    dependencySubstitution {
        substitute(module("io.github.smfdrummer:medal-core"))
            .using(project(":"))
    }
}
