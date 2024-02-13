package com.bretzelfresser.ornithodira.core.init;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.block.CustomEggBlock;
import com.bretzelfresser.ornithodira.common.item.BrushTool;
import com.bretzelfresser.ornithodira.common.item.ModBlockItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Ornithodira.MODID);

    public static final RegistryObject<BrushTool> METAL_BRUSH = ITEMS.register("metal_brush", () -> new BrushTool(0, new Item.Properties().durability(10)));
    public static final RegistryObject<BrushTool> DIAMOND_BRUSH = ITEMS.register("diamond_brush", () -> new BrushTool(10, new Item.Properties().durability(10)));

    public static final RegistryObject<ModBlockItem> PARAREPTILE_EGG = ITEMS.register("parareptile_egg", () -> new ModBlockItem(ModBlocks.PARAREPTILE_EGG.get(), state -> state.setValue(CustomEggBlock.FOSSILIZED, false), new Item.Properties()));
    public static final RegistryObject<ModBlockItem> FOSSILIZED_PARAREPTILE_EGG = ITEMS.register("fossilized_parareptile_egg", () -> new ModBlockItem(ModBlocks.PARAREPTILE_EGG.get(), state -> state.setValue(CustomEggBlock.FOSSILIZED, true), new Item.Properties()));

    public static final RegistryObject<ModBlockItem> SYNAPSID_EGG = ITEMS.register("synapsid_egg", () -> new ModBlockItem(ModBlocks.SYNAPSID_EGG.get(), state -> state.setValue(CustomEggBlock.FOSSILIZED, false), new Item.Properties()));
    public static final RegistryObject<ModBlockItem> FOSSILIZED_SYNAPSID_EGG = ITEMS.register("fossilized_synapsid_egg", () -> new ModBlockItem(ModBlocks.SYNAPSID_EGG.get(), state -> state.setValue(CustomEggBlock.FOSSILIZED, true), new Item.Properties()));

    public static final RegistryObject<Item> NINGXIAITES_CONE_STICK = ITEMS.register("ningxiaites_cone_on_a_stick", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DAPEDIUM = ITEMS.register("dapedium", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CLADOCYCLUS = ITEMS.register("cladocyclus", () -> new Item(new Item.Properties()));


    public static final RegistryObject<ForgeSpawnEggItem> SANCHUANSAURUS_SPAWN_EGG = ITEMS.register("sanchuansaurus_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.SANCHUANSAURUS, 0x51382c, 0x993b13, new Item.Properties()));
    public static final RegistryObject<ForgeSpawnEggItem> TAOHEODON_SPAWN_EGG = ITEMS.register("taoheodon_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.TAOHEODON, 0xb56039, 0x8b8852, new Item.Properties()));
    public static final RegistryObject<ForgeSpawnEggItem> DAPEDIUM_SPAWN_EGG = ITEMS.register("dapedium_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.DAPEDIUM, 0x80d606, 0xd04d10, new Item.Properties()));
    public static final RegistryObject<ForgeSpawnEggItem> CLADOCYCLUS_SPAWN_EGG = ITEMS.register("cladocyclus_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.CLADOCYCLUS, 0x9ab8dc, 0x5f4044, new Item.Properties()));
    public static final RegistryObject<ForgeSpawnEggItem> JURAVENATOR_SPAWN_EGG = ITEMS.register("juravenator_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.JURAVENATOR, 0xa4ae230,0x599c10, new Item.Properties()));
    public static final RegistryObject<ForgeSpawnEggItem> GEMINIRAPTOR_SPAWN_EGG = ITEMS.register("geminiraptor_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.GEMINIRAPTOR, 0x9b3528,0x372b25, new Item.Properties()));
    public static final RegistryObject<ForgeSpawnEggItem> ANIMANTARX_SPAWN_EGG = ITEMS.register("animantarx_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.ANIMANTARX, 0x411d1b,0x411d1b, new Item.Properties()));

    public static final RegistryObject<BucketItem> DAPEDIUM_BUCKET = ITEMS.register("dapedium_bucket", () -> new MobBucketItem(ModEntities.DAPEDIUM, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, new Item.Properties()));
    public static final RegistryObject<MobBucketItem> CLADOCYCLUS_BUCKET = registerMobBucket("cladocyclus_bucket", ModEntities.DAPEDIUM);


    public static RegistryObject<MobBucketItem> registerMobBucket(String name, Supplier<? extends EntityType<?>> fish){
        return registerMobBucket(name, fish, new Item.Properties());
    }
    public static RegistryObject<MobBucketItem> registerMobBucket(String name, Supplier<? extends EntityType<?>> fish, Item.Properties properties){
        return ITEMS.register(name, () -> new MobBucketItem(fish, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, properties));
    }

}
