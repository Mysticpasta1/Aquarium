package com.mystic.aquarium.block;

import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class AquariumBlockMulti extends AquariumBlockMultiGlass implements SimpleWaterloggedBlock {
    public AquariumBlockMulti(WaterType type, BlockBehaviour.Properties settings) {
        super(type, settings);
    }
}
