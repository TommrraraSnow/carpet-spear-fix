package io.github.tommrrarasnow.carpetspearfix.mixin;

import carpet.helpers.EntityPlayerActionPack;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.PiercingWeapon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "carpet.helpers.EntityPlayerActionPack$ActionType$2", remap = false)
abstract class AttackActionMixin {
    @Inject(method = "execute", at = @At("HEAD"), cancellable = true, remap = false)
    private void carpetSpearFix$useVanillaPiercingAttack(
            ServerPlayer player,
            EntityPlayerActionPack.Action action,
            CallbackInfoReturnable<Boolean> cir
    ) {
        ItemStack stack = player.getMainHandItem();
        PiercingWeapon piercingWeapon = stack.get(DataComponents.PIERCING_WEAPON);
        if (piercingWeapon == null) {
            return;
        }

        boolean continuous = ((ActionAccessor) (Object) action).carpetSpearFix$isContinuous();
        if (continuous) {
            cir.setReturnValue(true);
            return;
        }

        if (!stack.isItemEnabled(player.level().enabledFeatures())
                || player.cannotAttackWithItem(stack, 0)) {
            cir.setReturnValue(false);
            return;
        }

        player.resetLastActionTime();
        piercingWeapon.attack(player, EquipmentSlot.MAINHAND);
        cir.setReturnValue(true);
    }
}
