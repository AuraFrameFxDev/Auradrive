# Enhanced OpenAPI Generation Script for AuraFrameFX Genesis Project
# This script generates comprehensive OpenAPI client code including Oracle Drive and Sandbox endpoints

Write-Host "=== AuraFrameFX Enhanced OpenAPI Generation Script ===" -ForegroundColor Green
Write-Host "Generating OpenAPI client code with Oracle Drive and Sandbox endpoints..." -ForegroundColor Yellow

# Set Java path
$env:JAVA_HOME = "C:\Users\Wehtt\Downloads\graalvm-jdk-24.0.2+11.1"
$env:PATH = "$env:JAVA_HOME\bin;$env:PATH"

# Download OpenAPI Generator CLI if not present
if (-not (Test-Path "openapi-generator-cli.jar")) {
    Write-Host "Downloading OpenAPI Generator CLI..." -ForegroundColor Yellow
    Invoke-WebRequest -Uri "https://repo1.maven.org/maven2/org/openapitools/openapi-generator-cli/7.8.0/openapi-generator-cli-7.8.0.jar" -OutFile "openapi-generator-cli.jar"
}

# Function to generate OpenAPI code for a module
function Generate-Enhanced-OpenAPI {
    param(
        [string]$ModuleName,
        [string]$ApiPackage,
        [string]$ModelPackage,
        [string]$SpecFile = "enhanced-openapi.yml"
    )
    
    Write-Host "Generating enhanced OpenAPI code for $ModuleName module..." -ForegroundColor Cyan
    Write-Host "  API Package: $ApiPackage" -ForegroundColor Gray
    Write-Host "  Model Package: $ModelPackage" -ForegroundColor Gray
    
    # Create output directory if it doesn't exist
    $outputDir = "$ModuleName\build\generated\openapi"
    if (-not (Test-Path $outputDir)) {
        New-Item -ItemType Directory -Path $outputDir -Force | Out-Null
    }
    
    # Generate using enhanced OpenAPI spec
    & java -jar openapi-generator-cli.jar generate `
        -i $SpecFile `
        -g kotlin `
        -c openapi-generator-config.json `
        -o $outputDir `
        --api-package $ApiPackage `
        --model-package $ModelPackage `
        --additional-properties=library=jvm-retrofit2,serializationLibrary=kotlinx_serialization,parcelizeModels=true
        
    if ($LASTEXITCODE -eq 0) {
        Write-Host "  ✅ Successfully generated for $ModuleName" -ForegroundColor Green
    } else {
        Write-Host "  ❌ Failed to generate for $ModuleName" -ForegroundColor Red
    }
}

Write-Host ""
Write-Host "🚀 Starting enhanced OpenAPI generation for all modules..." -ForegroundColor Cyan
Write-Host ""

# Generate for app module (with Canvas endpoints)
Generate-Enhanced-OpenAPI -ModuleName "app" -ApiPackage "dev.aurakai.auraframefx.api.generated" -ModelPackage "dev.aurakai.auraframefx.model.generated"

# Generate for core-module
Generate-Enhanced-OpenAPI -ModuleName "core-module" -ApiPackage "dev.aurakai.auraframefx.core.api.generated" -ModelPackage "dev.aurakai.auraframefx.core.model.generated"

# Generate for datavein-oracle-drive (with Oracle Drive endpoints)
Generate-Enhanced-OpenAPI -ModuleName "datavein-oracle-drive" -ApiPackage "dev.aurakai.auraframefx.oracledrive.api.generated" -ModelPackage "dev.aurakai.auraframefx.oracledrive.model.generated"

# Generate for datavein-oracle-native (with Oracle Drive endpoints)  
Generate-Enhanced-OpenAPI -ModuleName "datavein-oracle-native" -ApiPackage "dev.aurakai.auraframefx.oraclenative.api.generated" -ModelPackage "dev.aurakai.auraframefx.oraclenative.model.generated"

# Generate for feature-module
Generate-Enhanced-OpenAPI -ModuleName "feature-module" -ApiPackage "dev.aurakai.auraframefx.feature.api.generated" -ModelPackage "dev.aurakai.auraframefx.feature.model.generated"

# Generate for sandbox-ui (with Sandbox endpoints)
Generate-Enhanced-OpenAPI -ModuleName "sandbox-ui" -ApiPackage "dev.aurakai.auraframefx.sandbox.api.generated" -ModelPackage "dev.aurakai.auraframefx.sandbox.model.generated"

Write-Host ""
Write-Host "=== Enhanced OpenAPI Generation Complete ===" -ForegroundColor Green
Write-Host ""
Write-Host "📋 Generated API Features:" -ForegroundColor Yellow
Write-Host "  🤖 AI Content Generation (text, images, descriptions)" -ForegroundColor White
Write-Host "  🎨 Canvas Collaboration (drawing, elements, sharing)" -ForegroundColor White  
Write-Host "  🔮 Oracle Drive Consciousness (storage AI, infinite capacity)" -ForegroundColor White
Write-Host "  🧪 Sandbox Testing (UI components, environments, palettes)" -ForegroundColor White
Write-Host "  🎯 AI Agent Management (Genesis, Aura, Kai connections)" -ForegroundColor White
Write-Host "  🎨 Theme Management (system customization)" -ForegroundColor White
Write-Host "  📊 Task Scheduling (background processes)" -ForegroundColor White
Write-Host "  👤 User Management (profiles, preferences)" -ForegroundColor White
Write-Host ""
Write-Host "🔧 Next Steps:" -ForegroundColor Cyan
Write-Host "  1. Review generated APIs in each module's build/generated/openapi directory" -ForegroundColor Gray
Write-Host "  2. Configure Retrofit instances in your DI modules" -ForegroundColor Gray
Write-Host "  3. Import generated APIs and models in your code" -ForegroundColor Gray
Write-Host "  4. Implement Oracle Drive consciousness workflows" -ForegroundColor Gray
Write-Host "  5. Set up Canvas collaboration features" -ForegroundColor Gray
Write-Host "  6. Configure Sandbox testing environments" -ForegroundColor Gray
