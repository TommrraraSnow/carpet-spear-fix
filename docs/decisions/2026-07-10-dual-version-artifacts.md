# Publish one artifact per Minecraft runtime contract

Status: accepted

## Context

Minecraft 1.21.11 and calendar-version releases execute the same
piercing-attack fix, but their binary contracts differ. The former uses Fabric
intermediary remapping and Java 21; Minecraft 26.1 and later ship official
Mojang names without intermediary mappings and require Java 25. Carpet and
Fabric Loader versions also differ.

## Decision

Keep one Java and resource source set, then build one Gradle subproject per
verified Minecraft release with explicit dependency, metadata, Java release,
Mixin compatibility, and remapping settings. Publish every runtime JAR under
one patch version with an exact Minecraft suffix:

- `carpet-spear-fix-<version>+mc1.21.11.jar`
- `carpet-spear-fix-<version>+mc26.1.jar`
- `carpet-spear-fix-<version>+mc26.1.1.jar`
- `carpet-spear-fix-<version>+mc26.1.2.jar`
- `carpet-spear-fix-<version>+mc26.2.jar`

Tagged CI must validate every embedded runtime contract, publish exactly the
declared binaries, and generate `SHA256SUMS`.

## Alternatives

- A broad Minecraft dependency range would misrepresent incompatible bytecode
  or accept an unverified future patch release.
- A single 26.1.x JAR would currently have identical behavior bytecode, but
  exact artifacts make Loader reject all version mismatches by construction.
- Copying the source into every project would make behavior fixes drift.
- Replacing an earlier release would remove known-good, immutable rollback
  artifacts.

## Consequences

Operators must install exactly one matching JAR. A JDK 25 build can produce all
artifacts, while the 1.21.11 target remains independently buildable on JDK 21.
The v1.0.0 and v1.1.0 releases and tags remain unchanged for rollback.
The 26.1.x artifacts require a Java 25 runtime but declare Mixin `JAVA_17`
feature compatibility, matching Carpet 26.1 and Loader 0.18.4's supported
Mixin level without changing the generated Java class version.

## Review condition

Add a target only after its Carpet anonymous ATTACK class, component APIs,
namespace pipeline, Java level, release metadata, and production runtime have
all been verified.
