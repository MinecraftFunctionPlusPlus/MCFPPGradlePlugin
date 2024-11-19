package top.mcfpp.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class MCFPPGradlePlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.tasks.register("compileMCFPP", MCFPPCompileTask::class.java){
            it.group = "build"
            it.description = "Compiles MCFPP"
        }
    }
}