package com.mystic.aquarium.block;

import net.minecraft.block.Waterloggable;

public class AquariumBlockMultiFlushed extends AquariumBlockMultiGlass implements Waterloggable {
    public AquariumBlockMultiFlushed(WaterType type, Settings settings) {
        super(type, settings);
    }
}
