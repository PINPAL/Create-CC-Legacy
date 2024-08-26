package com.github.pinpal;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(CreateCC.MODID)
public class CreateCC {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "createcc";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    // Define Deferred Registers for blocks and items
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final RegistryObject<Block> LOOTBOX_BLOCK = BLOCKS.register("lootbox",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOD)));
    public static final RegistryObject<Item> LOOTBOX_BLOCK_ITEM = ITEMS.register("lootbox",
            () -> new BlockItem(LOOTBOX_BLOCK.get(), new Item.Properties()));

    public CreateCC() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the Deferred Register to the mod event bus so the lootbox gets registered
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

}
