package com.mystic.aquarium.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.AbstractGlassBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.world.level.block.SculkSensorBlock.WATERLOGGED;

public class AquariumBlockMultiGlass extends AbstractGlassBlock implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final BooleanProperty UP = BlockStateProperties.UP;
    public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final BooleanProperty NORTHEAST = BooleanProperty.create("northeast");
    public static final BooleanProperty SOUTHEAST = BooleanProperty.create("southeast");
    public static final BooleanProperty SOUTHWEST = BooleanProperty.create("southwest");
    public static final BooleanProperty NORTHWEST = BooleanProperty.create("northwest");
    WaterType type;
    public static final EnumProperty<WaterType> TYPE = EnumProperty.create("type", WaterType.class);

    public AquariumBlockMultiGlass(WaterType type, Properties settings) {
        super(settings.noOcclusion().requiresCorrectToolForDrops().strength(0.5f));
        this.defaultBlockState().setValue(NORTH, false).setValue(EAST, false).setValue(SOUTH, false).setValue(WEST, false).setValue(WATERLOGGED, false).setValue(UP, false).setValue(DOWN, false).setValue(NORTHEAST, false).setValue(SOUTHEAST, false).setValue(SOUTHWEST, false).setValue(NORTHWEST, false).setValue(TYPE, type).setValue(FACING, Direction.NORTH);
        this.type = type;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, WEST, SOUTH, DOWN, UP, SOUTHEAST, SOUTHWEST, NORTHEAST, NORTHWEST, WATERLOGGED, TYPE, FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        BlockPos belowPos = blockpos.below();
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
        return defaultBlockState()
                .setValue(UP, this.defaultBlockState().getValue(UP))
                .setValue(DOWN, !(below.getBlock() instanceof AquariumBlockMultiGlass))
                .setValue(NORTH, !(north.getBlock() instanceof AquariumBlockMultiGlass))
                .setValue(EAST, !(east.getBlock() instanceof AquariumBlockMultiGlass))
                .setValue(SOUTH, !(south.getBlock() instanceof AquariumBlockMultiGlass))
                .setValue(WEST, !(west.getBlock() instanceof AquariumBlockMultiGlass))
                .setValue(TYPE, type)
                .setValue(NORTHEAST, (!(north.getBlock() instanceof AquariumBlockMultiGlass) || !(northeast.getBlock() instanceof AquariumBlockMultiGlass) || !(east.getBlock() instanceof AquariumBlockMultiGlass)))
                .setValue(SOUTHEAST, (!(south.getBlock() instanceof AquariumBlockMultiGlass) || !(southeast.getBlock() instanceof AquariumBlockMultiGlass) || !(east.getBlock() instanceof AquariumBlockMultiGlass)))
                .setValue(SOUTHWEST, (!(south.getBlock() instanceof AquariumBlockMultiGlass) || !(southwest.getBlock() instanceof AquariumBlockMultiGlass) || !(west.getBlock() instanceof AquariumBlockMultiGlass)))
                .setValue(NORTHWEST, (!(north.getBlock() instanceof AquariumBlockMultiGlass) || !(northwest.getBlock() instanceof AquariumBlockMultiGlass) || !(west.getBlock() instanceof AquariumBlockMultiGlass)))
                .setValue(WATERLOGGED, (fluidstate.is(Fluids.WATER)));
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        level.scheduleTick(pos, state.getBlock(), 1);
    }

    @Override
    public void tick(BlockState blockstate, ServerLevel level, BlockPos blockpos, RandomSource random) {
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
                .setValue(TYPE, type)
                .setValue(NORTHEAST, (!(north.getBlock() instanceof AquariumBlockMultiGlass) || !(northeast.getBlock() instanceof AquariumBlockMultiGlass) || !(east.getBlock() instanceof AquariumBlockMultiGlass))))
                .setValue(SOUTHEAST, (!(south.getBlock() instanceof AquariumBlockMultiGlass) || !(southeast.getBlock() instanceof AquariumBlockMultiGlass) || !(east.getBlock() instanceof AquariumBlockMultiGlass)))
                .setValue(SOUTHWEST, (!(south.getBlock() instanceof AquariumBlockMultiGlass) || !(southwest.getBlock() instanceof AquariumBlockMultiGlass) || !(west.getBlock() instanceof AquariumBlockMultiGlass)))
                .setValue(NORTHWEST, (!(north.getBlock() instanceof AquariumBlockMultiGlass) || !(northwest.getBlock() instanceof AquariumBlockMultiGlass) || !(west.getBlock() instanceof AquariumBlockMultiGlass)));
        level.scheduleTick(blockpos, blockstate.getBlock(), 1);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter pReader, BlockPos pPos) {
        return !(Boolean) state.getValue(WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }


    @Override
    public BlockState updateShape(BlockState blockstate, Direction direction, BlockState updatingstate, LevelAccessor level, BlockPos blockpos, BlockPos updatepos) {
        if (blockstate.getValue(WATERLOGGED))
            level.scheduleTick(blockpos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        BlockPos belowPos = blockpos.below();
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
        level.scheduleTick(blockpos, this, 1);
        return blockstate.
                setValue(UP, blockstate.getValue(UP)).
                setValue(DOWN, !(below.getBlock() instanceof AquariumBlockMultiGlass) || below.getBlock() instanceof AquariumBlockMultiGlass && below.getValue(UP)).
                setValue(NORTH, !(north.getBlock() instanceof AquariumBlockMultiGlass)).
                setValue(EAST, !(east.getBlock() instanceof AquariumBlockMultiGlass)).
                setValue(SOUTH, !(south.getBlock() instanceof AquariumBlockMultiGlass)).
                setValue(WEST, !(west.getBlock() instanceof AquariumBlockMultiGlass)).
                setValue(NORTHEAST, !(north.getBlock() instanceof AquariumBlockMultiGlass) || !(northeast.getBlock() instanceof AquariumBlockMultiGlass) || !(east.getBlock() instanceof AquariumBlockMultiGlass)).
                setValue(SOUTHEAST, !(south.getBlock() instanceof AquariumBlockMultiGlass) || !(southeast.getBlock() instanceof AquariumBlockMultiGlass) || !(east.getBlock() instanceof AquariumBlockMultiGlass)).
                setValue(SOUTHWEST, !(south.getBlock() instanceof AquariumBlockMultiGlass) || !(southwest.getBlock() instanceof AquariumBlockMultiGlass) || !(west.getBlock() instanceof AquariumBlockMultiGlass)).
                setValue(NORTHWEST, !(north.getBlock() instanceof AquariumBlockMultiGlass) || !(northwest.getBlock() instanceof AquariumBlockMultiGlass) || !(west.getBlock() instanceof AquariumBlockMultiGlass)).
                setValue(WATERLOGGED, level.getBlockState(blockpos.above()).getBlock() instanceof AquariumBlockMultiGlass ? level.getBlockState(blockpos.above()).getValue(WATERLOGGED) || blockstate.getValue(WATERLOGGED) : blockstate.getValue(WATERLOGGED));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter pLevel, BlockPos pPos, CollisionContext context) {
        VoxelShape shape = Shapes.empty();
        if (state.getValue(DOWN))
            shape = Shapes.or(shape, box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D));
        if (state.getValue(NORTH))
            shape = Shapes.or(shape, box(1.0D, 0.0D, 0.0D, 15.0D, 16.0D, 1.0D));
        if (state.getValue(EAST))
            shape = Shapes.or(shape, box(15.0D, 0.0D, 1.0D, 16.0D, 16.0D, 15.0D));
        if (state.getValue(SOUTH))
            shape = Shapes.or(shape, box(1.0D, 0.0D, 15.0D, 15.0D, 16.0D, 16.0D));
        if (state.getValue(WEST))
            shape = Shapes.or(shape, box(0.0D, 0.0D, 1.0D, 1.0D, 16.0D, 15.0D));
        if (state.getValue(NORTHEAST))
            shape = Shapes.or(shape, box(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D));
        if (state.getValue(SOUTHEAST))
            shape = Shapes.or(shape, box(15.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D));
        if (state.getValue(SOUTHWEST))
            shape = Shapes.or(shape, box(0.0D, 0.0D, 15.0D, 1.0D, 16.0D, 16.0D));
        if (state.getValue(NORTHWEST))
            shape = Shapes.or(shape, box(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 1.0D));
        if (shape == Shapes.empty())
            shape = box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
        return shape;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        VoxelShape shape = Shapes.empty();
        if (state.getValue(DOWN))
            shape = Shapes.or(shape, box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D));
        if (state.getValue(NORTH))
            shape = Shapes.or(shape, box(1.0D, 0.0D, 0.0D, 15.0D, 16.0D, 1.0D));
        if (state.getValue(EAST))
            shape = Shapes.or(shape, box(15.0D, 0.0D, 1.0D, 16.0D, 16.0D, 15.0D));
        if (state.getValue(SOUTH))
            shape = Shapes.or(shape, box(1.0D, 0.0D, 15.0D, 15.0D, 16.0D, 16.0D));
        if (state.getValue(WEST))
            shape = Shapes.or(shape, box(0.0D, 0.0D, 1.0D, 1.0D, 16.0D, 15.0D));
        if (state.getValue(NORTHEAST))
            shape = Shapes.or(shape, box(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D));
        if (state.getValue(SOUTHEAST))
            shape = Shapes.or(shape, box(15.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D));
        if (state.getValue(SOUTHWEST))
            shape = Shapes.or(shape, box(0.0D, 0.0D, 15.0D, 1.0D, 16.0D, 16.0D));
        if (state.getValue(NORTHWEST))
            shape = Shapes.or(shape, box(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 1.0D));
        if (shape == Shapes.empty())
            shape = box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
        return shape;
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter pReader, BlockPos pPos, CollisionContext pContext) {
        VoxelShape shape = Shapes.empty();
        if (state.getValue(DOWN))
            shape = Shapes.or(shape, box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D));
        if (state.getValue(NORTH))
            shape = Shapes.or(shape, box(1.0D, 0.0D, 0.0D, 15.0D, 16.0D, 1.0D));
        if (state.getValue(EAST))
            shape = Shapes.or(shape, box(15.0D, 0.0D, 1.0D, 16.0D, 16.0D, 15.0D));
        if (state.getValue(SOUTH))
            shape = Shapes.or(shape, box(1.0D, 0.0D, 15.0D, 15.0D, 16.0D, 16.0D));
        if (state.getValue(WEST))
            shape = Shapes.or(shape, box(0.0D, 0.0D, 1.0D, 1.0D, 16.0D, 15.0D));
        if (state.getValue(NORTHEAST))
            shape = Shapes.or(shape, box(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D));
        if (state.getValue(SOUTHEAST))
            shape = Shapes.or(shape, box(15.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D));
        if (state.getValue(SOUTHWEST))
            shape = Shapes.or(shape, box(0.0D, 0.0D, 15.0D, 1.0D, 16.0D, 16.0D));
        if (state.getValue(NORTHWEST))
            shape = Shapes.or(shape, box(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 1.0D));
        if (shape == Shapes.empty())
            shape = box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
        return shape;
    }
}
