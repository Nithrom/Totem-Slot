package com.cereware.totemslot;

import com.cereware.slots.api.CerewareSlotsApi;
import com.cereware.slots.api.SlotDefinition;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Items;

/** Totem Slot: hueco extra (tecla C) para tótems. Se usan al morir si no llevas uno en la mano. */
public class TotemSlot implements ModInitializer {
	public static final String MOD_ID = "totemslot";
	public static final Identifier SLOT = Identifier.fromNamespaceAndPath(MOD_ID, "totem");

	@Override
	public void onInitialize() {
		TotemSlotConfig.load();
		CerewareSlotsApi.register(new SlotDefinition(SLOT, Component.translatable("slot.totemslot.totem"),
			Items.TOTEM_OF_UNDYING, stack -> stack.has(DataComponents.DEATH_PROTECTION), 0, 30,
			() -> TotemSlotConfig.get().keepTotemsOnDeath));
	}
}
