package com.github.pinpal;

import com.simibubi.create.content.equipment.armor.BacktankBlockEntity;
import com.simibubi.create.content.equipment.armor.BacktankInstance;
import com.simibubi.create.content.equipment.armor.BacktankRenderer;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

public class AllBlockEntityTypes {
	private static final CreateRegistrate REGISTRATE = CreateCC.REGISTRATE;
	public static final BlockEntityEntry<BacktankBlockEntity> CUSTOM_BACKTANK = REGISTRATE
			.blockEntity("custom_backtank", BacktankBlockEntity::new)
			.instance(() -> BacktankInstance::new)
			.validBlocks(AllBlocks.CUSTOM_BACKTANK)
			.renderer(() -> BacktankRenderer::new)
			.register();

	public static void register() {}
}
