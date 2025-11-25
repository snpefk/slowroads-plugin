# Slowroads-plugin

Just a silly Gradle plugin that start [slow roads](https://slowroads.io/) while your build running. 

**I DON'T OWN THE GAME**. If you like it, please, donate author on [ko-fi](https://ko-fi.com/slowroads).

## Installation 

Plugin hosted on [Plugin Portal](https://plugins.gradle.org/). Just add this to your root `build.gradle`:  

```groovy
// build.gradle
plugins {
    id 'io.github.snpefk.slowroads-plugin' version '1.0' 
} 
```

```kotlin
// build.gradle.kts
plugins {
    id("io.github.snpefk.slowroads-plugin") version "1.0" 
}
```

## How to test 

Run the sample project 

```bash
./gradlew -p samples/simple dummy
``` 