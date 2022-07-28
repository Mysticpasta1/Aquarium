package com.mystic.aquarium.block;

import net.minecraft.block.Waterloggable;

public class AquariumBlockMultiGlassNonFlush extends AquariumBlockMultiGlass implements Waterloggable {
    public AquariumBlockMultiGlassNonFlush(WaterType type, Settings settings) {
        super(type, settings);
    }
}
