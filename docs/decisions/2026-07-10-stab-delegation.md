# Delegate spear Jab to the vanilla piercing component

Status: accepted

## Context

Carpet 1.4.194 resolves one crosshair target and calls `player.attack(entity)`.
Minecraft spear Jab is instead a piercing action evaluated before the ordinary
crosshair hit switch.

## Decision

Detect `DataComponents.PIERCING_WEAPON` before Carpet ray tracing, perform the
same zero-latency cooldown and feature checks as the client, then invoke the
same `PiercingWeapon.attack()` component used by the STAB server handler.

## Alternatives

- Calling `player.attack()` repeatedly cannot reproduce line collision or one
  attack lifecycle.
- Dispatching a synthetic STAB packet is protocol-shaped, but its client-loaded
  gate drops attacks during the first 60 ticks of a fake player's connection.
- Detecting `KINETIC_WEAPON` incorrectly selects Charge capability rather than
  Jab capability.

## Consequences

The patch stays small and inherits vanilla collision and attack behavior while
making the handler's relevant guards explicit. It intentionally targets
Carpet's anonymous ATTACK enum implementation, so compatibility is constrained
to the declared Carpet release range.

## Review condition

Retire the patch when an equivalent Fabric Carpet release is available.
