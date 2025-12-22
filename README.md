# Loopwish · Android

![Loopwish banner](https://raw.githubusercontent.com/loopwish/shared/main/assets/logos/loopwish-banner.png)

Loopwish er en ønskeliste-app som lar deg dele ønsker med familie og venner. Ønsk. Del. Få. Sammen.

Dette repoet inneholder Android-klienten for Loopwish.

## Plattformstøtte

- Android 8.0+ (API 26+)

## Teknologi-stack

- Kotlin
- Jetpack Compose
- Gradle (Kotlin DSL)
- ktlint

## Kom i gang

### Forutsetninger

- Android Studio (anbefalt)
- JDK 17

### Bygg og test

```bash
./gradlew ktlintCheck testDebugUnitTest assembleDebug
```

## Prosjektstruktur

```text
app/
  src/
    main/
      java/com/loopwish/
      res/
    test/
gradle/
build.gradle.kts
settings.gradle.kts
gradle.properties
```

## Bidra

Se [CONTRIBUTING.md](CONTRIBUTING.md).

## Lisens

Lisensiert under MIT. Se [LICENSE](LICENSE).

## Relaterte repositories

- https://github.com/loopwish/apple
- https://github.com/loopwish/android
- https://github.com/loopwish/windows
- https://github.com/loopwish/web
- https://github.com/loopwish/backend
- https://github.com/loopwish/shared
