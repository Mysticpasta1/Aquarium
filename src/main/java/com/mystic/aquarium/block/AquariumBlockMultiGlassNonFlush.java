package com.mystic.aquarium.block;

import net.minecraft.world.level.block.SimpleWaterloggedBlock;

public class AquariumBlockMultiGlassNonFlush extends AquariumBlockMultiGlass implements SimpleWaterloggedBlock {
    public AquariumBlockMultiGlassNonFlush(WaterType type, Properties settings) {
        super(type, settings);
    }
}
