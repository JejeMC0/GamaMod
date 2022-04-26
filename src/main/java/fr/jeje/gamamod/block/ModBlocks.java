package fr.jeje.gamamod.block;

import fr.jeje.gamamod.CreativeTab.ModCreativeModeTab;
import fr.jeje.gamamod.GamaMod;
import fr.jeje.gamamod.block.custom.Asphalt_block;
import fr.jeje.gamamod.block.custom.Skateboard_assembler;
import fr.jeje.gamamod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, GamaMod.MOD_ID);


    public static final RegistryObject<Block> SKATEBOARD_ASSEMBLER = registerBlock("skateboard_assembler",
            () -> new Skateboard_assembler(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(7f)),
            ModCreativeModeTab.SKATEBOARD_TAB);

    public static final RegistryObject<Block> ASPHALT_BLOCK = registerBlock("asphalt_block",
            () -> new Asphalt_block(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion().strength(5f)),
            ModCreativeModeTab.SKATEBOARD_TAB);





    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;

    }


    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                           CreativeModeTab tab){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));

    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);


    }
}
