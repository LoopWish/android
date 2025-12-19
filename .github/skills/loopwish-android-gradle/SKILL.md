---
name: loopwish-android-gradle
description: Workflows for the Loopwish Android app (Kotlin + Jetpack Compose): where to change code, how to run Gradle checks, and how to keep CI green.
---

# Loopwish Android (Gradle/Compose) Skill

Use this skill when working in the Loopwish **android repository**.

## When to use

- Editing Android UI in Jetpack Compose.
- Updating Kotlin code, resources, or app structure.
- Fixing ktlint/unit test/assemble failures.

## Project specifics

- Package/namespace: `com.loopwish`
- Min SDK: 26 (Android 8.0+)
- JDK: 17

## Validation (matches CI)

From the repo root:

```bash
chmod +x ./gradlew
./gradlew ktlintCheck testDebugUnitTest assembleDebug
```

## Typical workflow for a change

1. Make code changes under `app/src/main` (app code) or `app/src/test` (unit tests).
2. Keep business logic out of Composables as it grows (extract into appropriate layers).
3. Run the validation command above before opening a PR.

## Notes

- Avoid adding new Gradle plugins/dependencies unless necessary.
- Keep APIs compatible with minSdk 26.

## Example prompts

- “Add a simple Compose screen and a preview; ensure ktlint passes.”
- “Fix the failing unit test in `app/src/test` and keep behavior correct.”
