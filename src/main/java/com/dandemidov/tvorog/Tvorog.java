package com.dandemidov.tvorog;

import com.dandemidov.tvorog.handlers.ModHandlers;
import com.dandemidov.tvorog.items.ModItems;
import net.fabricmc.api.ModInitializer;

public class Tvorog implements ModInitializer {
    public static final String MOD_ID = "tvorog";

    public static float TVOROG_SATURATION = 1;

    @Override
    public void onInitialize() {
        ModItems.initialize();
        ModHandlers.initialize();
    }
}
