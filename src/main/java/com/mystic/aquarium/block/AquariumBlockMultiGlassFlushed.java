package com.mystic.aquarium.block;

import net.minecraft.block.Waterloggable;

public class AquariumBlockMultiGlassFlushed extends AquariumBlockMultiGlass implements Waterloggable {
    public AquariumBlockMultiGlassFlushed(WaterType type, Settings settings) {
        super(type, settings);
    }
}
