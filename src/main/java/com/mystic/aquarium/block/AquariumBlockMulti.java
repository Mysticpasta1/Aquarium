package com.mystic.aquarium.block;

import net.minecraft.block.Waterloggable;

public class AquariumBlockMulti extends AquariumBlockMultiGlass implements Waterloggable {
    public AquariumBlockMulti(WaterType type, Settings settings) {
        super(type, settings);
    }
}
