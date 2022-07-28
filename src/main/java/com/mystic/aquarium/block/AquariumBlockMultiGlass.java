package com.mystic.aquarium.block;

import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import static net.minecraft.block.StairsBlock.FACING;

public class AquariumBlockMultiGlass extends ConnectingBlock implements Waterloggable {
    public static final BooleanProperty DOWN = ConnectingBlock.DOWN;
    public static final BooleanProperty NORTH = ConnectingBlock.NORTH;
    public static final BooleanProperty EAST = ConnectingBlock.EAST;
    public static final BooleanProperty SOUTH = ConnectingBlock.SOUTH;
    public static final BooleanProperty WEST = ConnectingBlock.WEST;
    public static final BooleanProperty NORTHEAST = BooleanProperty.of("northeast");
    public static final BooleanProperty SOUTHEAST = BooleanProperty.of("southeast");
    public static final BooleanProperty SOUTHWEST = BooleanProperty.of("southwest");
    public static final BooleanProperty NORTHWEST = BooleanProperty.of("northwest");
    public static final BooleanProperty WATERLOGGED = StairsBlock.WATERLOGGED;
    private WaterType type;

    public AquariumBlockMultiGlass(WaterType type, Settings settings) {
        super(0.5F, settings.nonOpaque().requiresTool().strength(0.5f));
        setDefaultState(getDefaultState().with(NORTH, false).with(EAST, false).with(SOUTH, false).with(WEST, false).with(WATERLOGGED, false).with(FACING, Direction.NORTH).with(UP, false).with(DOWN, false));
        this.type = type;
    }

