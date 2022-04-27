package fr.jeje.gamamod.block.entity;

import fr.jeje.gamamod.GamaMod;
import fr.jeje.gamamod.block.ModBlocks;
import fr.jeje.gamamod.block.entity.custom.SkateboardAssemblerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities  {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, GamaMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<SkateboardAssemblerBlockEntity>> SKATEBOARD_ASSEMBLER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("skateboard_assembler_block_entity", () ->
                    BlockEntityType.Builder.of(SkateboardAssemblerBlockEntity::new,
                            ModBlocks.SKATEBOARD_ASSEMBLER.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
