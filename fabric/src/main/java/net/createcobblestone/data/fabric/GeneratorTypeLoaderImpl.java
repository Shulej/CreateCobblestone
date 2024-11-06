package net.createcobblestone.data.fabric;

import net.createcobblestone.data.GeneratorTypeLoader;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.level.ServerPlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
            GeneratorTypeLoader.sendGeneratorTypesToClient(server.getPlayerList().getPlayers());
        });
        ServerLifecycleEvents.SYNC_DATA_PACK_CONTENTS.register((player, serverResourceManager) -> {
            LOGGER.info("Server syncing data pack contents, loading generator types");
            GeneratorTypeLoader.sendGeneratorTypesToClient(Collections.singletonList(player));
        });
    }
}
