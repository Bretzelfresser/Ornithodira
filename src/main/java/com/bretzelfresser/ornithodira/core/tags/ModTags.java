package com.bretzelfresser.ornithodira.core.tags;

import com.bretzelfresser.ornithodira.Ornithodira;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {

    public static class Items{
        public static final TagKey<Item> SANCHUANSAURUS_STEER_ITEMS = tag("sanchuansaurus_steer_items");

        private static TagKey<Item> tag(String pName) {
            return TagKey.create(Registries.ITEM, Ornithodira.modLoc(pName));
        }
    }
}
