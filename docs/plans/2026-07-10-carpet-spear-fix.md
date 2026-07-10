# Carpet spear Jab fix plan

Status: active

## Maintenance owner

TommrraraSnow owns the temporary patch repository. Fabric Carpet maintainers
own the long-term fix after upstream acceptance.

## 当前状态

The 1.21.11 baseline comparison and full runtime regression are complete.
Release v1.1.0 publishes independent 1.21.11 and 26.2 artifacts from the shared
behavior source. Exact 26.1, 26.1.1, and 26.1.2 targets now build locally with
their verified Loader, Java, namespace, Carpet, and bytecode contracts. All
three final candidates pass production-server runtime validation; v1.2.0 CI
and publication remain. Upstream PR #2209 is open, and its CI is waiting for a
Carpet maintainer to approve the first-time fork workflow.

## 下一步

Publish and re-download v1.2.0, verify its public assets and one released 26.1.x
JAR at runtime, then continue tracking PR #2209. Retire each patch artifact
after an equivalent Carpet release exists.

## P0

- Build server-side patches for Minecraft 1.21.11 / Carpet 1.4.194, all three
  released Minecraft 26.1.x versions / Carpet 26.1, and Minecraft 26.2 /
  Carpet 26.2.
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
- Release metadata prevents each JAR from loading on any other target.

## Non-goals

- Reimplementing piercing ray tracing in the patch.
- Changing Charge attacks or the fake-player movement fix from PR #2176.
- Bundling Fabric Carpet into this mod.

## Execution index

- Validation: `../validation/2026-07-10-carpet-spear-fix.md`
- Publication and PR: `../execution/2026-07-10-upstream-pr.md`
- Minecraft 26.2 release: `../execution/2026-07-10-minecraft-26-2-release.md`
- Minecraft 26.1.x release: `../execution/2026-07-10-minecraft-26-1-release.md`
- Artifact architecture: `../decisions/2026-07-10-dual-version-artifacts.md`
