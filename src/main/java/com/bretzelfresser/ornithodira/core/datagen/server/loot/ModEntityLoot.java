package com.bretzelfresser.ornithodira.core.datagen.server.loot;

import com.bretzelfresser.ornithodira.core.init.ModEntities;
import com.bretzelfresser.ornithodira.core.init.ModItems;
import net.minecraft.data.loot.packs.VanillaEntityLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class ModEntityLoot extends VanillaEntityLoot {

    protected Set<EntityType<?>> knowEntities = new HashSet<>();


    @Override
    public void generate() {
        add(ModEntities.DAPEDIUM.get(), makeFishPool(ModItems.DAPEDIUM.get()));
        add(ModEntities.CLADOCYCLUS.get(), makeFishPool(ModItems.CLADOCYCLUS.get()));
    }

    public LootTable.Builder makeFishPool(ItemLike fish){
        return LootTable.lootTable().withPool(
                LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(fish).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.BONE_MEAL)).when(LootItemRandomChanceCondition.randomChance(0.05F)));
    }

    @Override
    protected void add(EntityType<?> pEntityType, ResourceLocation pLootTableLocation, LootTable.Builder pBuilder) {
        this.knowEntities.add(pEntityType);
        super.add(pEntityType, pLootTableLocation, pBuilder);
    }

    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        return this.knowEntities.stream();
    }
}
