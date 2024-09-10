package com.github.pinpal;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.content.equipment.armor.BacktankBlock;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.tterrag.registrate.util.entry.BlockEntry;


public class AllBlocks {
	private static final CreateRegistrate REGISTRATE = CreateCC.REGISTRATE;

	// Register the block for your custom backtank
	public static final BlockEntry<BacktankBlock> CUSTOM_BACKTANK =
			REGISTRATE.block("custom_backtank", BacktankBlock::new)
					.initialProperties(SharedProperties::copperMetal)
					.transform(BuilderTransformers.backtank(AllItems.CUSTOM_BACKTANK::get)) // Ensure this references the item
					// to block entity type here
					.register();

	// Method for registering all items
	public static void register() {
		// This will be called to ensure all items are registered
	}
}
