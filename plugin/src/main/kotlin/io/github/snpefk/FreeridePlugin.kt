package io.github.snpefk

import org.gradle.api.Plugin
import org.gradle.api.Project

class FreeridePlugin : Plugin<Project> {

    override fun apply(project: Project) {

        project.tasks.register("greeting") { task ->
            task.doLast {
            }
        }
    }
}
