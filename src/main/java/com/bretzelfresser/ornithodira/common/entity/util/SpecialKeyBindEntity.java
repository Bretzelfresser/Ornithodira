package com.bretzelfresser.ornithodira.common.entity.util;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public interface SpecialKeyBindEntity {

    boolean canExecute(Player sender);

    void execute(Player sender);
}
