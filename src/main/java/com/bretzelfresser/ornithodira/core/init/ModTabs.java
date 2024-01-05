package com.bretzelfresser.ornithodira.core.init;

import com.bretzelfresser.ornithodira.Ornithodira;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Ornithodira.MODID);

    public static final RegistryObject<CreativeModeTab> ITEMS = register("ornithodira_items",  CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ModItems.DIAMOND_BRUSH.get().getDefaultInstance()));
    public static final RegistryObject<CreativeModeTab> SPAWN_EGGS = register("ornithodira_spawn_eggs", CreativeModeTab.builder()
            .withTabsBefore(ITEMS.getId())
            .icon(() -> ModItems.SANCHUANSAURUS_SPAWN_EGG.get().getDefaultInstance()));

    public static RegistryObject<CreativeModeTab> register(String name, CreativeModeTab.Builder tabBuilder){
        CreativeModeTab.Builder finalTabBuilder = tabBuilder.title(Component.translatable(makeDiscriptionId(name)));
        return CREATIVE_MODE_TABS.register(name, () -> finalTabBuilder.build());
    }

    public static String makeDiscriptionId(String name){
        return "creative_tab." + Ornithodira.MODID + "." + name;
    }
}
