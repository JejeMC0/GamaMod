package fr.jeje.gamamod.block.entity.custom;

import fr.jeje.gamamod.block.entity.ModBlockEntities;
import fr.jeje.gamamod.item.ModItems;
import fr.jeje.gamamod.screen.SkateboardAssemblerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;


public class SkateboardAssemblerBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(12) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public SkateboardAssemblerBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.SKATEBOARD_ASSEMBLER_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Skateboard Assembler");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new SkateboardAssemblerMenu(pContainerId, pInventory, this);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }


    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, SkateboardAssemblerBlockEntity pBlockEntity) {
        if(hasRecipe(pBlockEntity) && hasNotReachedStackLimit(pBlockEntity)) {
            craftItem(pBlockEntity);
        }
    }

    private static void craftItem(SkateboardAssemblerBlockEntity entity) {
        entity.itemHandler.extractItem(0, 1, false);
        entity.itemHandler.extractItem(1, 1, false);
        entity.itemHandler.extractItem(2, 1, false);
        entity.itemHandler.extractItem(3, 1, false);
        entity.itemHandler.extractItem(4, 1, false);
        entity.itemHandler.extractItem(5, 1, false);
        entity.itemHandler.extractItem(6, 1, false);
        entity.itemHandler.extractItem(7, 1, false);
        entity.itemHandler.extractItem(8, 1, false);
        entity.itemHandler.extractItem(9, 1, false);
        entity.itemHandler.extractItem(10, 1, false);

        entity.itemHandler.setStackInSlot(11, new ItemStack(ModItems.SKATEBOARD.get(),
                entity.itemHandler.getStackInSlot(11).getCount() + 1));
    }

    private static boolean hasRecipe(SkateboardAssemblerBlockEntity entity) {
        boolean hasItemInWheel0Slot = entity.itemHandler.getStackInSlot(0).getItem() == ModItems.WHEEL.get();
        boolean hasItemInWheel1Slot = entity.itemHandler.getStackInSlot(1).getItem() == ModItems.WHEEL.get();
        boolean hasItemInWheel2Slot = entity.itemHandler.getStackInSlot(2).getItem() == ModItems.WHEEL.get();
        boolean hasItemInWheel3Slot = entity.itemHandler.getStackInSlot(3).getItem() == ModItems.WHEEL.get();

        boolean hasItemInTrucks0Slot = entity.itemHandler.getStackInSlot(4).getItem() == ModItems.TRUCKS.get();
        boolean hasItemInTrucks1Slot = entity.itemHandler.getStackInSlot(5).getItem() == ModItems.TRUCKS.get();

        boolean hasItemInScrew1Slot = entity.itemHandler.getStackInSlot(6).getItem() == ModItems.SCREW.get();
        boolean hasItemInScrew2Slot = entity.itemHandler.getStackInSlot(7).getItem() == ModItems.SCREW.get();
        boolean hasItemInScrew3Slot = entity.itemHandler.getStackInSlot(8).getItem() == ModItems.SCREW.get();
        boolean hasItemInScrew4Slot = entity.itemHandler.getStackInSlot(9).getItem() == ModItems.SCREW.get();


        boolean hasItemInDeckSlot = entity.itemHandler.getStackInSlot(10).getItem() == ModItems.SKATEBOARD_DECK.get();

        return hasItemInWheel0Slot && hasItemInWheel1Slot && hasItemInWheel2Slot && hasItemInWheel3Slot
                && hasItemInTrucks0Slot && hasItemInTrucks1Slot
                && hasItemInScrew1Slot && hasItemInScrew2Slot && hasItemInScrew3Slot && hasItemInScrew4Slot
                && hasItemInDeckSlot;
    }

    private static boolean hasNotReachedStackLimit(SkateboardAssemblerBlockEntity entity) {
        return entity.itemHandler.getStackInSlot(11).getCount() < entity.itemHandler.getStackInSlot(11).getMaxStackSize();
    }
}