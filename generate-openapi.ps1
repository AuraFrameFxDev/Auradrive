# Manual OpenAPI Generation Script for AuraFrameFX Genesis Project
# This script generates OpenAPI client code for modules that need it

Write-Host "=== AuraFrameFX Genesis OpenAPI Generation Script ===" -ForegroundColor Green
Write-Host "Generating OpenAPI client code for all relevant modules..." -ForegroundColor Yellow

# Set Java path
$env:JAVA_HOME = "C:\Users\Wehtt\Downloads\graalvm-jdk-24.0.2+11.1"
$env:PATH = "$env:JAVA_HOME\bin;$env:PATH"

# Download OpenAPI Generator CLI if not present
if (-not (Test-Path "openapi-generator-cli.jar")) {
    Write-Host "Downloading OpenAPI Generator CLI..." -ForegroundColor Yellow
    Invoke-WebRequest -Uri "https://repo1.maven.org/maven2/org/openapitools/openapi-generator-cli/7.8.0/openapi-generator-cli-7.8.0.jar" -OutFile "openapi-generator-cli.jar"
}

# Function to generate OpenAPI code for a module
function Generate-OpenAPI {
    param(
        [string]$ModuleName,
        [string]$ApiPackage,
        [string]$ModelPackage
    )
    
    Write-Host "Generating OpenAPI code for $ModuleName module..." -ForegroundColor Cyan
    
    # Create output directory if it doesn't exist
    $outputDir = "$ModuleName\build\generated\openapi"
    if (-not (Test-Path $outputDir)) {
        New-Item -ItemType Directory -Path $outputDir -Force | Out-Null
    }
    
    & java -jar openapi-generator-cli.jar generate `
        -i openapi.yml `
        -g kotlin `
        -c openapi-generator-config.json `
        -o $outputDir `
        --api-package $ApiPackage `
        --model-package $ModelPackage `
        --additional-properties=library=jvm-retrofit2
}

# Generate for each module
Generate-OpenAPI -ModuleName "app" -ApiPackage "dev.aurakai.auraframefx.api.generated" -ModelPackage "dev.aurakai.auraframefx.model.generated"
Generate-OpenAPI -ModuleName "core-module" -ApiPackage "dev.aurakai.auraframefx.core.api.generated" -ModelPackage "dev.aurakai.auraframefx.core.model.generated"
Generate-OpenAPI -ModuleName "datavein-oracle-drive" -ApiPackage "dev.aurakai.auraframefx.oracledrive.api.generated" -ModelPackage "dev.aurakai.auraframefx.oracledrive.model.generated"
Generate-OpenAPI -ModuleName "feature-module" -ApiPackage "dev.aurakai.auraframefx.feature.api.generated" -ModelPackage "dev.aurakai.auraframefx.feature.model.generated"
Generate-OpenAPI -ModuleName "datavein-oracle-native" -ApiPackage "dev.aurakai.auraframefx.oraclenative.api.generated" -ModelPackage "dev.aurakai.auraframefx.oraclenative.model.generated"

Write-Host "=== OpenAPI Generation Complete ===" -ForegroundColor Green
Write-Host "Generated API clients for all modules:" -ForegroundColor White
Write-Host "  - app" -ForegroundColor White
Write-Host "  - core-module" -ForegroundColor White
Write-Host "  - datavein-oracle-drive" -ForegroundColor White
Write-Host "  - feature-module" -ForegroundColor White
Write-Host "  - datavein-oracle-native" -ForegroundColor White
Write-Host ""
Write-Host "Next steps:" -ForegroundColor Yellow
Write-Host "1. Review generated code in each module's build/generated/openapi directory" -ForegroundColor White
Write-Host "2. Update build.gradle.kts files to include generated sources" -ForegroundColor White
Write-Host "3. Add retrofit and serialization dependencies as needed" -ForegroundColor White
