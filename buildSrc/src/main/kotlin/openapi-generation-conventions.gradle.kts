import org.openapitools.generator.gradle.plugin.OpenApiGeneratorPlugin
import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
    id("org.openapi.generator")
}

// Apply the OpenAPI generator plugin
apply<OpenApiGeneratorPlugin>()

// Configure OpenAPI generation tasks
tasks.register<GenerateTask>("generateApiClient") {
    group = "openapi"
    description = "Generate API client from OpenAPI specification"
    
    // Default configuration - modules can override these
    val projectOpenApiFile = file("src/main/openapi.yml")
    val rootOpenApiFile = rootProject.file("openapi.yml")
    
    // Use project-specific openapi.yml if it exists, otherwise use root
    inputSpec.set(
        if (projectOpenApiFile.exists()) {
            projectOpenApiFile.absolutePath
        } else {
            rootOpenApiFile.absolutePath
        }
    )
    
    outputDir.set("${project.layout.buildDirectory.get()}/generated/openapi")
    
    // Configuration from openapi-generator-config.json
    generatorName.set("kotlin")
    library.set("jvm-retrofit2")
    
    configOptions.putAll(mapOf(
        "useCoroutines" to "true",
        "serializationLibrary" to "kotlinx_serialization",
        "enumPropertyNaming" to "UPPERCASE",
        "parcelizeModels" to "true",
        "dateLibrary" to "java8",
        "collectionType" to "list",
        "packageName" to "${project.group}.${project.name}.api",
        "apiPackage" to "${project.group}.${project.name}.api.client",
        "modelPackage" to "${project.group}.${project.name}.api.model"
    ))
    
    // Ignore files we don't need
    ignoreFileOverride.set("${rootProject.projectDir}/.openapi-generator-ignore")
}

// Auto-generate API client before compiling
tasks.named("compileKotlin") {
    dependsOn("generateApiClient")
}

// Add generated sources to source sets
android {
    sourceSets {
        getByName("main") {
            java.srcDirs("${project.layout.buildDirectory.get()}/generated/openapi/src/main/kotlin")
        }
    }
}

// Add necessary dependencies for generated code
dependencies {
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-kotlinx-serialization:2.11.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
}
