package com.bretzelfresser.ornithodira.common.item;

import com.bretzelfresser.ornithodira.common.block.CustomEggBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Map;
import java.util.function.Predicate;

public class BrushTool extends Item {
    public static final int USE_DURATION = 10 * 20;
    public static final double MAX_BRUSH_DISTANCE = Math.sqrt(ServerGamePacketListenerImpl.MAX_INTERACTION_DISTANCE) - 1.0D;

    protected final int toolLevel;
    protected final Predicate<BlockState> canBrush;
    protected final BrushConsumer consumer;

    public BrushTool(int toolLevel, Predicate<BlockState> canBrush, BrushConsumer consumer, Properties pProperties) {
        super(pProperties);
        this.toolLevel = toolLevel;
        this.canBrush = canBrush;
        this.consumer = consumer;
    }

    public BrushTool(int toolLevel, Properties pProperties) {
        this(toolLevel, state -> state.getBlock() instanceof CustomEggBlock egg && egg.canBrush(state) && egg.getMinToolLevel() <= toolLevel, (state, level, pos, player, hand) -> {
            if (state.getBlock() instanceof CustomEggBlock egg) {
                egg.brush(state, level, pos, player, hand);
            }
        }, pProperties);
    }

    public InteractionResult useOn(UseOnContext pContext) {
        Player player = pContext.getPlayer();
        if (player != null && this.calculateHitResult(player) instanceof BlockHitResult blockHitResult && blockHitResult.getType() == HitResult.Type.BLOCK && canBrush.test(pContext.getLevel().getBlockState(blockHitResult.getBlockPos()))) {
            player.startUsingItem(pContext.getHand());
        }

        return InteractionResult.CONSUME;
    }

    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
        if (pRemainingUseDuration >= 0 && pLivingEntity instanceof Player player) {
            HitResult hitresult = this.calculateHitResult(pLivingEntity);
            if (hitresult instanceof BlockHitResult blockhitresult && hitresult.getType() == HitResult.Type.BLOCK && canBrush.test(pLevel.getBlockState(blockhitresult.getBlockPos()))) {
                int i = this.getUseDuration(pStack) - pRemainingUseDuration + 1;
                boolean flag = i % 10 == 5;
                if (flag || pRemainingUseDuration <= 1) {
                    BlockPos blockpos = blockhitresult.getBlockPos();
                    BlockState blockstate = pLevel.getBlockState(blockpos);
                    HumanoidArm humanoidarm = pLivingEntity.getUsedItemHand() == InteractionHand.MAIN_HAND ? player.getMainArm() : player.getMainArm().getOpposite();
                    spawnDustParticles(pLevel, blockhitresult, blockstate, pLivingEntity.getViewVector(0.0F), humanoidarm);
                    pLevel.playSound(player, blockpos, SoundEvents.BRUSH_GENERIC, SoundSource.BLOCKS);
                    if (pRemainingUseDuration <= 1 && canBrush.test(blockstate)) {
                        EquipmentSlot equipmentslot = pStack.equals(player.getItemBySlot(EquipmentSlot.OFFHAND)) ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND;
                        pStack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(equipmentslot));
                        this.consumer.brush(blockstate, pLevel, blockpos, player, pLivingEntity.getUsedItemHand());
                        pLivingEntity.releaseUsingItem();

                    }
                }
            } else
                pLivingEntity.releaseUsingItem();
        } else {
            pLivingEntity.releaseUsingItem();
        }
    }

    private HitResult calculateHitResult(LivingEntity pEntity) {
        return ProjectileUtil.getHitResultOnViewVector(pEntity, (entity) -> !entity.isSpectator() && entity.isPickable(), MAX_BRUSH_DISTANCE);
    }

    public int getToolLevel() {
        return toolLevel;
    }

    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BRUSH;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getUseDuration(ItemStack pStack) {
        return USE_DURATION;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean isEnchantable(ItemStack pStack) {
        return false;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    public static void spawnDustParticles(Level pLevel, BlockHitResult pHitResult, BlockState pState, Vec3 pPos, HumanoidArm pArm) {
        int i = pArm == HumanoidArm.RIGHT ? 1 : -1;
        int j = pLevel.getRandom().nextInt(7, 12);
        BlockParticleOption blockparticleoption = new BlockParticleOption(ParticleTypes.BLOCK, pState);
        Direction direction = pHitResult.getDirection();
        DustParticlesDelta brushitem$dustparticlesdelta = DustParticlesDelta.fromDirection(pPos, direction);
        Vec3 vec3 = pHitResult.getLocation();

        for (int k = 0; k < j; ++k) {
            pLevel.addParticle(blockparticleoption, vec3.x - (double) (direction == Direction.WEST ? 1.0E-6F : 0.0F), vec3.y, vec3.z - (double) (direction == Direction.NORTH ? 1.0E-6F : 0.0F), brushitem$dustparticlesdelta.xd() * (double) i * 3.0D * pLevel.getRandom().nextDouble(), 0.0D, brushitem$dustparticlesdelta.zd() * (double) i * 3.0D * pLevel.getRandom().nextDouble());
        }

    }

    public static record DustParticlesDelta(double xd, double yd, double zd) {

        public static DustParticlesDelta fromDirection(Vec3 pPos, Direction pDirection) {
            double d0 = 0.0D;
            DustParticlesDelta brushitem$dustparticlesdelta;
            switch (pDirection) {
                case DOWN:
                case UP:
                    brushitem$dustparticlesdelta = new DustParticlesDelta(pPos.z(), 0.0D, -pPos.x());
                    break;
                case NORTH:
                    brushitem$dustparticlesdelta = new DustParticlesDelta(1.0D, 0.0D, -0.1D);
                    break;
                case SOUTH:
                    brushitem$dustparticlesdelta = new DustParticlesDelta(-1.0D, 0.0D, 0.1D);
                    break;
                case WEST:
                    brushitem$dustparticlesdelta = new DustParticlesDelta(-0.1D, 0.0D, -1.0D);
                    break;
                case EAST:
                    brushitem$dustparticlesdelta = new DustParticlesDelta(0.1D, 0.0D, 1.0D);
                    break;
                default:
                    throw new IncompatibleClassChangeError();
            }

            return brushitem$dustparticlesdelta;
        }
    }

    public static interface BrushConsumer {
        public void brush(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand);
    }
}
