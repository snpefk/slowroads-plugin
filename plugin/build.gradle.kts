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
    website = "https://github.com/snpefk/slowroads-plugin#readme"
    vcsUrl = "https://github.com/snpefk/slowroads-plugin"
    tags = listOf("fun", "build", "game")
}

gradlePlugin {
    plugins.create("slowroads") {
        id = "io.github.snpefk.slowroads-plugin"
        displayName = "SlowRoads Plugin"
        description = "A harmless toy plugin that starts Slow Roads game in your browser while your gradle build is running."
        implementationClass = "io.github.snpefk.SlowRoadsPlugin"
    }
}