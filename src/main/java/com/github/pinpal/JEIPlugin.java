package com.github.pinpal;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		LOGGER.info("Registering Lootbox categories with JEI");
		registration.addRecipeCategories(new LootboxResultsCategory(registration.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		LOGGER.info("Registering Lootbox recipes with JEI");

		// Create test recipes
		List<LootboxRecipe> recipes = new ArrayList<>();
		List<ItemStack> outputs = Arrays.asList(
				new ItemStack(Items.DIAMOND, 1),
				new ItemStack(Items.EMERALD, 1),
				new ItemStack(Items.GOLD_INGOT, 1)
		);
		List<Float> outputWeights = Arrays.asList(0.5f, 0.3f, 0.2f);

		// Add a test recipe
		recipes.add(new LootboxRecipe(
				new ItemStack(Items.COMMAND_BLOCK),  // Input item
				outputs,
				outputWeights,
				3,  // Number of rolls
				"Test Lootbox"
		));

		// Register the recipes with JEI
		registration.addRecipes(LOOTBOX_RESULTS, recipes);
	}
}
