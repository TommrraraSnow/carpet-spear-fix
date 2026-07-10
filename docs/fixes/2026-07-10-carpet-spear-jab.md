# Carpet spear Jab loses piercing targets

## Symptom

`/player <name> attack` with a spear damages only the nearest entity and can
select thin blocks instead of performing the vanilla Jab line attack.

## Impact

Fake-player spear farms diverge from real-player behavior, including the cases
reported in Fabric Carpet issues #2150 and #2163.

## Root cause

Carpet's action pack always performs its ordinary single-target ray trace.
Minecraft checks for `PIERCING_WEAPON` first and sends STAB, whose component
implementation evaluates every eligible entity along a collider-clipped line.

## Fix

Intercept only Carpet's ATTACK action for piercing weapons, preserve the client
cooldown and feature checks, and invoke the vanilla piercing component.

## Validation

See `../validation/2026-07-10-carpet-spear-fix.md`.

## Prevention

Keep special weapon actions delegated to their vanilla component or handler and
include a multi-target runtime case when upstream weapon behavior changes.
