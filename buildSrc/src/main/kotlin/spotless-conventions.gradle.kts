import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

plugins {
    id("com.diffplug.spotless")
}

// Configure Spotless for all projects
spotless {
    // Configure Kotlin formatting
    kotlin {
        target("**/*.kt")
        targetExclude("**/build/**")
        
        // Use KtLint for Kotlin formatting with explicit version
        ktlint("1.2.1")
            .editorConfigOverride(
                mapOf(
                    "ktlint_standard_no-wildcard-imports" to "false",
                    "ktlint_standard_no-unused-imports" to "true"
                )
            )
        
        // License header
        licenseHeaderFile("$rootDir/spotless/copyright.kt")
        
        // Trim trailing whitespace
        trimTrailingWhitespace()
        
        // End with newline
        endWithNewline()
    }
    
    // Configure Kotlin Gradle scripts
    kotlinGradle {
        target("**/*.gradle.kts")
        targetExclude("**/build/**")
        ktlint("1.2.1")
        trimTrailingWhitespace()
        indentWithTabs()
        endWithNewline()
    }
    
    // Format markdown files
    format("markdown") {
        target("**/*.md")
        targetExclude("**/build/**", "**/.gradle/**")
        trimTrailingWhitespace()
        indentWithSpaces(2)
        endWithNewline()
    }
}
