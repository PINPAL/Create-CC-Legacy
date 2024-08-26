package com.github.pinpal.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import net.minecraftforge.fml.loading.FMLPaths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.*;


public class LootboxLoader {

	private static final Logger LOGGER = LoggerFactory.getLogger(LootboxLoader.class);
	private static final Gson GSON = new Gson();
	private static final Type LOOTBOX_TYPE = new TypeToken<LootboxData>() {
	}.getType();

	public static Map<String, LootboxData> loadLootboxes() {
		Map<String, LootboxData> lootboxes = new HashMap<>();
		File lootboxDir = new File(FMLPaths.GAMEDIR.get().toFile(), "config/createcc/lootboxes");

		if (lootboxDir.exists() && lootboxDir.isDirectory()) {
			for (File file : Objects.requireNonNull(lootboxDir.listFiles())) {
				if (file.isFile() && file.getName().endsWith(".json")) {
					try {
						// Log the file content for debugging
						String content = Files.readString(file.toPath());
						LOGGER.info("Loading lootbox data from file: {}", file.getName());
						LOGGER.info("File content:\n{}", content);

						// Parse the JSON content
						LootboxData data = GSON.fromJson(content, LOOTBOX_TYPE);
						lootboxes.put(file.getName().replace(".json", ""), data);
					} catch (IOException e) {
						LOGGER.error("[Create-CC-Lootboxes] Failed to read file: {}", file.getName(), e);
					} catch (JsonSyntaxException e) {
						LOGGER.error("[Create-CC-Lootboxes] Malformed JSON in file: {}", file.getName(), e);
					}
				}
			}
		} else {
			LOGGER.warn("Lootbox directory not found: {}", lootboxDir.getAbsolutePath());
		}
		return lootboxes;
	}
}

