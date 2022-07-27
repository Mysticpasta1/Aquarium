package com.mystic.aquarium.tabs;

import com.mystic.aquarium.init.BlockInit;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class AquaItemGroup {
    public static final ItemGroup CREATIVE_TAB_AQUARIUM = FabricItemGroupBuilder.create(new Identifier("aquarium")).icon(() -> new ItemStack(BlockInit.FRESH_WATER_AQUARIUM)).build();
}
