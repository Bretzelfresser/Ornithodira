package com.bretzelfresser.ornithodira.common.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.function.Function;

public class PrehistoricGratitude extends MobEffect {

    protected static final Function<Double, Double> xpGain = getXpBoostFunction(0.2d, 2d);

    public PrehistoricGratitude() {
        super(MobEffectCategory.BENEFICIAL, 0xB0FC38);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onXpGain(PlayerXpEvent.XpChange event) {
        if (event.getEntity().hasEffect(this)) {
            int amplifier = Math.max(0, event.getEntity().getEffect(this).getAmplifier());
            double percentageBoost = 1d + xpGain.apply(Double.valueOf(amplifier));
            double xpGain = (double) event.getAmount() * percentageBoost;
            event.setAmount((int) Math.round(xpGain));
        }
    }

    /**
     * @param minXP the xp the player gains with amplifier 0
     * @param maxXP the asymptotic maxXp that will eventually be reached
     * @return a function that describes exponential growth with this specs
     */
    public static Function<Double, Double> getXpBoostFunction(double minXP, double maxXP) {
        double a = minXP - maxXP;
        return x -> -a * Math.pow(Math.E, -x) + maxXP;
    }


}
