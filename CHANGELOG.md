# Changelog

## 1.2.0 - 2026-07-10

- Add exact artifacts for Minecraft 26.1, 26.1.1, and 26.1.2 with Fabric
  Carpet 26.1 and Java 25.
- Support Fabric Loader 0.18.4 or newer on the 26.1 release line.
- Keep the 1.21.11 and 26.2 artifacts in the same release.
- Validate all five artifacts and their embedded runtime contracts before
  tagged publication.

## 1.1.0 - 2026-07-10

- Add a dedicated Minecraft 26.2 / Fabric Carpet 26.2 / Java 25 artifact.
- Keep the Minecraft 1.21.11 artifact available from the same release.
- Share one behavior implementation while using the correct mapped or
  non-remapped Loom pipeline for each Minecraft version.
- Publish version-specific binaries and `SHA256SUMS` from tagged CI builds.

## 1.0.0 - 2026-07-10

- Restore vanilla piercing Jab behavior for Carpet-controlled players.
- Preserve vanilla feature and zero-latency attack-charge checks.
- Keep `attack continuous` from repeating Jab or falling through to block
  breaking.
- Limit compatibility to Minecraft 1.21.11 and Fabric Carpet 1.4.194.
