# GitHub Copilot Agent Instructions (Android)

This repository contains the Loopwish Android client.

## Stack

- Kotlin
- Jetpack Compose
- Min SDK: 26 (Android 8.0+)
- Build: Gradle (Kotlin DSL)
- Lint: ktlint

## Goals

- Keep the project buildable with `./gradlew`.
- Prefer small changes with clear reasoning.
- Match the package namespace `com.loopwish`.

## Coding conventions

- Prefer Compose UI patterns; keep business logic out of Composables when it grows (move to appropriate layers).
- Avoid adding new Gradle plugins/dependencies unless necessary.
- Keep Android APIs compatible with minSdk 26.

## Validation

Before opening a PR, run:

```bash
./gradlew ktlintCheck testDebugUnitTest assembleDebug
```

## Safety

- Never commit keystores (`*.jks`, `*.keystore`) or credentials.
- No secrets in `local.properties` or committed config.
