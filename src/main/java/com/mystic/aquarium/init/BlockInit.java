package com.mystic.aquarium.init;

import com.mystic.aquarium.Aquarium;
import com.mystic.aquarium.block.AquariumBlock;
import com.mystic.aquarium.block.AquariumBlockMulti;
import com.mystic.aquarium.block.AquariumBlockMultiGlass;
import com.mystic.aquarium.block.WaterType;
import com.mystic.aquarium.tabs.AquaItemGroup;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockInit {
    public static AquariumBlock SALT_WATER_AQUARIUM = registerBlock("salt_water_aquarium", new AquariumBlock(WaterType.Salt));
    public static AquariumBlock FRESH_WATER_AQUARIUM = registerBlock("fresh_water_aquarium", new AquariumBlock(WaterType.Fresh));
    public static AquariumBlockMulti SALT_WATER_AQUARIUM_MULTI = registerBlock("salt_water_aquarium_multi", new AquariumBlockMulti(WaterType.Salt, FabricBlockSettings.of(Material.GLASS)));
    public static AquariumBlockMulti FRESH_WATER_AQUARIUM_MULTI = registerBlock("fresh_water_aquarium_multi", new AquariumBlockMulti(WaterType.Fresh, FabricBlockSettings.of(Material.GLASS)));
    public static AquariumBlockMultiGlass SALT_WATER_AQUARIUM_MULTI_GLASS = registerBlock("salt_water_aquarium_multi_glass", new AquariumBlockMultiGlass(WaterType.Salt, FabricBlockSettings.of(Material.GLASS)));
    public static AquariumBlockMultiGlass FRESH_WATER_AQUARIUM_MULTI_GLASS = registerBlock("fresh_water_aquarium_multi_glass", new AquariumBlockMultiGlass(WaterType.Fresh, FabricBlockSettings.of(Material.GLASS)));

    public static void init() {

    }

    private static <T extends Block> T registerBlock(String id, T block) {
        Identifier identifier = Aquarium.id(id);
        T t = Registry.register(Registry.BLOCK, identifier, block);
        Registry.register(Registry.ITEM, identifier, new BlockItem(t, new FabricItemSettings().group(AquaItemGroup.CREATIVE_TAB_AQUARIUM)));
        return t;
    }
}
