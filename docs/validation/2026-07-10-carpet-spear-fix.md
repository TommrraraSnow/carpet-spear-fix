# Carpet spear fix validation

## Environment

Date: 2026-07-10

| Target | Carpet | Loader | Java used | Expected class major |
| --- | --- | --- | --- | --- |
| Minecraft 1.21.11 | 1.4.194 | 0.18.2 | 21.0.8 | 65 |
| Minecraft 26.2 | 26.2+v260616 | 0.19.3 | 25.0.3 | 69 |

Commands:

```bash
JAVA_HOME=/home/linuxbrew/.linuxbrew/opt/openjdk@25 ./gradlew clean build --warning-mode all
JAVA_HOME=/home/linuxbrew/.linuxbrew/opt/openjdk@21 ./gradlew :mc1_21_11:clean :mc1_21_11:build
JAVA_HOME=/home/linuxbrew/.linuxbrew/opt/openjdk@25 ./gradlew :mc26_2:clean :mc26_2:build
sha256sum build/libs/*.jar
```

All three builds completed with `BUILD SUCCESSFUL`. The local release
candidates are:

| Artifact | Local SHA-256 |
| --- | --- |
| `carpet-spear-fix-1.1.0+mc1.21.11.jar` | `bc8d5b377059e71b1aa83e0bac4401d3d54cb1b741e5efb013d44e214e48c096` |
| `carpet-spear-fix-1.1.0+mc26.2.jar` | `0dc8f138a96393922b2510d2105ec5be00e029db3fae5ef85daf96abfc48ac73` |

The 1.21.11 JAR contains intermediary-remapped Minecraft references,
`JAVA_21` Mixin metadata, and class major 65. The 26.2 JAR retains the official
Mojang runtime names, contains `JAVA_25` Mixin metadata, and has class major 69.
Both embedded dependency manifests match their declared Carpet, Loader, Java,
and exact Minecraft target.

The immutable v1.0.0 JAR remains
`9ca105635881c2177be5ee9aa564a4aaeaa6097f3c191df5f64172f1123aaaa8`.

Fabric Carpet master was also built with `./gradlew clean build` on Java 26
targeting release 25; it completed successfully with three pre-existing
deprecation warnings.

## Minecraft 1.21.11 runtime regression

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

The published/remapped v1.0.0 JAR was also copied into a clean Fabric server
next to the official Carpet 1.4.194 release. Fabric Loader listed both mods,
applied the Mixin without warnings, and the Diamond Spear case again produced
`16.0 / 16.0`. The development server started successfully with the declared
minimum Fabric Loader 0.18.2.

## Minecraft 26.2 production-JAR runtime

An independent Fabric server launcher 1.1.1 instance loaded the current root
build's `carpet-spear-fix-1.1.0+mc26.2.jar`, official Carpet 26.2, Loader
0.19.3, and Java 25.0.3. Loader enumerated the expected versions, Mixin selected
`JAVA_25`, and there were no injection errors.

The aligned-Creeper test ran at y=100 with the same two- and 3.5-block target
distances used above.

| Case | Expected | Actual |
| --- | --- | --- |
| Diamond Spear | Both targets | `16.0 / 16.0` |
| Three-block-high stone wall | Neither target | `20.0 / 20.0` |
| `attack continuous` for 5 seconds | No repeated Jab | `20.0 / 20.0` |
| Diamond Sword | Single target only | `13.0 / 20.0` |

The server was stopped through its console and exited with status 0. Pressure
plate passthrough and pickaxe block breaking were not repeated on 26.2; those
remain covered by the full 1.21.11 matrix and the shared behavior source.

## Evidence

- 1.21.11 development log: ignored local file `run/logs/latest.log`
- 1.21.11 production log: ignored local file
  `build/runtime-server/logs/latest.log`
- 26.2 production log: ignored local file
  `/tmp/carpet-26.2-validation/standalone/logs/latest.log`
- Local artifacts: `build/libs/`

## Remote verification

- v1.0.0 patch CI passed:
  `https://github.com/TommrraraSnow/carpet-spear-fix/actions/runs/29079261278`.
- v1.1.0 Java 21/25 matrix builds, artifact metadata checks, and release job
  passed in run
  `https://github.com/TommrraraSnow/carpet-spear-fix/actions/runs/29081375169`.
- The public release contains exactly two JARs and `SHA256SUMS`:
  `https://github.com/TommrraraSnow/carpet-spear-fix/releases/tag/v1.1.0`.
- Freshly downloaded assets passed `sha256sum --check SHA256SUMS`. Released JAR
  SHA-256 values are
  `ee23e7dd40dc4f072da51a917049a3da6b834b7347f2a8ae9f7a07e3f4e18525`
  for 1.21.11 and
  `20abd7f2a5ab3225d2e8bdb2c2e3103dd53df0a3c990fb1ecf97926ce2dab0e2`
  for 26.2.
- The downloaded 26.2 release JAR was substituted into the independent server;
  Loader and Mixin initialized cleanly, the spear case produced `16.0 / 16.0`,
  and the server exited normally.
- Carpet PR CI is `action_required` with no jobs because a maintainer must
  approve workflows from this first-time fork. This is an external approval
  gate, not a failed build.

## Remaining risk

- Durability per target, lunge, sounds, knockback, and mounted-target dismount
  are delegated to the vanilla component but were not separately observed.
- Vanilla STAB does not pass through `Player.attack(Entity)`, so Scarpet's
  `player_attacks_entity` event may differ from Carpet's old incorrect
  single-target path. Recreating that event is outside this compatibility fix.
- The Mixin targets Carpet's anonymous `$ActionType$2`; each additional Carpet
  release must be separately compiled, inspected, and runtime-tested.
