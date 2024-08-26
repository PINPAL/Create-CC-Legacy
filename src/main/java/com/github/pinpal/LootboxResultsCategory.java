package com.github.pinpal;

import com.github.pinpal.JEIPlugin;
import com.github.pinpal.LootboxRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class LootboxResultsCategory implements IRecipeCategory<LootboxRecipe> {

	private final Component title;
	private final IDrawable background;
	private final IDrawable icon;

	private static final int columns = 7;
	private static final int rows = 3;
	private static final int slotSize = 24;
	private static final int slotPadding = 4;
	private static final int padding = 8;
	private static final int navHeight = 34;
	private static final int guiWidth = padding + columns * slotSize;
	private static final int guiHeight = navHeight + padding + rows * (slotSize + slotSize / 2);


	public LootboxResultsCategory(IGuiHelper guiHelper) {
		this.title = Component.translatable("createcc.jei.lootbox_results");
		this.background = guiHelper.createDrawable(
				new ResourceLocation("createcc", "textures/gui/lootbox_jei.png"),
				0, 0, guiWidth, guiHeight
		);
		this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(Items.COMMAND_BLOCK));

	}

	@Override
	public RecipeType<LootboxRecipe> getRecipeType() {
		return JEIPlugin.LOOTBOX_RESULTS;
	}

	@Override
	public Component getTitle() {
		return title;
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public IDrawable getIcon() {
		return icon;
	}


	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, LootboxRecipe recipe, IFocusGroup focuses) {
		// Logic to set up the layout of the recipe slots

		// Example setup:
		builder.addSlot(RecipeIngredientRole.INPUT, padding, padding)
				.addItemStack(recipe.getInput());

		int x = padding + slotPadding;
		int y = navHeight + slotPadding + padding / 2;
		for (ItemStack output : recipe.getOutputs()) {
			builder.addSlot(RecipeIngredientRole.OUTPUT, x, y)
					.addItemStack(output);
			x += slotSize;
			if (x > columns * slotSize) {
				x = padding;
				y += slotSize + slotSize / 2;
			}
		}
	}
}
