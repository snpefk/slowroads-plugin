plugins {
    `java-gradle-plugin`
    id("org.jetbrains.kotlin.jvm") version "1.6.21"
    id("com.gradle.plugin-publish") version "1.0.0"
    id("maven-publish")
}

val owner = "snpefk"
val repository = "slowroads-plugin"

group = "io.github.snpefk"
version = "1.0"

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

pluginBundle {
    website = "https://github.com/$owner/$repository#readme"
    vcsUrl = "https://github.com/$owner/$repository"
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

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/$owner/$repository")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: owner
                password = project.findProperty("gpr.key") as String?
            }
        }
    }

    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}