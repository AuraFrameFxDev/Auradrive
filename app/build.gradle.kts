import org.jetbrains.kotlin.gradle.dsl.JvmTarget

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)

    id("org.openapi.generator") version "7.14.0"
    id("io.gitlab.arturbosch.detekt")
    id("com.diffplug.spotless")

}

android {
    namespace = "dev.aurakai.auraframefx"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "dev.aurakai.auraframefx"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["clearPackageData"] = "true"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            applicationIdSuffix = ".debug"
            isDebuggable = true
        }
    }

    buildFeatures {
        viewBinding = true
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    packaging {
        resources {
            excludes += setOf(
                "/META-INF/{AL2.0,LGPL2.1}",
                "/META-INF/AL2.0",
                "/META-INF/LGPL2.1",
                "META-INF/LICENSE*",
                "META-INF/NOTICE*",
                "META-INF/*.version",
                "META-INF/proguard/*",
                "/*.properties",
                "fabric/*.properties",
                "DebugProbesKt.bin"
            )
        }
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }

    // OpenAPI Generator configuration
    openApiGenerate {
        generatorName.set("kotlin")
        inputSpec.set("${rootProject.projectDir}/openapi.yml")
        outputDir.set(layout.buildDirectory.dir("generated/openapi").get().asFile.path)
        configFile.set("${rootProject.projectDir}/openapi-generator-config.json")
        skipOverwrite.set(false)
        library.set("jvm-retrofit2")
        apiPackage.set("dev.aurakai.auraframefx.api.generated")
        modelPackage.set("dev.aurakai.auraframefx.model.generated")
        configOptions.set(
            mapOf(
                "useCoroutines" to "true",
                "serializationLibrary" to "kotlinx_serialization",
                "enumPropertyNaming" to "UPPERCASE"
            )
        )
    }

    sourceSets.getByName("main") {
        java.srcDir(layout.buildDirectory.dir("generated/openapi/src/main/kotlin"))
    }

    tasks.named("preBuild") {
        dependsOn("openApiGenerate")
    }
}

dependencies {
    // Core AndroidX
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Core library desugaring
    coreLibraryDesugaring(libs.coreLibraryDesugaring)

    // Testing
    testImplementation(libs.bundles.testing)
    testRuntimeOnly(libs.junit.engine)

    // Android Instrumentation Tests
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.compiler)

    // Debug implementations
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // System interaction and documentation
    // Remove implementation(libs.yuki) and implementation(libs.lsposed) if not defined in libs.versions.toml
    // Dokka for documentation
}
