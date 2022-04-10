package fr.jeje.gamamod.CreativeTab;

import fr.jeje.gamamod.item.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab SKATEBOARD_TAB = new CreativeModeTab("skateboard_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SKATEBOARD.get());
        }
    };

}
