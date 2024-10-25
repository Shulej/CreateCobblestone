package net.createcobblestone.data.fabric;

import net.createcobblestone.data.GeneratorTypeLoader;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

import static net.createcobblestone.CreateCobblestoneMod.LOGGER;

public class GeneratorTypeLoaderImpl {
    public static void init() {
        ServerLifecycleEvents.SERVER_STARTING.register(server -> {
            LOGGER.info("Server starting, loading generator types");
            GeneratorTypeLoader.loadGeneratorTypes(server.getResourceManager());
        });
        ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, serverResourceManager, success) -> {
            LOGGER.info("Server reloading, loading generator types");
            GeneratorTypeLoader.loadGeneratorTypes(serverResourceManager);
        });
    }
}
