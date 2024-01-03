package com.bretzelfresser.ornithodira.core.datagen;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.core.datagen.client.ModBlockStatesProvider;
import com.bretzelfresser.ornithodira.core.datagen.client.ModItemModelsProvider;
import com.bretzelfresser.ornithodira.core.datagen.client.ModLanguageProvider;
import com.bretzelfresser.ornithodira.core.datagen.server.ModBlockTagsProvider;
import com.bretzelfresser.ornithodira.core.datagen.server.ModGlobalLootModifiersProvider;
import com.bretzelfresser.ornithodira.core.datagen.server.ModItemTagsProvider;
import com.bretzelfresser.ornithodira.core.datagen.server.ModRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = Ornithodira.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerator {


    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        net.minecraft.data.DataGenerator gen = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();
        PackOutput output = event.getGenerator().getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        gen.addProvider(event.includeClient(), new ModItemModelsProvider(output, helper));
        gen.addProvider(event.includeServer(), new ModLanguageProvider(output));
        gen.addProvider(event.includeServer(), new ModGlobalLootModifiersProvider(output));
        gen.addProvider(event.includeServer(), new ModRecipeProvider(output));
        gen.addProvider(event.includeClient(), new ModBlockStatesProvider(output, helper));
        ModBlockTagsProvider blockTags = gen.addProvider(event.includeServer(), new ModBlockTagsProvider(output,lookupProvider, helper));
        gen.addProvider(event.includeServer(), new ModItemTagsProvider(output, lookupProvider, blockTags.contentsGetter(), helper));



        try {
            gen.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
