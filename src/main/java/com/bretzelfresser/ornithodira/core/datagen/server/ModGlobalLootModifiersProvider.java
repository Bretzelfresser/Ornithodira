package com.bretzelfresser.ornithodira.core.datagen.server;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.loot.AddItemModifier;
import com.bretzelfresser.ornithodira.core.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, Ornithodira.MODID);
    }

    @Override
    protected void start() {
        add("add_to_desert_pyramid", new AddItemModifier(new LootItemCondition[]{
              new LootTableIdCondition.Builder(BuiltInLootTables.DESERT_PYRAMID).build(),
                LootItemRandomChanceCondition.randomChance(0.25f).build()
        }, ModItems.PARADOX_AMULET.get()));
        add("add_to_jungle_temple", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(BuiltInLootTables.JUNGLE_TEMPLE).build(),
                LootItemRandomChanceCondition.randomChance(0.25f).build()
        }, ModItems.PARADOX_AMULET.get()));
    }
}
