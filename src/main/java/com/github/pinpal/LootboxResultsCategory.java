package com.github.pinpal;

import com.github.pinpal.JEIPlugin;
import com.github.pinpal.LootboxRecipe;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
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
import net.minecraft.client.Minecraft;

public class LootboxResultsCategory implements IRecipeCategory<LootboxRecipe> {

	private final Component title;
	private final IDrawable background;
	private final IDrawable icon;

	private static final int columns = 7;
	private static final int rows = 3;
	private static final int slotSize = 24;
	private static final int slotPadding = 2;
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
		// Draw input slot
		builder.addSlot(RecipeIngredientRole.INPUT, padding, padding)
				.addItemStack(recipe.getInput());

		// Draw output slots
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

	private String formatPercentage(float decimal) {
		// Format the percentage to 0 decimal places
		String percentageString = String.format("%.0f", decimal * 100) + "%";
		// Format the percentage if it's less than 1%
		if (decimal < 0.01) {
			percentageString = "<1%";
		}
		return percentageString;
	}

	@Override
	public void draw(LootboxRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX,
	                 double mouseY) {
		Minecraft minecraft = Minecraft.getInstance();
		Font font = minecraft.font;

		// Draw recipe name
		font.draw(poseStack, recipe.getName(), 30, 6, 0xFFFFFF);

		// Draw roll count
		String rollsText = "Rolls: " + recipe.getRolls();
		font.draw(poseStack, rollsText, 30, 18, 0xA8A8A8);

		// Draw output weights
		int x = padding;
		int y = navHeight + slotSize;
		for (int i = 0; i < recipe.getOutputs().size(); i++) {
			String weightText = formatPercentage(recipe.getOutputWeights().get(i));
			font.draw(poseStack, weightText, x + 2, y + 2, 0xA8A8A8);
			x += slotSize;
			if (x > columns * slotSize) {
				x = padding;
				y += slotSize + slotSize / 2;
			}
		}
	}
}
