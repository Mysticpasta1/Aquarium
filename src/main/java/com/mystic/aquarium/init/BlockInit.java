package com.mystic.aquarium.init;

import com.mystic.aquarium.Aquarium;
import com.mystic.aquarium.block.*;
import com.mystic.aquarium.tabs.AquaItemGroup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Properties;
import java.util.function.Function;
import java.util.function.Supplier;

public class BlockInit {

    public static DeferredRegister<Block> BLOCK = DeferredRegister.create(ForgeRegistries.BLOCKS, "aquarium");
    public static DeferredRegister<Item> ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, "aquarium");

    public static RegistryObject<Block> SALT_WATER_AQUARIUM = registerBlock("salt_water_aquarium", () -> new AquariumBlock(WaterType.Salt));
    public static RegistryObject<Block> FRESH_WATER_AQUARIUM = registerBlock("fresh_water_aquarium", () -> new AquariumBlock(WaterType.Fresh));
    public static RegistryObject<Block> SALT_WATER_AQUARIUM_MULTI = registerBlock("salt_water_aquarium_multi", () ->  new AquariumBlockMulti(WaterType.Salt, BlockBehaviour.Properties.of(Material.GLASS)));
    public static RegistryObject<Block> FRESH_WATER_AQUARIUM_MULTI = registerBlock("fresh_water_aquarium_multi", () -> new AquariumBlockMulti(WaterType.Fresh, BlockBehaviour.Properties.of(Material.GLASS)));
    public static RegistryObject<Block> FRESH_WATER_AQUARIUM_MULTI_GLASS_FLUSH = registerBlock("fresh_water_aquarium_multi_glass_flushed", () -> new AquariumBlockMultiGlassFlushed(WaterType.Fresh, BlockBehaviour.Properties.of(Material.GLASS)));
    public static RegistryObject<Block> SALT_WATER_AQUARIUM_MULTI_GLASS_FLUSH = registerBlock("salt_water_aquarium_multi_glass_flushed", () -> new AquariumBlockMultiGlassFlushed(WaterType.Salt, BlockBehaviour.Properties.of(Material.GLASS)));
    public static RegistryObject<Block> SALT_WATER_AQUARIUM_MULTI_GLASS_NONFLUSH = registerBlock("salt_water_aquarium_multi_glass", () -> new AquariumBlockMultiGlassNonFlush(WaterType.Salt, BlockBehaviour.Properties.of(Material.GLASS)));
    public static RegistryObject<Block> FRESH_WATER_AQUARIUM_MULTI_GLASS_NONFLUSH = registerBlock("fresh_water_aquarium_multi_glass", () -> new AquariumBlockMultiGlassNonFlush(WaterType.Fresh, BlockBehaviour.Properties.of(Material.GLASS)));
    public static RegistryObject<Block> SALT_WATER_AQUARIUM_MULTI_FLUSH = registerBlock("salt_water_aquarium_multi_flushed", () -> new AquariumBlockMultiFlushed(WaterType.Salt, BlockBehaviour.Properties.of(Material.GLASS)));
    public static RegistryObject<Block> FRESH_WATER_AQUARIUM_MULTI_FLUSH = registerBlock("fresh_water_aquarium_multi_flushed", () -> new AquariumBlockMultiFlushed(WaterType.Fresh, BlockBehaviour.Properties.of(Material.GLASS)));

    public static void init(IEventBus bus) {
        BLOCK.register(bus);
        ITEM.register(bus);
    }

    private static <B extends Block> RegistryObject<B> registerBlock(String name, Supplier<B> block) {
        return registerBlock(name, block, b -> () -> new BlockItem(b.get(),new Item.Properties().tab(AquaItemGroup.MAIN)));
    }

    private static <B extends Block, I extends BlockItem> RegistryObject<B> registerBlock(String name, Supplier<B> block, Function<RegistryObject<B>, Supplier<I>> item) {
        var reg = BLOCK.register(name, block);
        ITEM.register(name, () -> item.apply(reg).get());
        return reg;
    }
}
