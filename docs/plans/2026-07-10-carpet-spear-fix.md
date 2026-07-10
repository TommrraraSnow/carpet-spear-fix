# Carpet spear Jab fix plan

Status: active

## Maintenance owner

TommrraraSnow owns the temporary patch repository. Fabric Carpet maintainers
own the long-term fix after upstream acceptance.

## 当前状态

Implementation, local builds, baseline comparison, development runtime smoke,
production-JAR runtime smoke, patch publication, and upstream PR submission are
complete. The patch CI is green. Upstream CI is waiting for a Carpet maintainer
to approve the first-time fork workflow.

## 下一步

Track PR #2209 for workflow approval and maintainer review. If requested, move
the accepted change to maintained release branches and retire this patch after
an upstream release.

## P0

- Build a Minecraft 1.21.11 server-side patch for Carpet 1.4.194.
- Delegate piercing attacks to vanilla STAB with client-equivalent cooldown.
- Prove multi-target damage and normal attack regressions on a runtime server.
- Submit the equivalent source fix to Fabric Carpet master.

## P1

- Publish a reproducible JAR and validation evidence.
- Link the upstream PR to issues #2150 and #2163.

## P2

- Backport or build additional patch artifacts only if maintainers do not ship
  the fix for supported releases.

## Acceptance criteria

- Two aligned targets are both damaged by a single spear Jab.
- A collider block stops the Jab according to vanilla behavior.
- Normal weapons and block breaking retain Carpet behavior.
- `continuous` does not repeat Jab.
- Local builds and GitHub CI pass.

## Non-goals

- Reimplementing piercing ray tracing in the patch.
- Changing Charge attacks or the fake-player movement fix from PR #2176.
- Bundling Fabric Carpet into this mod.

## Execution index

- Validation: `../validation/2026-07-10-carpet-spear-fix.md`
- Publication and PR: `../execution/2026-07-10-upstream-pr.md`
