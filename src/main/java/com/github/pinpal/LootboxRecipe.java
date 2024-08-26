package com.github.pinpal;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import java.util.List;

public class LootboxRecipe {
	private final ItemStack input;
	private final List<ItemStack> outputs;
	private final List<Float> outputChance;
	private final int rolls;
	private final String name;

	public LootboxRecipe(ItemStack input, List<ItemStack> outputs, List<Float> outputChance, int rolls, String name) {
		this.input = new ItemStack(Items.COMMAND_BLOCK);
		this.outputs = outputs;
		this.outputChance = outputChance;
		this.rolls = rolls;
		this.name = name;
	}

	public ItemStack getInput() {
		return input;
	}

	public List<ItemStack> getOutputs() {
		return outputs;
	}

	public List<Float> getOutputChance() {
		return outputChance;
	}

	public int getRolls() {
		return rolls;
	}

	public String getName() {
		return name;
	}

	public boolean isValid() {
		return !outputs.isEmpty() && outputChance.size() == outputs.size() && rolls > 0;
	}
}

