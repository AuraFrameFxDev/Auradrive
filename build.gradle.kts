plugins {
    // Remove explicit version for plugins already on the classpath
    id("com.android.application") apply false
    id("com.android.library") apply false
    alias(libs.plugins.kotlin.compose) apply false
}

// Configure Java toolchain for all subprojects
subprojects {
    // Apply toolchain configuration to all projects
    plugins.withType<JavaBasePlugin> {
        configure<JavaPluginExtension> {
            toolchain {
                languageVersion.set(JavaLanguageVersion.of(24))
                vendor.set(JvmVendorSpec.ADOPTIUM)
            }
        }
    }

    // Configure Kotlin compiler options for all Kotlin projects
    plugins.withId("org.jetbrains.kotlin.android") {
        configure<org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension> {
            compilerOptions {
                jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_24)
                apiVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_2)
                languageVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_2)

                // K2 Compiler flags
                freeCompilerArgs.addAll(
                    "-Xskip-prerelease-check",
                    "-opt-in=kotlin.RequiresOptIn",
                    "-opt-in=kotlin.ExperimentalStdlibApi",
                    "-Xjvm-default=all",
                    "-progressive"
                )
            }
        }
    }

    // Configure Android common settings
    plugins.withId("com.android.application") {
        configure<com.android.build.gradle.AppExtension> {
            compileSdkVersion(libs.versions.compileSdk.get().toInt())

            defaultConfig {
                minSdk = libs.versions.minSdk.get().toInt()
                targetSdk = libs.versions.targetSdk.get().toInt()
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_24
                targetCompatibility = JavaVersion.VERSION_24
                isCoreLibraryDesugaringEnabled = true
            }
        }
    }

    plugins.withId("com.android.library") {
        configure<com.android.build.gradle.LibraryExtension> {
            compileSdkVersion(libs.versions.compileSdk.get().toInt())

            defaultConfig {
                minSdk = libs.versions.minSdk.get().toInt()
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_24
                targetCompatibility = JavaVersion.VERSION_24
                isCoreLibraryDesugaringEnabled = true
            }
        }
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}