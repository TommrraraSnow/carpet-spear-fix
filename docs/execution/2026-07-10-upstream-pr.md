# Patch publication and upstream PR

## Environment

GitHub repositories under `TommrraraSnow`, targeting `gnembon/fabric-carpet`
master.

## Scope

Publish the Minecraft 1.21.11 patch mod and submit the equivalent minimal Carpet
source change.

## Steps

1. Build and runtime-test the 1.21.11 patch against Carpet 1.4.194.
2. Create `TommrraraSnow/carpet-spear-fix` and publish release `v1.0.0`.
3. Fork `gnembon/fabric-carpet` and push
   `fix/piercing-weapon-player-attack`.
4. Open a pull request against Carpet master linking issues #2150 and #2163.
5. Verify both repositories' GitHub Actions results.

## Result

Local implementation and runtime validation are complete. Repository, release,
pull request, and CI fields will be filled after publication.

## Rollback

Delete the patch release or close the PR; local source branches remain intact.

## Follow-up

Track maintainer feedback and retire the patch after an upstream release.
