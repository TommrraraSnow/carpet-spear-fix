# Minecraft 26.1.x patch release

## Environment

- Minecraft 26.1, 26.1.1, and 26.1.2
- Fabric Carpet 26.1 (`26.1+v260402` in its embedded metadata)
- Fabric Loader 0.18.4
- Fabric server launcher 1.1.1
- Java 25

## Source evidence

- Mojang release enumeration:
  `https://piston-meta.mojang.com/mc/game/version_manifest_v2.json`
- Fabric's mappingless 26.1 guidance:
  `https://fabricmc.net/2026/03/14/261.html`
- Carpet 26.1 release metadata:
  `https://api.modrinth.com/v2/version/ygtmLbO3`
- Carpet 26.1 tagged source and binary:
  `https://github.com/gnembon/fabric-carpet/releases/tag/v26.1`

## Scope

Add exact artifacts for every formally released Minecraft 26.1.x version while
sharing the existing piercing behavior source and preserving the immutable
v1.0.0 and v1.1.0 releases.

## Steps

1. Enumerated formal 26.1.x releases from Mojang and Fabric metadata.
2. Verified the official Carpet 26.1 artifact, ATTACK class, and Minecraft API
   descriptors across 26.1, 26.1.1, and 26.1.2.
3. Added exact no-remap Java 25 build and CI targets for all three releases.
4. Built all five project targets and inspected their dependency metadata,
   namespace, class-file version, and core behavior bytecode.
5. Ran production-style runtime validation for every 26.1.x target, including
   full behavior matrices on the first and last patch releases.
6. Publish v1.2.0, re-download all public assets, verify `SHA256SUMS` and
   metadata, and repeat a release-JAR runtime smoke.

## Result

Local compilation, static artifact inspection, and production runtime
validation pass. Minecraft 26.1 reproduced the unpatched defect as
`16.0 / 20.0`; all three final exact artifacts produced `16.0 / 16.0` with a
Diamond Spear. The 26.1 and 26.1.2 endpoints also retained wall collision,
continuous action, and Diamond Sword behavior. Loader 0.18.4 applied the final
`JAVA_17` Mixin configs without compatibility warnings or injection errors.
GitHub CI and release publication are pending.

## Rollback

Remove the matching 26.1.x patch JAR to restore stock Carpet behavior. Releases
v1.0.0 and v1.1.0 remain unchanged.

## Follow-up

Record runtime results, CI, released hashes, and downloaded-asset verification,
then continue tracking upstream PR #2209.
