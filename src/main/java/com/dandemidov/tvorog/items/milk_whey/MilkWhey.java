package com.dandemidov.tvorog.items.milk_whey;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class MilkWhey extends Item {
    private static final Settings SETTINGS = new Item.Settings();

    public static final String ID = "milk_whey";

    public MilkWhey() {
        super(SETTINGS);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (entity instanceof ChickenEntity chicken) {
            stack.decrement(1);
            user.setStackInHand(hand, stack);

            chicken.dropStack(new ItemStack(Items.EGG));
        }

        return super.useOnEntity(stack, user, entity, hand);
    }
}