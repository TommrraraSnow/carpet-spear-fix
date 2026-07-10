# Publish one artifact per Minecraft runtime contract

Status: accepted

## Context

Minecraft 1.21.11 and 26.2 execute the same piercing-attack fix, but their
binary contracts differ. The former uses Fabric intermediary remapping and
Java 21; the latter ships official Mojang names without intermediary mappings
and requires Java 25. Carpet and Fabric Loader versions also differ.

## Decision

Keep one Java and resource source set, then build two Gradle subprojects with
explicit dependency, metadata, Java release, Mixin compatibility, and remapping
settings. Publish both runtime JARs under one patch version with a Minecraft
suffix:

- `carpet-spear-fix-<version>+mc1.21.11.jar`
- `carpet-spear-fix-<version>+mc26.2.jar`

Tagged CI must validate the embedded Minecraft version, publish exactly these
two binaries, and generate `SHA256SUMS`.

## Alternatives

- A broad Minecraft dependency range would misrepresent incompatible bytecode
  and namespaces.
- Copying the source into two projects would make behavior fixes drift.
- Replacing v1.0.0 would remove a known-good, immutable rollback artifact.

## Consequences

Operators must install exactly one matching JAR. A JDK 25 build can produce both
artifacts, while the 1.21.11 target remains independently buildable on JDK 21.
The v1.0.0 release and tag remain unchanged for rollback.

## Review condition

Add a target only after its Carpet anonymous ATTACK class, component APIs,
namespace pipeline, Java level, release metadata, and production runtime have
all been verified.
