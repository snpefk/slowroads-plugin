plugins {
    `java-gradle-plugin`
    id("org.jetbrains.kotlin.jvm") version "1.6.21"
    id("com.gradle.plugin-publish") version "1.0.0"
}

group = "io.github.snpefk"
version = "1.0"

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

pluginBundle {
    website = "https://snpefk.github.io"
    vcsUrl = "https://github.com/snpefk/slowroads-plugin"
    tags = listOf("cringe", "jokes", "games")
}

gradlePlugin {
    plugins.create("slowroads") {
        id = "io.github.snpefk.slowroads-plugin"
        displayName = "SlowRoads Plugin"
        description = "The worst thing you can add to your build"
        implementationClass = "io.github.snpefk.SlowRoadsPlugin"
    }
}