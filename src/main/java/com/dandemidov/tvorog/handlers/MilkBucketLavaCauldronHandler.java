package com.dandemidov.tvorog.handlers;

import com.dandemidov.tvorog.items.ModItems;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

import java.util.logging.Logger;

public class MilkBucketLavaCauldronHandler {
    public static void register() {
        UseBlockCallback.EVENT.register(MilkBucketLavaCauldronHandler::handleMilkToWhey);
    }

    private static ActionResult handleMilkToWhey(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        // Check if the block is a lava cauldron and the item in hand is a milk bucket
        Block block = world.getBlockState(hitResult.getBlockPos()).getBlock();
        ItemStack itemStack = player.getStackInHand(hand);

        boolean isLavaCauldron = block == Blocks.LAVA_CAULDRON;
        boolean isCauldron = block == Blocks.CAULDRON;
        boolean isHolingMilkBucket = itemStack.getItem() == Items.MILK_BUCKET;

        if (isLavaCauldron && isHolingMilkBucket) {
            // Convert milk into whey preserving the bucket
            player.setStackInHand(hand, new ItemStack(Items.BUCKET));
            player.giveItemStack(new ItemStack(ModItems.MILK_WHEY));

            // Notify the player about the event by playing the corresponding sound
            world.playSound(null, hitResult.getBlockPos(), SoundEvents.ENTITY_COW_MILK, SoundCategory.BLOCKS, 1.0f, 1.0f);

            // Apply some damage (because the bucket is hot, you know)
            if (player.canTakeDamage()) {
                player.damage(null, 1.0f);
            }

            return ActionResult.SUCCESS;

//            return ActionResult.CONSUME;
        }

        return ActionResult.PASS;
    }
}