# Carpet spear fix validation

## Environment

- Date: 2026-07-10
- Minecraft: 1.21.11
- Fabric Carpet: 1.4.194
- Fabric Loader: 0.18.2 (minimum) and 0.18.4 (release-JAR smoke)
- Java: 21

## Build

Commands:

```bash
JAVA_HOME=/home/linuxbrew/.linuxbrew/opt/openjdk@21 ./gradlew clean build
sha256sum build/libs/carpet-spear-fix-1.0.0+mc1.21.11.jar
```

Actual result: `BUILD SUCCESSFUL`. The remapped JAR SHA-256 is
`9ca105635881c2177be5ee9aa564a4aaeaa6097f3c191df5f64172f1123aaaa8`.

Fabric Carpet master was also built with `./gradlew clean build` on Java 26
targeting release 25; it completed successfully with three pre-existing
deprecation warnings.

## Runtime smoke

The test used two stationary Creepers on the same view line, at `(0.5, 64,
2.5)` and `(0.5, 64, 4.0)`, with the player at `(0.5, 64, 0.5)` facing south.
Health was queried on a later server tick than the scheduled player action.

Representative commands:

```text
item replace entity SpearBot weapon.mainhand with minecraft:diamond_spear
player SpearBot attack once
data get entity @e[tag=near,limit=1] Health
data get entity @e[tag=far,limit=1] Health
```

| Case | Expected | Actual |
| --- | --- | --- |
| Carpet 1.4.194 without patch | Single target only | `12.0 / 20.0` |
| Patch, Diamond Spear | Both targets | `16.0 / 16.0` |
| Patch, pressure plate in path | Both targets | `16.0 / 16.0` |
| Patch, full stone collider in path | Neither target | `20.0 / 20.0` |
| Patch, `attack continuous` | No repeated Jab | `20.0 / 20.0` after 11 seconds |
| Patch, Diamond Sword | Single target only | `13.0 / 20.0` |
| Patch, Diamond Pickaxe continuous | Existing block breaking | Stone became air |

The published/remapped JAR was then copied into a clean Fabric server next to
the official Carpet 1.4.194 release. Fabric Loader listed both mods, applied the
Mixin without warnings, and the Diamond Spear case again produced
`16.0 / 16.0`.

The development server also started successfully with the declared minimum
Fabric Loader 0.18.2.

## Evidence

- Development server log: ignored local file `run/logs/latest.log`
- Production-JAR server log: ignored local file
  `build/runtime-server/logs/latest.log`
- Artifact: `build/libs/carpet-spear-fix-1.0.0+mc1.21.11.jar`

## Remaining risk

- Patch CI passed:
  `https://github.com/TommrraraSnow/carpet-spear-fix/actions/runs/29079261278`.
- Carpet PR CI is `action_required` with no jobs because a maintainer must
  approve workflows from this first-time fork. This is an external approval
  gate, not a failed build.
- Durability per target, lunge, sounds, knockback, and mounted-target dismount
  were delegated to the vanilla component but not separately observed here.
- Vanilla STAB does not pass through `Player.attack(Entity)`, so Scarpet's
  `player_attacks_entity` event may differ from Carpet's old incorrect
  single-target path. Recreating that event is outside this compatibility fix.
