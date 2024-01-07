package com.bretzelfresser.ornithodira.core.datagen.client;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.core.init.ModEntities;
import com.bretzelfresser.ornithodira.core.init.ModItems;
import com.bretzelfresser.ornithodira.core.init.ModTabs;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;

import java.util.concurrent.CompletableFuture;

public class ModLanguageProvider extends BetterLanguageProvider {
    public ModLanguageProvider(PackOutput output) {
        super(output, Ornithodira.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        ModItems.ITEMS.getEntries().stream().forEach(i -> {
            try {
                add(i.get(), toTitleCase(i.getId().getPath()));
            }catch (Exception ignored){
            }

        });
        ModEntities.ENTITIES.getEntries().forEach(e -> {
            add(e.get(), toTitleCase(e.getId().getPath()));
        });
        ModTabs.CREATIVE_MODE_TABS.getEntries().stream().forEach(i -> {
            try {
                add(ModTabs.makeDiscriptionId(i.getId().getPath()), toTitleCase(i.getId().getPath()));
            }catch (Exception ignored){

            }

        });
    }

    public static String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (c == '_') {
                nextTitleCase = true;
                titleCase.append(" ");
                continue;
            }

            if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            } else {
                c = Character.toLowerCase(c);
            }

            titleCase.append(c);
        }

        return titleCase.toString();
    }
}
