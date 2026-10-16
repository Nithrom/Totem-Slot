package com.cereware.totemslot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import net.fabricmc.loader.api.FabricLoader;

/** config/totemslot.json */
public final class TotemSlotConfig {
	/** Conservar los tótems del hueco al morir (además de la gamerule keepInventory). */
	public boolean keepTotemsOnDeath = false;

	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static TotemSlotConfig instance = new TotemSlotConfig();

	public static TotemSlotConfig get() {
		return instance;
	}

	public static void load() {
		Path file = FabricLoader.getInstance().getConfigDir().resolve(TotemSlot.MOD_ID + ".json");
		try {
			if (Files.exists(file)) {
				try (Reader r = Files.newBufferedReader(file)) {
					TotemSlotConfig read = GSON.fromJson(r, TotemSlotConfig.class);
					if (read != null) instance = read;
				}
			}
			try (Writer w = Files.newBufferedWriter(file)) {
				GSON.toJson(instance, w);
			}
		} catch (Exception ignored) {
			// valores por defecto
		}
	}
}
