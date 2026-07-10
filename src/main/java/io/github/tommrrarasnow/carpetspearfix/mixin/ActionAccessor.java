package io.github.tommrrarasnow.carpetspearfix.mixin;

import carpet.helpers.EntityPlayerActionPack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = EntityPlayerActionPack.Action.class, remap = false)
public interface ActionAccessor {
    @Accessor(value = "isContinuous", remap = false)
    boolean carpetSpearFix$isContinuous();
}
