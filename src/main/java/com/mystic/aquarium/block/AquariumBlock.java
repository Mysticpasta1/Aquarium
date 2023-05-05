package com.mystic.aquarium.block;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Material;

import static net.minecraft.world.level.block.ChainBlock.WATERLOGGED;

public class AquariumBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {
    private WaterType type;

    public AquariumBlock(WaterType type) {
        super(Properties.of(Material.GLASS).noOcclusion().requiresCorrectToolForDrops().strength(0.5f));
        this.defaultBlockState().setValue(WATERLOGGED, false).setValue(FACING, Direction.NORTH);
        this.type = type;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED).add(FACING);
    }
}
