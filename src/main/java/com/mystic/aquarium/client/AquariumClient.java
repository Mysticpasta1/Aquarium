package com.mystic.aquarium.client;

import com.mystic.aquarium.init.BlockInit;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.stream.Stream;

@Mod.EventBusSubscriber(modid = "aquarium", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AquariumClient {

    @SubscribeEvent
    public static void onInitializeClient(FMLClientSetupEvent event) {
        // This code runs on the client side.
        registerBlockRenderLayers(RenderType.cutout(),
                BlockInit.SALT_WATER_AQUARIUM.get(),
                BlockInit.FRESH_WATER_AQUARIUM.get(),
                BlockInit.FRESH_WATER_AQUARIUM_MULTI.get(),
                BlockInit.SALT_WATER_AQUARIUM_MULTI.get(),
                BlockInit.FRESH_WATER_AQUARIUM_MULTI_FLUSH.get(),
                BlockInit.SALT_WATER_AQUARIUM_MULTI_FLUSH.get(),
                BlockInit.SALT_WATER_AQUARIUM_MULTI_GLASS_FLUSH.get(),
                BlockInit.FRESH_WATER_AQUARIUM_MULTI_GLASS_FLUSH.get(),
                BlockInit.SALT_WATER_AQUARIUM_MULTI_GLASS_NONFLUSH.get(),
                BlockInit.FRESH_WATER_AQUARIUM_MULTI_GLASS_NONFLUSH.get());
    }

    private static void registerBlockRenderLayers(RenderType layer, Block... blocks) {
        Stream.of(blocks).forEach(block -> ItemBlockRenderTypes.setRenderLayer(block, layer));
    }
}
