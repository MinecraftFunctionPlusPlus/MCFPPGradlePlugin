package top.mcfpp.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

abstract class MCFPPCompileTask: DefaultTask() {
    @TaskAction
    fun compile() {
        println("Compiling MCFPP")
    }
}