package com.bretzelfresser.ornithodira.core.init;

import com.bretzelfresser.ornithodira.Ornithodira;
import com.bretzelfresser.ornithodira.common.blockentity.ScannerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Ornithodira.MODID);



    public static final RegistryObject<BlockEntityType<ScannerBlockEntity>> SCANNER = BLOCK_ENTITIES.register("scanner", () -> BlockEntityType.Builder.of(ScannerBlockEntity::new, ModBlocks.SCANNER_1.get()).build(null));
}
