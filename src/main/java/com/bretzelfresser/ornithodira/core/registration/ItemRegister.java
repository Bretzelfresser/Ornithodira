package com.bretzelfresser.ornithodira.core.registration;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.registries.DeferredRegister;

import java.util.Map;
import java.util.function.Supplier;

public class ItemRegister {

    protected final DeferredRegister<Item> register;

    public ItemRegister(DeferredRegister<Item> register) {
        this.register = register;
    }



    public static class ItemProperties{

        public static ItemPropertiesBuilder builder(){
            return new ItemPropertiesBuilder();
        }

        protected final Supplier<CreativeModeTab> tab;
        protected final boolean simple, spawnEgg, custom;

        protected final String translatedName;
        public ItemProperties(Supplier<CreativeModeTab> tab, boolean simple, boolean spawnEgg, boolean custom, String translatedName) {
            this.tab = tab;
            this.simple = simple;
            this.spawnEgg = spawnEgg;
            this.custom = custom;
            this.translatedName = translatedName;
        }

        public CreativeModeTab getTab() {
            return tab.get();
        }

        public boolean isSimple() {
            return simple;
        }

        public boolean isSpawnEgg() {
            return spawnEgg;
        }

        public boolean isCustom() {
            return custom;
        }

        public String getTranslatedName() {
            return translatedName;
        }
    }

    public static class ItemPropertiesBuilder{
        protected Supplier<CreativeModeTab> tab;
        protected boolean simple, spawnEgg, custom;

        protected String translatedName;

        public ItemPropertiesBuilder noTab(){
            this.tab = null;
            return this;
        }

        public ItemPropertiesBuilder tab(Supplier<CreativeModeTab> tab){
            this.tab = tab;
            return this;
        }

        public ItemPropertiesBuilder simpleModel(){
            this.simple = true;
            this.spawnEgg = false;
            this.custom = false;
            return this;
        }

        public ItemPropertiesBuilder spawnEgg(){
            this.spawnEgg = true;
            this.simple = false;
            this.custom = false;
            return this;
        }

        public ItemPropertiesBuilder customModel(){
            this.spawnEgg = false;
            this.custom = true;
            this.simple = false;
            return this;
        }

        public ItemPropertiesBuilder translatedName(String name){
            this.translatedName = name;
            return this;
        }

        public ItemProperties build(){
            return new ItemProperties(this.tab, simple, spawnEgg, custom, this.translatedName);
        }
    }


}
