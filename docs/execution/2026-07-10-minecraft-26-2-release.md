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
6. Configured CI to build each target on its exact JDK and tagged builds to
   publish both binaries plus `SHA256SUMS`.
7. Merged patch PR #1, tagged v1.1.0, and observed release workflow run
   `29081375169` complete successfully.
8. Downloaded all public release assets, verified `SHA256SUMS` and embedded
   metadata, then repeated the 26.2 aligned-Creeper smoke with the downloaded
   JAR.

## Result

- Release: `https://github.com/TommrraraSnow/carpet-spear-fix/releases/tag/v1.1.0`
- CI: `https://github.com/TommrraraSnow/carpet-spear-fix/actions/runs/29081375169`
- 1.21.11 JAR SHA-256:
  `ee23e7dd40dc4f072da51a917049a3da6b834b7347f2a8ae9f7a07e3f4e18525`
- 26.2 JAR SHA-256:
  `20abd7f2a5ab3225d2e8bdb2c2e3103dd53df0a3c990fb1ecf97926ce2dab0e2`

The downloaded 26.2 JAR loads without Mixin errors and one
`/player SpearBot attack once` damages both aligned Creepers from `20.0` to
`16.0`. The server then stops cleanly with exit status 0.

## Rollback

Remove the 26.2 patch JAR to restore stock Carpet 26.2 behavior. The v1.0.0
release remains available for the original 1.21.11 deployment.

## Follow-up

Retain the patch while the correct upstream implementation is reconsidered,
then retire it once Carpet publishes an equivalent fix for that release line.
