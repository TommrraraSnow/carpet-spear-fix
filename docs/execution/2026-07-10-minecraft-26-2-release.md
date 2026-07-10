# Minecraft 26.2 patch release

## Environment

- Minecraft 26.2
- Fabric Carpet 26.2 (`26.2+v260616` in its embedded metadata)
- Fabric Loader 0.19.3
- Fabric server launcher 1.1.1
- Java 25.0.3

## Scope

Add a Minecraft 26.2 artifact without changing the piercing behavior source or
the immutable v1.0.0 release, and publish it together with a rebuilt 1.21.11
artifact as v1.1.0.

## Steps

1. Verified 26.2's official-name, no-remap runtime and required Java level.
2. Added explicit 1.21.11 and 26.2 Gradle targets sharing the root source set.
3. Inspected embedded dependency metadata, class-file versions, and namespaces.
4. Started an independent production-style Fabric server with the 26.2 runtime
   JAR and official Carpet release.
5. Ran the aligned two-Creeper spear Jab smoke test.
6. Configure CI to build each target on its exact JDK and tagged builds to
   publish both binaries plus `SHA256SUMS`.

## Current result

The candidate 26.2 JAR loads without Mixin errors and one
`/player SpearBot attack once` damages both aligned Creepers from `20.0` to
`16.0`. Local dual-target builds and artifact inspections pass. GitHub CI and
release publication are pending.

## Rollback

Remove the 26.2 patch JAR to restore stock Carpet 26.2 behavior. The v1.0.0
release remains available for the original 1.21.11 deployment.

## Follow-up

Record the v1.1.0 CI run, release URL, final SHA-256 values, and remote asset
inspection after publication.
