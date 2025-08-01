plugins {
    id("android-library-conventions")
    id("detekt-conventions")
    id("spotless-conventions")
}

dependencies {
    // Add module-specific dependencies here
    implementation(libs.yuki) // If defined in libs.versions.toml
    implementation(libs.lsposed) // If defined in libs.versions.toml
    dokkaHtmlPlugin(libs.dokka)
}
