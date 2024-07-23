package com.dandemidov.tvorog.items.tvorog_bucket_with_jam;

import com.dandemidov.tvorog.Tvorog;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TvorogWithJamBucket extends Item {
    private static final Settings SETTINGS = new Item.Settings().food(
            new FoodComponent.Builder().nutrition(13).saturationModifier(Tvorog.TVOROG_SATURATION).build()
    );
    public static final String ID = "tvorog_with_jam_bucket";

    public TvorogWithJamBucket() {
        super(SETTINGS);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user.isPlayer()) {
            PlayerEntity player = (PlayerEntity) user;
            player.clearStatusEffects();

            player.getInventory().offerOrDrop(new ItemStack(net.minecraft.item.Items.BUCKET));
        }

        return super.finishUsing(stack, world, user);
    }
}