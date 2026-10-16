package com.cereware.totemslot.mixin;

import com.cereware.slots.api.CerewareSlotsApi;
import com.cereware.totemslot.TotemSlot;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.advancements.triggers.CriteriaTriggers;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DeathProtection;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Si vanilla no encontró tótem en las manos, usa uno del hueco (solo una unidad).
 * Replica el comportamiento de LivingEntity.checkTotemDeathProtection.
 */
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
	@Inject(method = "checkTotemDeathProtection", at = @At("RETURN"), cancellable = true)
	private void totemslot$useSlotTotem(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
		if (cir.getReturnValue()) return;
		if (!((Object) this instanceof ServerPlayer player)) return;
		if (source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) return;
		ItemStack slot = CerewareSlotsApi.getStack(player, TotemSlot.SLOT);
		DeathProtection protection = slot.get(DataComponents.DEATH_PROTECTION);
		if (protection == null) return;
		ItemStack used = slot.copyWithCount(1);
		ItemStack rest = slot.copy();
		rest.shrink(1);
		CerewareSlotsApi.setStack(player, TotemSlot.SLOT, rest);
		player.awardStat(Stats.ITEM_USED.get(used.getItem()));
		CriteriaTriggers.USED_TOTEM.trigger(player, used);
		used.causeUseVibration(player, GameEvent.ITEM_INTERACT_FINISH);
		player.setHealth(1.0f);
		protection.applyEffects(used, player);
		player.level().broadcastEntityEvent(player, (byte) 35);
		cir.setReturnValue(true);
	}
}
