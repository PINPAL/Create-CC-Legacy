package com.github.pinpal;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(CreateCC.MODID)
public class CreateCC {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "createcc";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    public CreateCC() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register client setup
        modEventBus.addListener(this::clientSetup);

        // Register server setup
        modEventBus.addListener(this::serverSetup);

        // Register other event handlers if needed
        forgeEventBus.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        // Client-specific setup, such as registering JEI plugins
        LOGGER.info("HELLO FROM CLIENT SETUP");
        // Register JEI plugin here if not done automatically
    }

    private void serverSetup(final FMLDedicatedServerSetupEvent event) {
        // Server-specific setup
        LOGGER.info("HELLO FROM SERVER SETUP");
    }
}
