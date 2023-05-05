package com.mystic.aquarium.block;

import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class AquariumBlockMultiFlushed extends AquariumBlockMultiGlass implements SimpleWaterloggedBlock {
    public AquariumBlockMultiFlushed(WaterType type, BlockBehaviour.Properties settings) {
        super(type, settings);
    }
}
