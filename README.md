# Carpet Spear Fix

A temporary server-side Fabric patch for
[Fabric Carpet #2150](https://github.com/gnembon/fabric-carpet/issues/2150).
It restores the vanilla multi-entity Jab attack when a Carpet-controlled player
uses a spear through `/player <name> attack`.

[Download Carpet Spear Fix 1.0.0](https://github.com/TommrraraSnow/carpet-spear-fix/releases/tag/v1.0.0)

## Compatibility

- Minecraft 1.21.11
- Fabric Loader 0.18.2 or newer
- Fabric Carpet 1.4.194
- Java 21 or newer

For a dedicated server, clients do not need this mod. For single-player or LAN
worlds, install it in the instance that hosts the integrated server. Remove it
after an equivalent fix is released in Fabric Carpet.

## Install

Place the Carpet Spear Fix JAR next to Fabric Carpet in the server's `mods`
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

```bash
JAVA_HOME=/path/to/jdk-21 ./gradlew build
```

The remapped mod JAR is written to `build/libs/`.

## Upstream

The equivalent source fix is proposed in
[Fabric Carpet PR #2209](https://github.com/gnembon/fabric-carpet/pull/2209).
This repository remains narrowly scoped as an installable bridge for 1.21.11.
