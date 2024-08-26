package com.github.pinpal;

import com.github.pinpal.util.LootboxData;
import com.github.pinpal.util.LootboxLoader;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@JeiPlugin
public class JEIPlugin implements IModPlugin {

	private static final Logger LOGGER = LoggerFactory.getLogger("createcc");

	private static final ResourceLocation PLUGIN_UID = new ResourceLocation("createcc", "jei_plugin");

	public static final RecipeType<LootboxRecipe> LOOTBOX_RESULTS = new RecipeType<>(
			new ResourceLocation("createcc", "lootbox_results"), LootboxRecipe.class
	);

	@Override
	public ResourceLocation getPluginUid() {
		return PLUGIN_UID;
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		LOGGER.info("Registering Lootbox Categories with JEI");
		registration.addRecipeCategories(new LootboxResultsCategory(registration.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		LOGGER.info("Registering Lootboxes recipes with JEI");

		// Load lootboxes from JSON
		Map<String, LootboxData> lootboxes = LootboxLoader.loadLootboxes();

		// Create a recipe for each lootbox
		List<LootboxRecipe> recipes = new ArrayList<>();
		for (Map.Entry<String, LootboxData> entry : lootboxes.entrySet()) {
			LOGGER.info("Registering lootbox recipe: " + entry.getKey());
			LootboxData lootbox = entry.getValue();

			List<ItemStack> outputs = new ArrayList<>();
			List<Float> outputWeights = new ArrayList<>();

			for (LootboxData.LootboxItem item : lootbox.getItems()) {
				ItemStack itemStack = new ItemStack(Registry.ITEM.get(new ResourceLocation(item.getItem())));
				outputs.add(itemStack);
				outputWeights.add(item.getWeight());
			}

			recipes.add(new LootboxRecipe(
					new ItemStack(Items.COMMAND_BLOCK),  // Example input item
					outputs,
					outputWeights,
					lootbox.getRolls(),
					lootbox.getName()
			));
		}

		// Register the recipes with JEI
		registration.addRecipes(LOOTBOX_RESULTS, recipes);
	}
}
