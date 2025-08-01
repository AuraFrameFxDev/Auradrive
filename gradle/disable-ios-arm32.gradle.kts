// This script disables iOS ARM32 target which is causing issues with Compose
// It should be applied before any Compose plugin is configured

// Disable iOS ARM32 target
System.setProperty("kotlin.native.ignoreDisabledTargets", "true")
System.setProperty("kotlin.native.disableTargets", "ios_arm32")
System.setProperty("org.jetbrains.kotlin.native.ignoreDisabledTargets", "true")
System.setProperty("org.jetbrains.compose.experimental.uikit.enabled", "false")

// Apply to all projects
gradle.projectsLoaded {
    rootProject.allprojects { project ->
        project.plugins.withId("org.jetbrains.compose") {
            // Configure Compose to skip unsupported targets
            project.extensions.configure<org.jetbrains.compose.ExperimentalComposeLibraryExtension> {
                // Explicitly disable iOS targets to prevent initialization
                skipNativeTargets.addAll("iosArm32")
            }
        }
    }
}
