package com.bretzelfresser.ornithodira.common.block;

import com.bretzelfresser.ornithodira.common.recipe.EggEntitiesRecipe;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class CustomEggBlock extends Block {

    public static final VoxelShape SHAPE_EGG1 = Block.box(4, 0, 4, 12, 7, 12);
    public static final VoxelShape SHAPE_EGG_OTHER = Block.box(1, 0, 1, 15, 7, 15);

    public static final IntegerProperty HATCH = BlockStateProperties.HATCH;
    public static final IntegerProperty EGGS = BlockStateProperties.EGGS;

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public static final BooleanProperty FOSSILIZED = BooleanProperty.create("fossilized");

    protected final Supplier<RecipeType<? extends EggEntitiesRecipe>> recipeSupplier;
    protected final Predicate<ItemStack> fossilizedEgg, cleanEgg;
    protected final float probability, fortuneAddition;
    protected final int minToolLevel;

    public CustomEggBlock(Supplier<RecipeType<? extends EggEntitiesRecipe>> recipeSUpplier, float probability, float fortuneAddition, int minToolLevel, Predicate<ItemStack> fossilizedEgg, Predicate<ItemStack> cleanEgg, Properties pProperties) {
        super(pProperties);
        this.probability = probability;
        this.fortuneAddition = fortuneAddition;
        this.minToolLevel = minToolLevel;
        this.fossilizedEgg = fossilizedEgg;
        this.cleanEgg = cleanEgg;
        this.recipeSupplier = recipeSUpplier;
        this.registerDefaultState(this.stateDefinition.any().setValue(HATCH, 0).setValue(EGGS, 1).setValue(FOSSILIZED, false).setValue(FACING, Direction.NORTH));
    }

    public CustomEggBlock(Supplier<RecipeType<? extends EggEntitiesRecipe>> recipeSUpplier, float probability, float fortuneAddition, int minToolLevel, Supplier<ItemLike> fossilizedEgg, Supplier<ItemLike> cleanEgg, Properties pProperties) {
        this(recipeSUpplier, probability, fortuneAddition, minToolLevel, stack -> stack.getItem() == fossilizedEgg.get().asItem(), stack -> stack.getItem() == cleanEgg.get().asItem(), pProperties);
    }

    public void brush(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand) {
        int eggs = state.getValue(EGGS);
        ItemStack tool = player.getItemInHand(hand);
        int fortuneLevel = tool.getEnchantmentLevel(Enchantments.BLOCK_FORTUNE);
        float chance = Mth.clamp(probability + fortuneLevel * fortuneAddition, 0, 1);
        int moreEggs = 0;
        for (int i = 0; i < eggs; i++) {
            if (level.getRandom().nextFloat() <= chance) {
                moreEggs++;
            }
        }
        if (moreEggs > 0)
            level.setBlock(pos, state.setValue(FOSSILIZED, false).setValue(EGGS, moreEggs), 3);
        else {
            level.destroyBlock(pos, false, player, 1 << 5);
        }
        if (level.isClientSide) {
            BlockParticleOption blockparticleoption = new BlockParticleOption(ParticleTypes.BLOCK, state);
            Vec3 centerPos = Vec3.atCenterOf(pos);
            for (double x = -.25d; x <= .25d; x += .01d) {
                for (double z = -.25d; z <= .25d; z += .01d) {
                    double y = Math.sqrt(1 - z * z - x * x);
                    Vec3 moition = new Vec3(x, y, z).normalize().scale(.3d);
                    level.addParticle(blockparticleoption, centerPos.x, centerPos.y, centerPos.z, moition.x, moition.y, moition.z);
                }
            }

        }

    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRot) {
        return pState.setValue(FACING, pRot.rotate(pState.getValue(FACING)));
    }

    public boolean canBrush(BlockState state) {
        return state.getValue(FOSSILIZED);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack held = pPlayer.getItemInHand(pHand);
        if (pState.getValue(FOSSILIZED)) {
            if (fossilizedEgg.test(held) && pState.getValue(EGGS) < 4) {
                if (!pLevel.isClientSide) {
                    pLevel.setBlock(pPos, pState.setValue(EGGS, pState.getValue(EGGS) + 1), 3);
                    if (!pPlayer.isCreative())
                        held.shrink(1);
                }
                return InteractionResult.SUCCESS;
            }
        } else {
            if (cleanEgg.test(held) && pState.getValue(EGGS) < 4) {
                if (!pLevel.isClientSide) {
                    pLevel.setBlock(pPos, pState.setValue(EGGS, pState.getValue(EGGS) + 1), 3);
                    if (!pPlayer.isCreative())
                        held.shrink(1);
                }
                return InteractionResult.SUCCESS;
            }
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (!pEntity.isSteppingCarefully() && !pState.getValue(FOSSILIZED)) {
            this.destroyEgg(pLevel, pState, pPos, pEntity, 100);
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction facing = context.getHorizontalDirection();
        int random = context.getLevel().random.nextInt(4);
        for (int i = 0; i < random; i++) {
            facing = facing.getClockWise();
        }
        return super.getStateForPlacement(context).setValue(FACING, facing);
    }

    public void fallOn(Level pLevel, BlockState pState, BlockPos pPos, Entity pEntity, float pFallDistance) {
        if (!(pEntity instanceof Zombie) && !pState.getValue(FOSSILIZED)) {
            this.destroyEgg(pLevel, pState, pPos, pEntity, 3);
        }

        super.fallOn(pLevel, pState, pPos, pEntity, pFallDistance);
    }

    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!pState.getValue(FOSSILIZED) && this.shouldUpdateHatchLevel(pLevel)) {
            int i = pState.getValue(HATCH);
            if (i < 2) {
                pLevel.playSound((Player) null, pPos, SoundEvents.TURTLE_EGG_CRACK, SoundSource.BLOCKS, 0.7F, 0.9F + pRandom.nextFloat() * 0.2F);
                pLevel.setBlock(pPos, pState.setValue(HATCH, Integer.valueOf(i + 1)), 2);
            } else {
                pLevel.playSound((Player) null, pPos, SoundEvents.TURTLE_EGG_HATCH, SoundSource.BLOCKS, 0.7F, 0.9F + pRandom.nextFloat() * 0.2F);
                pLevel.removeBlock(pPos, false);

                EntityType<?> type = spawnEntity(pState, pLevel, pPos, pRandom);
                for (int j = 0; j < pState.getValue(EGGS); ++j) {
                    pLevel.levelEvent(2001, pPos, Block.getId(pState));
                    Entity turtle = type.create(pLevel);
                    if (turtle != null && turtle instanceof AgeableMob ageableMob) {
                        ageableMob.setAge(-24000);
                    }
                    turtle.moveTo((double) pPos.getX() + 0.3D + (double) j * 0.2D, (double) pPos.getY(), (double) pPos.getZ() + 0.3D, 0.0F, 0.0F);
                    pLevel.addFreshEntity(turtle);
                }
            }
        }

    }

    protected EntityType<?> spawnEntity(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom){
        RecipeType<? extends EggEntitiesRecipe> type = recipeSupplier.get();
        List<Pair<EntityType<?>, Integer>> entities = new ArrayList<>();
        for (EggEntitiesRecipe recipes : pLevel.getRecipeManager().getAllRecipesFor(type)){
            entities.addAll(recipes.getEntries());
        }
        EntityType<?> weightedRandom = getRandomEntity(entities, pRandom);
        return weightedRandom;
    }

    public static EntityType<?> getRandomEntity(List<Pair<EntityType<?>, Integer>> entities, RandomSource random) {
        int totalWeight = entities.stream().mapToInt(Pair::getSecond).sum();
        int randomWeight = random.nextInt(totalWeight) + 1; // Add 1 to make it inclusive

        for (Pair<EntityType<?>, Integer> pair : entities) {
            randomWeight -= pair.getSecond();
            if (randomWeight <= 0) {
                return pair.getFirst();
            }
        }

        throw new IllegalStateException("This should not happen, but in case of issues, return null or handle it accordingly");
    }

    @Override
    public void playerDestroy(Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState, @Nullable BlockEntity pTe, ItemStack pStack) {
        super.playerDestroy(pLevel, pPlayer, pPos, pState, pTe, pStack);
        this.decreaseEggs(pLevel, pPos, pState);
    }

    private boolean shouldUpdateHatchLevel(Level pLevel) {
        float f = pLevel.getTimeOfDay(1.0F);
        if ((double) f < 0.69D && (double) f > 0.65D) {
            return true;
        } else {
            return pLevel.random.nextInt(500) == 0;
        }
    }

    private void destroyEgg(Level pLevel, BlockState pState, BlockPos pPos, Entity pEntity, int pChance) {
        if (canDestroyEgg(pLevel, pEntity)) {
            if (!pLevel.isClientSide && pLevel.random.nextInt(pChance) == 0 && pState.is(this)) {
                this.decreaseEggs(pLevel, pPos, pState);
            }

        }
    }

    private void decreaseEggs(Level pLevel, BlockPos pPos, BlockState pState) {
        pLevel.playSound((Player) null, pPos, SoundEvents.TURTLE_EGG_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + pLevel.random.nextFloat() * 0.2F);
        int i = pState.getValue(EGGS);
        if (i <= 1) {
            pLevel.destroyBlock(pPos, false);
        } else {
            pLevel.setBlock(pPos, pState.setValue(EGGS, Integer.valueOf(i - 1)), 2);
            pLevel.gameEvent(GameEvent.BLOCK_DESTROY, pPos, GameEvent.Context.of(pState));
            pLevel.levelEvent(2001, pPos, Block.getId(pState));
        }

    }

    private static boolean canDestroyEgg(Level pLevel, Entity pEntity) {
        if (!(pEntity instanceof Turtle) && !(pEntity instanceof Bat)) {
            if (!(pEntity instanceof LivingEntity)) {
                return false;
            } else {
                return pEntity instanceof Player || net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(pLevel, pEntity);
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockState below = pLevel.getBlockState(pPos.below());
        return below.isFaceSturdy(pLevel, pPos.below(), Direction.UP);
    }

    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!pLevel.isAreaLoaded(pPos, 1))
            return; // Forge: prevent growing cactus from loading unloaded chunks with block update
        if (!pState.canSurvive(pLevel, pPos)) {
            pLevel.destroyBlock(pPos, true);
        }

    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
        if (!pState.canSurvive(pLevel, pCurrentPos)) {
            pLevel.scheduleTick(pCurrentPos, this, 1);
        }

        return super.updateShape(pState, pDirection, pNeighborState, pLevel, pCurrentPos, pNeighborPos);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return pState.getValue(EGGS) <= 1 ? SHAPE_EGG1 : SHAPE_EGG_OTHER;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(HATCH, EGGS, FOSSILIZED, FACING);
    }

    public float getProbability() {
        return probability;
    }

    public float getFortuneAddition() {
        return fortuneAddition;
    }

    public int getMinToolLevel() {
        return minToolLevel;
    }
}
