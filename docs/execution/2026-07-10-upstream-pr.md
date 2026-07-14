# Patch publication and upstream PR

## Environment

GitHub repositories under `TommrraraSnow`, targeting `gnembon/fabric-carpet`
master.

## Scope

Publish the Minecraft 1.21.11 patch mod and submit the equivalent minimal Carpet
source change.

## Steps

1. Built and runtime-tested the 1.21.11 patch against Carpet 1.4.194.
2. Created `TommrraraSnow/carpet-spear-fix` and published release `v1.0.0`.
3. Forked `gnembon/fabric-carpet` and pushed
   `fix/piercing-weapon-player-attack`.
4. Opened pull request #2209 against Carpet master, linking #2150 and #2163.
5. Verified patch CI and inspected the upstream workflow gate.
6. Closed PR #2209 on 2026-07-14 after determining that the submitted approach
   was not a correct upstream fix, and left that reason on the PR.

## Result

- Patch repository:
  `https://github.com/TommrraraSnow/carpet-spear-fix`
- Release:
  `https://github.com/TommrraraSnow/carpet-spear-fix/releases/tag/v1.0.0`
- Release JAR SHA-256:
  `9ca105635881c2177be5ee9aa564a4aaeaa6097f3c191df5f64172f1123aaaa8`
- Patch CI: success, run `29079261278`
- Upstream PR: closed,
  `https://github.com/gnembon/fabric-carpet/pull/2209`
- Closure comment:
  `https://github.com/gnembon/fabric-carpet/pull/2209#issuecomment-4966411047`

## Rollback

The upstream PR is closed. Patch releases remain available and can still be
removed independently without data migration.

## Follow-up

Reconsider the upstream implementation before opening a replacement PR. Retire
the patch after a correct upstream release exists.
