pluginManagement {
    val kotlinVersion: String by settings
    plugins {
        kotlin("multiplatform") version kotlinVersion apply false
    }
    repositories {
        google()
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()

    }

    dependencyResolutionManagement {
        repositories {
            google()
            mavenCentral()

            // your repos
        }
    }


}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")


rootProject.name = "KspStandalone"

include(":sandbox")
include(":runner")
include(":test-ksp")

