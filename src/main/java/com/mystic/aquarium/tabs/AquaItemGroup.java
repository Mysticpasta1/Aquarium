package com.mystic.aquarium.tabs;

import com.mystic.aquarium.init.BlockInit;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class AquaItemGroup {
    public static final CreativeModeTab MAIN = new CreativeModeTab(CreativeModeTab.getGroupCountSafe(), "aquarium.general") {
        @Override
        public ItemStack makeIcon() {
            return BlockInit.SALT_WATER_AQUARIUM.get().asItem().getDefaultInstance();
        }
    };

    public static void init(){
    }
}
