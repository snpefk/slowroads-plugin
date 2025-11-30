# Slowroads-plugin

Silly Gradle plugin that starts [slow roads](https://slowroads.io/) while your build is running.

**I DON'T OWN THE GAME**. If you like it, please, donate author on [ko-fi](https://ko-fi.com/slowroads).

## Installation

~~Plugin hosted on [Plugin Portal](https://plugins.gradle.org/). Just add this to your root `build.gradle`:~~

The plugin was rejected by Gradle Plugin Portal. You have to install it from GitHub Packages, which requires some effort
to authenticate.

### GitHub Packages

You must add the GitHub Packages repository to the `pluginManagement` repositories in your root `settings.gradle(.kts)`
and provide credentials.

Prerequisites:

- A GitHub personal access token (classic) with at least `read:packages` scope. If the package is in a private repo,
  also add `repo` scope
- Your GitHub username

1) Add credentials to `~/.gradle/gradle.properties` or your project `gradle.properties`:

```
gpr.user=YOUR_GITHUB_USERNAME
gpr.key=YOUR_GITHUB_PAT
```

2) Point plugin resolution to GitHub Packages in `settings.gradle.kts`:

```kotlin
pluginManagement {
    repositories {
        gradlePluginPortal()
        maven {
            url = uri("https://maven.pkg.github.com/snpefk/slowroads-plugin")
            credentials {
                // Will use values from gradle.properties if present, else fall back to env (useful in CI)
                username = providers.gradleProperty("gpr.user")
                    .get()
                password = providers.gradleProperty("gpr.key")
                    .get()
            }
        }
    }
}
```

3) Apply the plugin in your root build script (`build.gradle.kts`):

```kotlin
plugins {
    id("io.github.snpefk.slowroads-plugin") version "1.0"
}
```

## How to test

Run the sample project

```bash
./gradlew -p samples/simple dummy
``` 