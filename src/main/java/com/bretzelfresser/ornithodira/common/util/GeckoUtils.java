package com.bretzelfresser.ornithodira.common.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class GeckoUtils {

    /**
     * defines whether the entity is moving or not
     * @param e the entity we want to decide that on
     * @param minDistMovedVertically the minimal distance it has to move in order to trigger this, this is inclusive
     * @return whether the entity is moving or not
     */
    public static boolean isMoving(Entity e, double minDistMovedVertically){
        Vec3 oldPos = new Vec3(e.xOld, 0, e.zOld);
        double distanceSquared = e.distanceToSqr(oldPos);
        return distanceSquared >= minDistMovedVertically * minDistMovedVertically;
    }
}
