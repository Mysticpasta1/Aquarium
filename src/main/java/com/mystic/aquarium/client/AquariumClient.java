package com.mystic.aquarium.client;

import com.mystic.aquarium.init.BlockInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class AquariumClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // This code runs on the client side.
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), BlockInit.SALT_WATER_AQUARIUM, BlockInit.FRESH_WATER_AQUARIUM, BlockInit.FRESH_WATER_AQUARIUM_MULTI,
                BlockInit.SALT_WATER_AQUARIUM_MULTI, BlockInit.FRESH_WATER_AQUARIUM_MULTI_FLUSH, BlockInit.SALT_WATER_AQUARIUM_MULTI_FLUSH
                , BlockInit.SALT_WATER_AQUARIUM_MULTI_GLASS_FLUSH, BlockInit.FRESH_WATER_AQUARIUM_MULTI_GLASS_FLUSH, BlockInit.SALT_WATER_AQUARIUM_MULTI_GLASS_NONFLUSH,
                BlockInit.FRESH_WATER_AQUARIUM_MULTI_GLASS_NONFLUSH);
    }
}
