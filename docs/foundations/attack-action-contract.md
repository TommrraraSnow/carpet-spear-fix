# Carpet-controlled attack contract

## Contract

Minecraft owns piercing-weapon attack semantics. A Carpet-controlled player
holding an item with `DataComponents.PIERCING_WEAPON` must use the vanilla
`PiercingWeapon.attack()` behavior before Carpet performs a single-target or
block ray trace.

## Source of truth

- Client intent and cooldown precheck: `Minecraft.startAttack()`
- Authoritative validation: `ServerGamePacketListenerImpl.handlePlayerAction()`
- Piercing behavior: `PiercingWeapon.attack()`
- Action scheduling: `EntityPlayerActionPack.Action`

## Supported runtime contracts

| Minecraft | Carpet | Loader | Java | Distribution namespace |
| --- | --- | --- | --- | --- |
| 1.21.11 | 1.4.194 | 0.18.2+ | 21+ | Fabric intermediary-remapped |
| 26.2 | 26.2 | 0.19.3+ | 25+ | Mojang official, no remap |

The Java and resource sources are shared. Each row has independent dependency,
metadata, bytecode, and packaging validation; the resulting JARs are not
interchangeable.

## Lifecycle

Discrete `once` and `interval` attempts may Jab after the client-equivalent
cooldown and feature checks. `continuous` represents a held attack button and
must not repeat Jab or fall through to block breaking.

## Failure and recovery

Removing the matching patch JAR restores that Carpet release's behavior without
data migration. The component call deliberately avoids the real-client load
acknowledgement, which a newly spawned fake player cannot provide.

## Consumers

Fake players, shadowed players, real players controlled through `/player`, and
farms relying on spear Jab collision all consume this contract.
