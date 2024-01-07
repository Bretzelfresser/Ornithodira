package com.bretzelfresser.ornithodira.core.datagen.server;

import com.bretzelfresser.ornithodira.core.datagen.server.loot.ModArcheologyLoot;
import com.bretzelfresser.ornithodira.core.datagen.server.loot.ModBlockLoot;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class ModLoot extends LootTableProvider {
    public ModLoot(PackOutput pOutput) {
        super(pOutput, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(ModBlockLoot::new, LootContextParamSets.BLOCK),
                new LootTableProvider.SubProviderEntry(ModArcheologyLoot::new, LootContextParamSets.ARCHAEOLOGY)
        ));
    }
}
