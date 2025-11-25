package io.github.snpefk

import java.io.IOException
import java.util.Locale
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logging
import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters
import org.gradle.api.services.BuildServiceRegistry

class SlowRoad {

    private var runningGame: Process? = null
    private val logger = Logging.getLogger("SlowRoad")

    companion object {
        private const val SLOW_ROADS_URL = "https://slowroads.io"
    }

    fun start() {
        val os = System.getProperty("os.name").lowercase(Locale.getDefault())

        runningGame = when {
            "linux" in os -> onLinux()
            "win" in os -> onWin()
            "mac" in os -> onMac()
            else -> {
                logger.error("Unsupported OS: $os")
                null
            }
        }
    }

    fun stop() {
        logger.info("Stopping SlowRoads, let's get back to work")
        runningGame?.destroy()
    }

    fun onLinux(): Process? {
        val runtime = Runtime.getRuntime()

        val browsers = listOf("google-chrome --new-window", "chromium --new-window", "firefox --new-window")
        for (browser in browsers) {
            try {
                return runtime.exec("$browser $SLOW_ROADS_URL")
            } catch (e: IOException) {
                logger.warn("Can't start SlowRoad in $browser: $e")
            }
        }

        logger.error("The system doesn't have installed supported browsers to run SlowRoad")
        return null
    }

    fun onWin(): Process? {
        val runtime = Runtime.getRuntime()

        return try {
            runtime.exec("rundll32 url.dll,FileProtocolHandler $SLOW_ROADS_URL")
        } catch (e: IOException) {
            logger.error("Error while starting on Win: $e")
            null
        }
    }

    fun onMac(): Process? {
        val runtime = Runtime.getRuntime()

        return try {
            runtime.exec("open $SLOW_ROADS_URL")
        } catch (e: IOException) {
            logger.error("Error while starting on Mac: $e")
            null
        }
    }
}

abstract class BuildLifecycleService :
    BuildService<BuildLifecycleService.Params>,
    AutoCloseable {

    interface Params : BuildServiceParameters

    private val slowRoad = SlowRoad()

    init {
        slowRoad.start()
    }

    override fun close() {
        slowRoad.stop()
    }
}

class SlowRoadsPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val gradle = project.gradle
        val sharedServices: BuildServiceRegistry = gradle.sharedServices

        val serviceProvider = sharedServices.registerIfAbsent(
            "buildLifecycleServiceSlowRoad",
            BuildLifecycleService::class.java,
            {}
        )

        gradle.taskGraph.whenReady {
            serviceProvider.get()
        }
    }
}