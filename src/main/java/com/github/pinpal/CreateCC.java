package com.github.pinpal;

import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraft.resources.ResourceLocation;
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
    public static final String MODID = "createcc";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    // Create a CreateRegistrate instance for your mod
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MODID);

    // Define Deferred Registers for blocks and items
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final RegistryObject<Block> LOOTBOX_BLOCK = BLOCKS.register("lootbox",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOD)));
    public static final RegistryObject<Item> LOOTBOX_BLOCK_ITEM = ITEMS.register("lootbox",
            () -> new BlockItem(LOOTBOX_BLOCK.get(), new Item.Properties()));

    public CreateCC() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register your blocks and items
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);

        // Register custom items and blocks
        AllItems.register();
        AllBlocks.register();
        AllBlockEntityTypes.register();

        // Register event listeners for CreateRegistrate
        REGISTRATE.registerEventListeners(modEventBus);

        // Register ourselves for server and other game events
        MinecraftForge.EVENT_BUS.register(this);
    }

    // Utility
    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(CreateCC.MODID, path);
    }

}
