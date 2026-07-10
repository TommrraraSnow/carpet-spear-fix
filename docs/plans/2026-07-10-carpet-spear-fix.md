# Carpet spear Jab fix plan

Status: active

## Maintenance owner

TommrraraSnow owns the temporary patch repository. Fabric Carpet maintainers
own the long-term fix after upstream acceptance.

## 当前状态

The 1.21.11 release, baseline comparison, full runtime regression, and upstream
PR are complete. The shared-source 26.2 target builds successfully and its
production JAR passes a dedicated-server multi-target smoke test. CI and the
v1.1.0 dual-artifact publication are the remaining release steps. Upstream CI
is waiting for a Carpet maintainer to approve the first-time fork workflow.

## 下一步

Publish v1.1.0 with separate 1.21.11 and 26.2 JARs, verify both release assets
and checksums, then track PR #2209 for workflow approval and maintainer review.
Retire each patch artifact after an equivalent Carpet release exists.

## P0

- Build server-side patches for Minecraft 1.21.11 / Carpet 1.4.194 and
  Minecraft 26.2 / Carpet 26.2.
- Delegate piercing attacks to vanilla STAB with client-equivalent cooldown.
- Prove multi-target damage on production-form runtime servers and retain the
  full 1.21.11 regression matrix.
- Submit the equivalent source fix to Fabric Carpet master.

## P1

- Publish reproducible, version-specific JARs and validation evidence.
- Link the upstream PR to issues #2150 and #2163.

## P2

- Build any additional version artifacts only if maintainers do not ship the
  fix for those releases.

## Acceptance criteria

- Two aligned targets are both damaged by a single spear Jab.
- A collider block stops the Jab according to vanilla behavior.
- Normal weapons and block breaking retain Carpet behavior.
- `continuous` does not repeat Jab.
- Local builds and GitHub CI pass.
- Release metadata prevents either JAR from loading on the other target.

## Non-goals

- Reimplementing piercing ray tracing in the patch.
- Changing Charge attacks or the fake-player movement fix from PR #2176.
- Bundling Fabric Carpet into this mod.

## Execution index

- Validation: `../validation/2026-07-10-carpet-spear-fix.md`
- Publication and PR: `../execution/2026-07-10-upstream-pr.md`
- Minecraft 26.2 release: `../execution/2026-07-10-minecraft-26-2-release.md`
- Artifact architecture: `../decisions/2026-07-10-dual-version-artifacts.md`
