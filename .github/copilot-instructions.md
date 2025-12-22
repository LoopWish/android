# GitHub Copilot Agent Instructions (Android)

This repository contains the Loopwish Android client.

## Organization-wide standards (Loopwish)

- Keep PRs small and reviewable; avoid drive-by refactors.
- Do not commit secrets (API keys, tokens, credentials) or `.env` files.
- Prefer secure-by-default changes; avoid logging sensitive data.
- Update docs and tests when behavior changes.
- Keep CI green and run the repo’s validation commands before opening a PR.
- Follow the existing code style and architecture; copy patterns already used in the repo.

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

## Git & GitHub procedure

- `main` is protected: create a feature branch, push, and open a PR.
- If updating shared vendored assets:
	- Update `.loopwish/shared.ref` to the desired shared tag (example: `v0.1.1`).
	- Ensure `vendor/shared/...` matches `shared@<tag>` exactly.
	- Update `scripts/verify_shared_vendor.sh` if new files are added to the vendoring contract.
	- Run `./scripts/verify_shared_vendor.sh` to validate vendored files match the pinned tag.
- Prefer merging shared changes/tag first, then updating this repo to pin/vendor that tag.
- After merge, clean up local branches safely:

```bash
git fetch --prune
git branch --merged origin/main
```

## Safety

- Never commit keystores (`*.jks`, `*.keystore`) or credentials.
- No secrets in `local.properties` or committed config.
