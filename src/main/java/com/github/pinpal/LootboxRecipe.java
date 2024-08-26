package com.github.pinpal;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import java.util.List;

public class LootboxRecipe {
	private final ItemStack input;
	private final List<ItemStack> outputs;
	private final List<Float> outputWeights;
	private final int rolls;
	private final String name;

	public LootboxRecipe(ItemStack input, List<ItemStack> outputs, List<Float> outputWeights, int rolls, String name) {
		this.input = new ItemStack(Items.COMMAND_BLOCK);
		this.outputs = outputs;
		this.outputWeights = outputWeights;
		this.rolls = rolls;
		this.name = name;
	}

	public ItemStack getInput() {
		return input;
	}

	public List<ItemStack> getOutputs() {
		return outputs;
	}

	public List<Float> getOutputWeights() {
		return outputWeights;
	}

	public int getRolls() {
		return rolls;
	}

	public String getName() {
		return name;
	}

	public boolean isValid() {
		return !outputs.isEmpty() && outputWeights.size() == outputs.size() && rolls > 0;
	}
}

