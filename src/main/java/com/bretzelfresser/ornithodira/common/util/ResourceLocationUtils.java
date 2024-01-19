package com.bretzelfresser.ornithodira.common.util;

import net.minecraft.resources.ResourceLocation;

public class ResourceLocationUtils {


    public static ResourceLocation extend(ResourceLocation toExtend, String extender){
        return new ResourceLocation(toExtend.getNamespace(), toExtend.getPath() + extender);
    }

    public static ResourceLocation prepend(ResourceLocation toExtend, String extender){
        return new ResourceLocation(toExtend.getNamespace(),  extender + toExtend.getPath());
    }
}
