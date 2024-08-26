package com.github.pinpal.util;

import net.minecraft.world.item.ItemStack;

import java.util.List;

public class LootboxData {
	private String name;
	private int rolls;
	private List<LootboxItem> items;

	public String getName() {
		return name;
	}

	public int getRolls() {
		return rolls;
	}

	public List<LootboxItem> getItems() {
		return items;
	}

	public static class LootboxItem {
		private String item;
		private float weight;

		public String getItem() {
			return item;
		}

		public float getWeight() {
			return weight;
		}
	}
}
