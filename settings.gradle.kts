pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenLocal()
    }
}

rootProject.name = "slowroads-plugin"
include("plugin")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}