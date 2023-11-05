package com.bretzelfresser.ornithodira.core.datagen.client;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.core.init.ModItems;
import com.bretzelfresser.ornithodira.core.init.ModTabs;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output) {
        super(output, Ornithodira.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        ModItems.ITEMS.getEntries().stream().forEach(i -> add(i.get(), toTitleCase(i.getId().getPath())));
        ModTabs.CREATIVE_MODE_TABS.getEntries().stream().forEach(i -> add(ModTabs.makeDiscriptionId(i.getId().getPath()), toTitleCase(i.getId().getPath())));
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
