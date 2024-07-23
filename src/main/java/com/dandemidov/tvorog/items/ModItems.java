package com.dandemidov.tvorog.items;

import com.dandemidov.tvorog.Tvorog;
import com.dandemidov.tvorog.items.milk_whey.MilkWhey;
import com.dandemidov.tvorog.items.tvorog_bucket.TvorogBucket;
import com.dandemidov.tvorog.items.tvorog_bucket_with_jam.TvorogWithJamBucket;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.type.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public final static Item TVOROG_BUCKET = register(new TvorogBucket(), TvorogBucket.ID);

    public final static Item TVOROG_WITH_BERRIES_BUCKET = register(new TvorogWithJamBucket(), TvorogWithJamBucket.ID);

    public final static Item MILK_WHEY = register(new MilkWhey(), MilkWhey.ID);

    public static Item register(Item item, String id) {
        Identifier itemID = Identifier.of(Tvorog.MOD_ID, id);

        Item registeredItem = Registry.register(Registries.ITEM, itemID, item);

        return registeredItem;
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
                .register((itemGroup) -> itemGroup.add(TVOROG_BUCKET));
    }
}