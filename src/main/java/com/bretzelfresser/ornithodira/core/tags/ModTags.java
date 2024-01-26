package com.bretzelfresser.ornithodira.core.tags;

import com.bretzelfresser.ornithodira.Ornithodira;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Items{
        public static final TagKey<Item> SANCHUANSAURUS_STEER_ITEMS = tag("sanchuansaurus_steer_items");
        public static final TagKey<Item> COMMON_EGGS = tag("common_eggs");

        private static TagKey<Item> tag(String pName) {
            return TagKey.create(Registries.ITEM, Ornithodira.modLoc(pName));
        }
    }

    public static class Blocks{

        public static final TagKey<Block> TAHOEODON_DIG_BLOCKS = tag("taoheodon_digging_blocks");

        private static TagKey<Block> tag(String pName) {
            return TagKey.create(Registries.BLOCK, Ornithodira.modLoc(pName));
        }
    }
}
