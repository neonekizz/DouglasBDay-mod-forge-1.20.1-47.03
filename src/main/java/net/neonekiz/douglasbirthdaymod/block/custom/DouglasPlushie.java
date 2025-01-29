package net.neonekiz.douglasbirthdaymod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;

import javax.annotation.Nullable;
public class DouglasPlushie extends Block {
    public static final VoxelShape SHAPE = Block.box(4, 0, 4, 12, 14.75, 12);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;{
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));

    }




    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }


    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }



    public DouglasPlushie(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public void wasExploded(Level pLevel, BlockPos pPos, Explosion pExplosion) {
        pLevel.explode(null, pPos.getX(), pPos.getY(), pPos.getZ(), 3.5F, true, Level.ExplosionInteraction.BLOCK);
        super.wasExploded(pLevel, pPos, pExplosion);
    }


    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack itemStack = pPlayer.getItemInHand(pHand);
        if (!itemStack.is(Items.FLINT_AND_STEEL) && !itemStack.is(Items.FIRE_CHARGE)) {
            return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
        } else {
            this.onCaughtFire(pState, pLevel, pPos, pHit.getDirection(), pPlayer);
            pLevel.explode(pPlayer, pPos.getX(), pPos.getY(), pPos.getZ(), 6.0F, true, Level.ExplosionInteraction.BLOCK);
            Item item = itemStack.getItem();
            if (!pPlayer.isCreative()) {

                if (item == Items.FLINT_AND_STEEL) {

                    itemStack.hurtAndBreak(1, pPlayer, p_57425_ -> p_57425_.broadcastBreakEvent(pHand));
                } else {

                    itemStack.shrink(1);

                }

            }





            pPlayer.awardStat(Stats.ITEM_USED.get(item));
            return InteractionResult.sidedSuccess(pLevel.isClientSide);




        }


    }

    public boolean dropFromExplosion(Explosion pExplosion) {
        return false;
    }
}



