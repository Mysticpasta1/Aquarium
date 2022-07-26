package com.mystic.aquarium.block;

import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.Direction;

import static net.minecraft.block.ChainBlock.WATERLOGGED;

public class AquariumBlock extends FacingBlock implements Waterloggable {
    private WaterType type;

    public AquariumBlock(WaterType type) {
        super(AbstractBlock.Settings.of(Material.GLASS).nonOpaque().requiresTool().strength(0.5f));
        setDefaultState(getDefaultState().with(WATERLOGGED, false).with(FACING, Direction.NORTH));
        this.type = type;
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing());
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder.add(WATERLOGGED).add(FACING));
    }
}
