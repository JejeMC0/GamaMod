package fr.jeje.gamamod;


import com.mojang.logging.LogUtils;
import fr.jeje.gamamod.block.ModBlocks;
import fr.jeje.gamamod.block.entity.ModBlockEntities;
import fr.jeje.gamamod.item.ModItems;
import fr.jeje.gamamod.screen.ModMenuTypes;
import fr.jeje.gamamod.screen.SkateboardAssemblerScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(GamaMod.MOD_ID)
public class GamaMod {

    public static final String MOD_ID = "gamamod";

    private static final Logger LOGGER = LogUtils.getLogger();

    public GamaMod() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);

        ModBlockEntities.register(eventBus);
        ModMenuTypes.register(eventBus);



        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }


    private void clientSetup(final FMLCommonSetupEvent event){
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SKATEBOARD_ASSEMBLER.get(), RenderType.cutout());


        MenuScreens.register(ModMenuTypes.SKATEBOARD_ASSEMBLER_MENU.get(), SkateboardAssemblerScreen::new);


    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}