plugins {
    `kotlin-dsl`
}

kotlin {
    jvmToolchain(24)  // <-- Toolchain for buildSrc itself
    compilerOptions {
        languageVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_2)
        apiVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_2)
    }
}