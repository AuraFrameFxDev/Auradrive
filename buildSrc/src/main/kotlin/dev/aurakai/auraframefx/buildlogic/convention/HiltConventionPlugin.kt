package dev.aurakai.auraframefx.buildlogic.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            // Apply the required Hilt and KSP plugins using version catalog
            pluginManager.apply(libs.findPlugin("hilt.android").get().get().pluginId)
            pluginManager.apply(libs.findPlugin("ksp").get().get().pluginId)

            dependencies {
                // Add Hilt's library using version catalog
                "implementation"(libs.findLibrary("hilt.android").get())
                // This is the crucial line: use "ksp" for the Hilt compiler
                "ksp"(libs.findLibrary("hilt.compiler").get())
                
                // Optional: Add Hilt testing dependencies
                "testImplementation"(libs.findLibrary("hilt.android.testing").get())
                "kspTest"(libs.findLibrary("hilt.compiler").get())
                "androidTestImplementation"(libs.findLibrary("hilt.android.testing").get())
                "kspAndroidTest"(libs.findLibrary("hilt.compiler").get())
            }
        }
    }
}