    @Override
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return this.getOutlineShape(state, world, pos, context);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, WEST, SOUTH, DOWN, UP, SOUTHEAST, SOUTHWEST, NORTHEAST, NORTHWEST, WATERLOGGED, FACING);
    }

    public BlockState getPlacementState(ItemPlacementContext context) {
        World level = context.getWorld();
        BlockPos blockpos = context.getBlockPos();
        FluidState fluidstate = context.getWorld().getFluidState(context.getBlockPos());
        BlockPos belowPos = blockpos.down();
        BlockPos northPos = blockpos.north();
        BlockPos eastPos = blockpos.east();
        BlockPos southPos = blockpos.south();
        BlockPos westPos = blockpos.west();
        BlockPos northeastPos = blockpos.north().east();
        BlockPos southeastPos = blockpos.south().east();
        BlockPos southwestPos = blockpos.south().west();
        BlockPos northwestPos = blockpos.north().west();
        BlockState below = level.getBlockState(belowPos);
        BlockState north = level.getBlockState(northPos);
        BlockState east = level.getBlockState(eastPos);
        BlockState south = level.getBlockState(southPos);
        BlockState west = level.getBlockState(westPos);
        BlockState northeast = level.getBlockState(northeastPos);
        BlockState southeast = level.getBlockState(southeastPos);
        BlockState southwest = level.getBlockState(southwestPos);
        BlockState northwest = level.getBlockState(northwestPos);
        return getDefaultState()
                .with(DOWN, !(below.getBlock() instanceof AquariumBlockMultiGlass))
                .with(NORTH, !(north.getBlock() instanceof AquariumBlockMultiGlass))
                .with(EAST, !(east.getBlock() instanceof AquariumBlockMultiGlass))
                .with(SOUTH, !(south.getBlock() instanceof AquariumBlockMultiGlass))
                .with(WEST, !(west.getBlock() instanceof AquariumBlockMultiGlass))
                .with(NORTHEAST, (!(north.getBlock() instanceof AquariumBlockMultiGlass) || !(northeast.getBlock() instanceof AquariumBlockMultiGlass) || !(east.getBlock() instanceof AquariumBlockMultiGlass)))
                .with(SOUTHEAST, (!(south.getBlock() instanceof AquariumBlockMultiGlass) || !(southeast.getBlock() instanceof AquariumBlockMultiGlass) || !(east.getBlock() instanceof AquariumBlockMultiGlass)))
                .with(SOUTHWEST, (!(south.getBlock() instanceof AquariumBlockMultiGlass) || !(southwest.getBlock() instanceof AquariumBlockMultiGlass) || !(west.getBlock() instanceof AquariumBlockMultiGlass)))
                .with(NORTHWEST, (!(north.getBlock() instanceof AquariumBlockMultiGlass) || !(northwest.getBlock() instanceof AquariumBlockMultiGlass) || !(west.getBlock() instanceof AquariumBlockMultiGlass)))
                .with(WATERLOGGED, (fluidstate.getFluid() == Fluids.WATER));
    }

    @Override
    public void onBlockAdded(BlockState blockstate, World level, BlockPos blockpos, BlockState oldstate, boolean p_60570_) {
        level.createAndScheduleBlockTick(blockpos, blockstate.getBlock(), 1);
    }

    @Override
    public void scheduledTick(BlockState blockstate, ServerWorld level, BlockPos blockpos, Random random) {
        BlockPos northPos = blockpos.north();
        BlockPos eastPos = blockpos.east();
        BlockPos southPos = blockpos.south();
        BlockPos westPos = blockpos.west();
        BlockPos northeastPos = blockpos.north().east();
        BlockPos southeastPos = blockpos.south().east();
        BlockPos southwestPos = blockpos.south().west();
        BlockPos northwestPos = blockpos.north().west();
        BlockState north = level.getBlockState(northPos);
        BlockState east = level.getBlockState(eastPos);
        BlockState south = level.getBlockState(southPos);
        BlockState west = level.getBlockState(westPos);
        BlockState northeast = level.getBlockState(northeastPos);
        BlockState southeast = level.getBlockState(southeastPos);
        BlockState southwest = level.getBlockState(southwestPos);
        BlockState northwest = level.getBlockState(northwestPos);
        (blockstate
                .with(NORTHEAST, (!(north.getBlock() instanceof AquariumBlockMultiGlass) || !(northeast.getBlock() instanceof AquariumBlockMultiGlass) || !(east.getBlock() instanceof AquariumBlockMultiGlass))))
                .with(SOUTHEAST, (!(south.getBlock() instanceof AquariumBlockMultiGlass) || !(southeast.getBlock() instanceof AquariumBlockMultiGlass) || !(east.getBlock() instanceof AquariumBlockMultiGlass)))
                .with(SOUTHWEST, (!(south.getBlock() instanceof AquariumBlockMultiGlass) || !(southwest.getBlock() instanceof AquariumBlockMultiGlass) || !(west.getBlock() instanceof AquariumBlockMultiGlass)))
                .with(NORTHWEST, (!(north.getBlock() instanceof AquariumBlockMultiGlass) || !(northwest.getBlock() instanceof AquariumBlockMultiGlass) || !(west.getBlock() instanceof AquariumBlockMultiGlass)));
        level.createAndScheduleBlockTick(blockpos, blockstate.getBlock(), 1);
    }

    @Override
    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return !(Boolean) state.get(WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState blockstate, Direction direction, BlockState updatingstate, WorldAccess level, BlockPos blockpos, BlockPos updatingpos) {
        if (blockstate.get(WATERLOGGED))
            level.createAndScheduleFluidTick(blockpos, Fluids.WATER, Fluids.WATER.getTickRate(level));
        BlockPos belowPos = blockpos.down();
        BlockPos northPos = blockpos.north();
        BlockPos eastPos = blockpos.east();
        BlockPos southPos = blockpos.south();
        BlockPos westPos = blockpos.west();
        BlockPos northeastPos = blockpos.north().east();
        BlockPos southeastPos = blockpos.south().east();
        BlockPos southwestPos = blockpos.south().west();
        BlockPos northwestPos = blockpos.north().west();
        BlockState below = level.getBlockState(belowPos);
        BlockState north = level.getBlockState(northPos);
        BlockState east = level.getBlockState(eastPos);
        BlockState south = level.getBlockState(southPos);
        BlockState west = level.getBlockState(westPos);
        BlockState northeast = level.getBlockState(northeastPos);
        BlockState southeast = level.getBlockState(southeastPos);
        BlockState southwest = level.getBlockState(southwestPos);
        BlockState northwest = level.getBlockState(northwestPos);
        level.createAndScheduleBlockTick(blockpos, this, 1);
        return blockstate
                .with(DOWN, !(below.getBlock() instanceof AquariumBlockMultiGlass))
                .with(NORTH, !(north.getBlock() instanceof AquariumBlockMultiGlass))
                .with(EAST, !(east.getBlock() instanceof AquariumBlockMultiGlass))
                .with(SOUTH, !(south.getBlock() instanceof AquariumBlockMultiGlass))
                .with(WEST, !(west.getBlock() instanceof AquariumBlockMultiGlass))
                .with(NORTHEAST, (!(north.getBlock() instanceof AquariumBlockMultiGlass) || !(northeast.getBlock() instanceof AquariumBlockMultiGlass) || !(east.getBlock() instanceof AquariumBlockMultiGlass)))
                .with(SOUTHEAST, (!(south.getBlock() instanceof AquariumBlockMultiGlass) || !(southeast.getBlock() instanceof AquariumBlockMultiGlass) || !(east.getBlock() instanceof AquariumBlockMultiGlass)))
                .with(SOUTHWEST, (!(south.getBlock() instanceof AquariumBlockMultiGlass) || !(southwest.getBlock() instanceof AquariumBlockMultiGlass) || !(west.getBlock() instanceof AquariumBlockMultiGlass)))
                .with(NORTHWEST, (!(north.getBlock() instanceof AquariumBlockMultiGlass) || !(northwest.getBlock() instanceof AquariumBlockMultiGlass) || !(west.getBlock() instanceof AquariumBlockMultiGlass)))
                .with(WATERLOGGED, (level.getBlockState(blockpos.up()).getBlock() instanceof AquariumBlockMultiGlass) ? level.getBlockState(blockpos.up()).get(WATERLOGGED) : blockstate.get(WATERLOGGED));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView level, BlockPos pos, ShapeContext context) {
        VoxelShape shape = VoxelShapes.empty();
        if (state.get(DOWN))
            shape = VoxelShapes.union(shape, createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D));
        if (state.get(NORTH))
            shape = VoxelShapes.union(shape, createCuboidShape(1.0D, 0.0D, 0.0D, 15.0D, 16.0D, 1.0D));
        if (state.get(EAST))
            shape = VoxelShapes.union(shape, createCuboidShape(15.0D, 0.0D, 1.0D, 16.0D, 16.0D, 15.0D));
        if (state.get(SOUTH))
            shape = VoxelShapes.union(shape, createCuboidShape(1.0D, 0.0D, 15.0D, 15.0D, 16.0D, 16.0D));
        if (state.get(WEST))
            shape = VoxelShapes.union(shape, createCuboidShape(0.0D, 0.0D, 1.0D, 1.0D, 16.0D, 15.0D));
        if (state.get(NORTHEAST))
            shape = VoxelShapes.union(shape, createCuboidShape(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D));
        if (state.get(SOUTHEAST))
            shape = VoxelShapes.union(shape, createCuboidShape(15.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D));
        if (state.get(SOUTHWEST))
            shape = VoxelShapes.union(shape, createCuboidShape(0.0D, 0.0D, 15.0D, 1.0D, 16.0D, 16.0D));
        if (state.get(NORTHWEST))
            shape = VoxelShapes.union(shape, createCuboidShape(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 1.0D));
        if (shape == VoxelShapes.empty())
            shape = createCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
        return shape;
    }

    public VoxelShape getCollisionShape(BlockState state, BlockView level, BlockPos pos, ShapeContext context) {
        VoxelShape shape = VoxelShapes.empty();
        if (state.get(DOWN))
            shape = VoxelShapes.union(shape, createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D));
        if (state.get(NORTH))
            shape = VoxelShapes.union(shape, createCuboidShape(1.0D, 0.0D, 0.0D, 15.0D, 16.0D, 1.0D));
        if (state.get(EAST))
            shape = VoxelShapes.union(shape, createCuboidShape(15.0D, 0.0D, 1.0D, 16.0D, 16.0D, 15.0D));
        if (state.get(SOUTH))
            shape = VoxelShapes.union(shape, createCuboidShape(1.0D, 0.0D, 15.0D, 15.0D, 16.0D, 16.0D));
        if (state.get(WEST))
            shape = VoxelShapes.union(shape, createCuboidShape(0.0D, 0.0D, 1.0D, 1.0D, 16.0D, 15.0D));
        if (state.get(NORTHEAST))
            shape = VoxelShapes.union(shape, createCuboidShape(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D));
        if (state.get(SOUTHEAST))
            shape = VoxelShapes.union(shape, createCuboidShape(15.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D));
        if (state.get(SOUTHWEST))
            shape = VoxelShapes.union(shape, createCuboidShape(0.0D, 0.0D, 15.0D, 1.0D, 16.0D, 16.0D));
        if (state.get(NORTHWEST))
            shape = VoxelShapes.union(shape, createCuboidShape(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 1.0D));
        return shape;
    }

    @Override
    public VoxelShape getRaycastShape(BlockState state, BlockView blockView, BlockPos blockPos) {
        return VoxelShapes.fullCube();
    }
}
