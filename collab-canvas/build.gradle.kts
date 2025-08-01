plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}


android {
    namespace = "dev.aurakai.auraframefx"
    compileSdk = 36

    defaultConfig {
        applicationId = "dev.aurakai.auraframefx"
        minSdk = 33
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        viewBinding = true
        // dataBinding = true // Uncomment if you use data binding
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.8.2" // Should match your Compose version
    }

    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.29.2"
        }
    }

    ndkVersion = "26.2.11394342"
}

dependencies {
    implementation(libs.compose.ui)
    implementation(libs.compose.material)
    implementation(libs.compose.preview)
    // Add other dependencies/aliases as needed
    implementation(libs.yuki) // If defined in libs.versions.toml
    implementation(libs.lsposed) // If defined in libs.versions.toml
    dokkaHtmlPlugin(libs.dokka)
}