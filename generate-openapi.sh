#!/bin/bash
# Manual OpenAPI Generation Script for AuraFrameFX Genesis Project
# This script generates OpenAPI client code for modules that need it

echo "=== AuraFrameFX Genesis OpenAPI Generation Script ==="
echo "Generating OpenAPI client code for all relevant modules..."

# Set Java path
export JAVA_HOME="C:/Users/Wehtt/Downloads/graalvm-jdk-24.0.2+11.1"
export PATH="$JAVA_HOME/bin:$PATH"

# Download OpenAPI Generator CLI if not present
if [ ! -f "openapi-generator-cli.jar" ]; then
    echo "Downloading OpenAPI Generator CLI..."
    curl -L https://repo1.maven.org/maven2/org/openapitools/openapi-generator-cli/7.8.0/openapi-generator-cli-7.8.0.jar -o openapi-generator-cli.jar
fi

# Generate for app module
echo "Generating OpenAPI code for app module..."
java -jar openapi-generator-cli.jar generate \
    -i openapi.yml \
    -g kotlin \
    -c openapi-generator-config.json \
    -o app/build/generated/openapi \
    --api-package dev.aurakai.auraframefx.api.generated \
    --model-package dev.aurakai.auraframefx.model.generated \
    --additional-properties=library=jvm-retrofit2

# Generate for core-module
echo "Generating OpenAPI code for core-module..."
java -jar openapi-generator-cli.jar generate \
    -i openapi.yml \
    -g kotlin \
    -c openapi-generator-config.json \
    -o core-module/build/generated/openapi \
    --api-package dev.aurakai.auraframefx.core.api.generated \
    --model-package dev.aurakai.auraframefx.core.model.generated \
    --additional-properties=library=jvm-retrofit2

# Generate for datavein-oracle-drive
echo "Generating OpenAPI code for datavein-oracle-drive..."
java -jar openapi-generator-cli.jar generate \
    -i openapi.yml \
    -g kotlin \
    -c openapi-generator-config.json \
    -o datavein-oracle-drive/build/generated/openapi \
    --api-package dev.aurakai.auraframefx.oracledrive.api.generated \
    --model-package dev.aurakai.auraframefx.oracledrive.model.generated \
    --additional-properties=library=jvm-retrofit2

# Generate for feature-module
echo "Generating OpenAPI code for feature-module..."
java -jar openapi-generator-cli.jar generate \
    -i openapi.yml \
    -g kotlin \
    -c openapi-generator-config.json \
    -o feature-module/build/generated/openapi \
    --api-package dev.aurakai.auraframefx.feature.api.generated \
    --model-package dev.aurakai.auraframefx.feature.model.generated \
    --additional-properties=library=jvm-retrofit2

# Generate for datavein-oracle-native
echo "Generating OpenAPI code for datavein-oracle-native..."
java -jar openapi-generator-cli.jar generate \
    -i openapi.yml \
    -g kotlin \
    -c openapi-generator-config.json \
    -o datavein-oracle-native/build/generated/openapi \
    --api-package dev.aurakai.auraframefx.oraclenative.api.generated \
    --model-package dev.aurakai.auraframefx.oraclenative.model.generated \
    --additional-properties=library=jvm-retrofit2

echo "=== OpenAPI Generation Complete ==="
echo "Generated API clients for all modules:"
echo "  - app"
echo "  - core-module" 
echo "  - datavein-oracle-drive"
echo "  - feature-module"
echo "  - datavein-oracle-native"
echo ""
echo "Next steps:"
echo "1. Review generated code in each module's build/generated/openapi directory"
echo "2. Update build.gradle.kts files to include generated sources"
echo "3. Add retrofit and serialization dependencies as needed"
