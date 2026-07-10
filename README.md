# Carpet Spear Fix

A temporary server-side Fabric patch for
[Fabric Carpet #2150](https://github.com/gnembon/fabric-carpet/issues/2150).
It restores the vanilla multi-entity Jab attack when a Carpet-controlled player
uses a spear through `/player <name> attack`.

[Download Carpet Spear Fix 1.1.0](https://github.com/TommrraraSnow/carpet-spear-fix/releases/tag/v1.1.0)

## Compatibility

| Patch JAR | Minecraft | Fabric Carpet | Fabric Loader | Java |
| --- | --- | --- | --- | --- |
| `carpet-spear-fix-1.1.0+mc1.21.11.jar` | 1.21.11 | 1.4.194 | 0.18.2 or newer | 21 or newer |
| `carpet-spear-fix-1.1.0+mc26.2.jar` | 26.2 | 26.2 | 0.19.3 or newer | 25 or newer |

For a dedicated server, clients do not need this mod. For single-player or LAN
worlds, install it in the instance that hosts the integrated server. Remove it
after an equivalent fix is released in Fabric Carpet.

## Install

Download and install only the JAR whose Minecraft version matches the server.
Do not install both JARs. Place it next to Fabric Carpet in the server's `mods`
directory, then restart the server.

## Behavior

For items with the `minecraft:piercing_weapon` data component, the patch routes
Carpet's attack action to Minecraft's vanilla `PiercingWeapon.attack()` behavior
before Carpet performs its single-target ray trace. The patch preserves the
client cooldown and feature checks; Minecraft remains responsible for range,
block collision, all entities on the attack line, knockback, dismounting,
durability, lunge behavior, sounds, and animation.

Non-piercing weapons and block breaking continue through Carpet's existing
implementation.

## Build

Build both targets with JDK 25:

```bash
JAVA_HOME=/path/to/jdk-25 ./gradlew clean build
```

The two runtime JARs are collected in `build/libs/`. A single target can also
be built with `./gradlew :mc1_21_11:build` on JDK 21 or
`./gradlew :mc26_2:build` on JDK 25.

## Upstream

The equivalent source fix is proposed in
[Fabric Carpet PR #2209](https://github.com/gnembon/fabric-carpet/pull/2209).
That PR targets Carpet's development branch and does not change the already
published Carpet 26.2 JAR. This repository remains narrowly scoped as an
installable bridge for the declared releases.
