package net.createcobblestone.forge;

import net.createcobblestone.CreateCobblestoneMod;
import net.createcobblestone.data.GeneratorTypeLoader;
import net.createcobblestone.index.forge.CreativeTabsImpl;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Collections;
import java.util.function.Consumer;

import static net.createcobblestone.CreateCobblestoneMod.LOGGER;
import static net.createcobblestone.CreateCobblestoneMod.REGISTRATE;

@Mod(CreateCobblestoneMod.MOD_ID)
public class CreateCobblestoneForge {
    public CreateCobblestoneForge() {
        // registrate must be given the mod event bus on forge before registration
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        REGISTRATE.registerEventListeners(eventBus);
        CreativeTabsImpl.register(FMLJavaModLoadingContext.get().getModEventBus());

        MinecraftForge.EVENT_BUS.addListener((Consumer<OnDatapackSyncEvent>) event -> {

            if (event.getPlayer() != null) {
                LOGGER.info("Syncing generator types to new client");
                GeneratorTypeLoader.sendGeneratorTypesToClient(
                        Collections.singleton(event.getPlayer())
                );

            } else if (!event.getPlayerList().getPlayers().isEmpty()) {
                LOGGER.info("Syncing generator types to all clients");
                GeneratorTypeLoader.sendGeneratorTypesToClient(
                        event.getPlayerList().getPlayers()
                );

            } else {
                LOGGER.warn("Syncing generator types, but no players found");
            }
        });

        CreateCobblestoneMod.init();
    }
}
