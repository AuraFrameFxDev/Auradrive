# Genesis-OS Build Logic

This module contains the build logic and custom tasks for the Genesis-OS project.

## Build Features

- **Kotlin DSL**: All build scripts use Kotlin DSL for type-safe build configuration
- **Dokka**: Integrated documentation generation with Dokka
- **Dependency Analysis**: Automated dependency analysis to detect unused or misconfigured
  dependencies
- **Build Cache**: Configured build cache for faster incremental builds
- **Configuration Cache**: Enabled for faster build times

## Custom Plugins

### Available Plugins

- `genesis.build` - Main build configuration
- `genesis.docs` - Documentation generation
- `genesis.analysis` - Static analysis and dependency checking

## Build Optimization

The build is optimized with:

- Parallel task execution
- Incremental compilation
- K2 compiler optimizations
- Build cache configuration
- Configuration caching
- Dependency verification

## Usage

### Generate Documentation

```bash
./gradlew dokkaHtml
```

### Analyze Dependencies

```bash
./gradlew buildHealth
```

### Update Dependencies

```bash
./gradlew useLatestVersions
```

## Contributing

When adding new build logic:

1. Add your code to `src/main/kotlin`
2. Document new tasks and extensions
3. Update this documentation if needed
4. Test with `./gradlew build`

## License

This project is licensed under the terms of the MIT license.
