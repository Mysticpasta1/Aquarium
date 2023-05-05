package com.mystic.aquarium.block;

import net.minecraft.world.level.block.SimpleWaterloggedBlock;

public class AquariumBlockMultiGlassFlushed extends AquariumBlockMultiGlass implements SimpleWaterloggedBlock {
    public AquariumBlockMultiGlassFlushed(WaterType type, Properties settings) {
        super(type, settings);
    }
}
