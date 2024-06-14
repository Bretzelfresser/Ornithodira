package com.bretzelfresser.ornithodira.core.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    //its the same as raw chicken
    public static final FoodProperties RAW_CLADOCYCLUS = new FoodProperties.Builder().nutrition(2).saturationMod(0.3F).effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F).meat().build();
    public static final FoodProperties COOKED_CLADOCYCLUS = new FoodProperties.Builder().nutrition(6).saturationMod(0.6F).meat().build();
}
