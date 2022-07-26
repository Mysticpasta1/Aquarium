package com.mystic.aquarium.block;

import com.mystic.aquarium.Aquarium;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockInit {
    public static AquariumBlock SALT_WATER_AQUARIUM = registerBlock("salt_water_aquarium", new AquariumBlock(WaterType.Salt));
    public static AquariumBlock FRESH_WATER_AQUARIUM = registerBlock("fresh_water_aquarium", new AquariumBlock(WaterType.Fresh));

    public static void init() {

    }

    private static <T extends Block> T registerBlock(String id, T block) {
        Identifier identifier = Aquarium.id(id);
        T t = Registry.register(Registry.BLOCK, identifier, block);
        Registry.register(Registry.ITEM, identifier, new BlockItem(t, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        return t;
    }
}
