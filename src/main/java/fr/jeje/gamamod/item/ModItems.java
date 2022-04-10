package fr.jeje.gamamod.item;


import fr.jeje.gamamod.CreativeTab.ModCreativeModeTab;
import fr.jeje.gamamod.GamaMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, GamaMod.MOD_ID);

    //skateboard
    public static final RegistryObject<Item> SKATEBOARD = ITEMS.register("skateboard",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.SKATEBOARD_TAB).stacksTo(1)));
    //skateboard deck
    public static final RegistryObject<Item> SKATEBOARD_DECK = ITEMS.register("skateboard_deck",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.SKATEBOARD_TAB).stacksTo(1)));

    //wheel
    public static final RegistryObject<Item> WHEEL = ITEMS.register("wheel",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.SKATEBOARD_TAB).stacksTo(16)));

    //trucks
    public static final RegistryObject<Item> TRUCKS = ITEMS.register("trucks",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.SKATEBOARD_TAB).stacksTo(8)));

    //screw
    public static final RegistryObject<Item> SCREW = ITEMS.register("screw",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.SKATEBOARD_TAB).stacksTo(32)));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}