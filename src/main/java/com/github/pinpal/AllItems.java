package com.github.pinpal;

import com.simibubi.create.Create;
import com.simibubi.create.content.equipment.armor.AllArmorMaterials;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.resources.ResourceLocation;
import com.tterrag.registrate.util.entry.ItemEntry;
import static com.simibubi.create.AllTags.forgeItemTag;
import com.simibubi.create.content.equipment.armor.DivingBootsItem;
import net.minecraft.world.item.ArmorMaterials;
import com.simibubi.create.content.equipment.armor.BacktankItem;
import com.simibubi.create.content.equipment.armor.BacktankItem.BacktankBlockItem;
import com.simibubi.create.AllTags.AllItemTags;

public class AllItems {

	// Reference to your CreateRegistrate instance
	private static final CreateRegistrate REGISTRATE = CreateCC.REGISTRATE;

	// Register your diving boots item
	public static final ItemEntry<? extends ArmorItem> CUSTOM_DIVING_BOOTS =
			REGISTRATE.item("custom_diving_boots",
							p -> new DivingBootsItem(ArmorMaterials.NETHERITE, p, new ResourceLocation("createcc", "custom_diving")))
					.tag(forgeItemTag("armors/boots"))
					.register();

	// Register backtank BlockItem
	// Register the placeable version of the custom backtank
	public static final ItemEntry<BacktankBlockItem> CUSTOM_BACKTANK_PLACEABLE =
			REGISTRATE.item("custom_backtank_placeable",
							p -> new BacktankBlockItem(AllBlocks.CUSTOM_BACKTANK.get(), AllItems.CUSTOM_BACKTANK::get, p))
					.model((c, p) -> p.withExistingParent(c.getName(), p.mcLoc("item/barrier")))
					.register();

	// Register the wearable custom backtank
	public static final ItemEntry<? extends BacktankItem> CUSTOM_BACKTANK =
			REGISTRATE.item("custom_backtank",
							p -> new BacktankItem(AllArmorMaterials.COPPER, p, CreateCC.asResource("custom_diving"),
									CUSTOM_BACKTANK_PLACEABLE))
					.model(AssetLookup.customGenericItemModel("_", "item"))
					.tag(AllItemTags.PRESSURIZED_AIR_SOURCES.tag)
					.tag(forgeItemTag("armors/chestplates"))
					.register();

	// Method for registering all items
	public static void register() {
		// This will be called to ensure all items are registered
	}
}
