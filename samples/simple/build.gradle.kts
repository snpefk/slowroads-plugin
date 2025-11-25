plugins {
    id("io.github.snpefk.slowroads-plugin") version "1.0"
}

tasks.register("dummy") {
    doLast {
        val timeout = 10_000L
        println("Dummy task start running for $timeout")
        Thread.sleep(timeout)
    }
}