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
6. Published v1.2.0, re-downloaded all public assets, verified `SHA256SUMS` and
   metadata, and repeated a 26.1.2 release-JAR runtime smoke.

## Result

Local compilation, static artifact inspection, and production runtime
validation pass. Minecraft 26.1 reproduced the unpatched defect as
`16.0 / 20.0`; all three final exact artifacts produced `16.0 / 16.0` with a
Diamond Spear. The 26.1 and 26.1.2 endpoints also retained wall collision,
continuous action, and Diamond Sword behavior. Loader 0.18.4 applied the final
`JAVA_17` Mixin configs without compatibility warnings or injection errors.

Tag workflow `29084644561` built and validated all five exact artifacts before
publishing:

- Release: `https://github.com/TommrraraSnow/carpet-spear-fix/releases/tag/v1.2.0`
- 1.21.11 SHA-256:
  `fd2240dd968ad347ab08fcd153b231090555d98b140c7eadba2b163823647f9d`
- 26.1 SHA-256:
  `c41856e0d8ce6dfaab21f6166c3b542df96a0f5c72a4d644d571e3fdfff73c25`
- 26.1.1 SHA-256:
  `0115428479fbee25d4f009a6b6c64f6d7bded8953d95a456e929a867d0995268`
- 26.1.2 SHA-256:
  `62b4235a85e6b39f3a70a25aaf165c92879fd7a4ffba49ed731b5c2d0b9196ee`
- 26.2 SHA-256:
  `9abbf140e91bf680bb1803ac7a1474d1d64d1342a5f7bb8e73d172ad9b0df4ef`

Freshly downloaded assets contained exactly five JARs plus `SHA256SUMS`, and
all checksums and embedded runtime contracts passed. The downloaded 26.1.2 JAR
then produced `16.0 / 16.0` and the server stopped with exit status 0.

## Rollback

Remove the matching 26.1.x patch JAR to restore stock Carpet behavior. Releases
v1.0.0 and v1.1.0 remain unchanged.

## Follow-up

Continue tracking upstream PR #2209 and retire each matching patch artifact
after Carpet publishes an equivalent release fix.
