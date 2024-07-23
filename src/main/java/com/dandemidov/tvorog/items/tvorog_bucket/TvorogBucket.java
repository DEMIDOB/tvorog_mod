package com.dandemidov.tvorog.items.tvorog_bucket;

import com.dandemidov.tvorog.Tvorog;
import com.dandemidov.tvorog.items.ModItems;
import com.dandemidov.tvorog.items.tvorog_bucket_with_jam.TvorogWithJamBucket;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class TvorogBucket extends Item {
    private static final Settings SETTINGS = new Item.Settings().food(
            new FoodComponent.Builder().nutrition(10).saturationModifier(Tvorog.TVOROG_SATURATION * 1.2f).build()
    );
    public static final String ID = "tvorog_bucket";

    public TvorogBucket() {
        super(SETTINGS);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        Block block = world.getBlockState(context.getBlockPos()).getBlock();
        PlayerEntity player = context.getPlayer();

        if (player != null && block == Blocks.SWEET_BERRY_BUSH && !world.isClient) {
            player.getItemCooldownManager().set(this, 20); // Add a cooldown if desired
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PLAYER_SPLASH, SoundCategory.PLAYERS, 1.0F, 2.0F);
            player.setStackInHand(Hand.MAIN_HAND, new ItemStack(ModItems.TVOROG_WITH_BERRIES_BUCKET));

            return ActionResult.SUCCESS;
        }

        return super.useOnBlock(context);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user.isPlayer()) {
            PlayerEntity player = (PlayerEntity) user;
            player.clearStatusEffects();

            // Stop burning!
            player.extinguish();

            player.getInventory().offerOrDrop(new ItemStack(net.minecraft.item.Items.BUCKET));
        }

        return super.finishUsing(stack, world, user);
    }
}