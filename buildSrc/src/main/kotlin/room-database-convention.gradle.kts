import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

plugins {
    // This plugin builds on the base library conventions
    id("android-library-conventions")
}

dependencies {
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    add("ksp", "androidx.room:room-compiler:2.6.1")
    testImplementation("androidx.room:room-testing:2.6.1")
}

android {
    // Room schema export configuration
    defaultConfig {
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }
    }
}
