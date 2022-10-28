plugins {
    `java-gradle-plugin`
    `maven-publish`
    id("org.jetbrains.kotlin.jvm") version "1.6.21"
    id("slowroads-plugin") version "1.0"
}

group = "io.github.snpefk"
version = "1.0"

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

gradlePlugin {
    plugins.create("slowroads") {
        id = "slowroads-plugin"
        displayName = "SlowRoads Plugin"
        description = "The worst thing you can add to your build"
        implementationClass = "io.github.snpefk.SlowRoadsPlugin"
    }
}