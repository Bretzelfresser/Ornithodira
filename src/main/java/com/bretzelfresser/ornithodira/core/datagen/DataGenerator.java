package com.bretzelfresser.ornithodira.core.datagen;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.core.datagen.client.ModItemModelsProvider;
import com.bretzelfresser.ornithodira.core.datagen.client.ModLanguageProvider;
import com.bretzelfresser.ornithodira.core.datagen.server.ModGlobalLootModifiersProvider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.IOException;

@Mod.EventBusSubscriber(modid = Ornithodira.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerator {


    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        net.minecraft.data.DataGenerator gen = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();
        PackOutput output = event.getGenerator().getPackOutput();

        gen.addProvider(event.includeClient(), new ModItemModelsProvider(output, helper));
        gen.addProvider(event.includeClient(), new ModLanguageProvider(output));
        gen.addProvider(event.includeServer(), new ModGlobalLootModifiersProvider(output));



        try {
            gen.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
