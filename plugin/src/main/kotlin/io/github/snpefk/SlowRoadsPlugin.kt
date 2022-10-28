package io.github.snpefk

import java.io.IOException
import java.util.Locale
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.slf4j.LoggerFactory

class SlowRoadsPlugin : Plugin<Project> {

    companion object {
        private const val SLOW_ROADS_URL = "https://slowroads.io"
    }

    private var runningGame: Process? = null
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun apply(project: Project) {
        project.afterEvaluate { start() }
        project.gradle.buildFinished { stop() }
    }

    private fun start() {
        val os = System.getProperty("os.name").lowercase(Locale.getDefault())

        runningGame = when {
            "linux" in os -> onLinux()
            "win" in os -> onWin()
            "mac" in os -> onMac()
            else -> {
                logger.warn("Unsupported OS: $os")
                null
            }
        }
    }

    private fun stop() {
        runningGame?.destroy()
    }

    private fun onLinux(): Process? {
        val runtime = Runtime.getRuntime()

        val browsers = listOf("google-chrome --new-window", "chromium --new-window", "firefox --new-window")
        for (browser in browsers) {
            try {
                return runtime.exec("$browser $SLOW_ROADS_URL")
            } catch (e: IOException) {
                logger.info("Tried $browser; Didn't work")
            }
        }
        logger.warn("Tried all browsers. None worked")
        return null
    }

    private fun onWin(): Process? {
        val runtime = Runtime.getRuntime()

        return try {
            runtime.exec("rundll32 url.dll,FileProtocolHandler $SLOW_ROADS_URL")
        } catch (e: IOException) {
            logger.warn("Failed to start on Win")
            null
        }
    }

    private fun onMac(): Process? {
        val runtime = Runtime.getRuntime()

        return try {
            runtime.exec("open $SLOW_ROADS_URL")
        } catch (e: IOException) {
            logger.warn("Tried to start on Mac")
            null
        }
    }
}