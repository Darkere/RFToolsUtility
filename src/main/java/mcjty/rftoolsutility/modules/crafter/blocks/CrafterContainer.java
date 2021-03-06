package mcjty.rftoolsutility.modules.crafter.blocks;

import mcjty.lib.container.*;
import mcjty.lib.tileentity.GenericTileEntity;
import mcjty.lib.varia.ItemStackList;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

import static mcjty.rftoolsutility.modules.crafter.CrafterSetup.CONTAINER_CRAFTER;

public class CrafterContainer extends GenericContainer {

    public static final int SLOT_CRAFTINPUT = 0;
    public static final int SLOT_CRAFTOUTPUT = 9;
    public static final int SLOT_BUFFER = 10;
    public static final int BUFFER_SIZE = (13*2);
    public static final int SLOT_BUFFEROUT = SLOT_BUFFER + BUFFER_SIZE;
    public static final int BUFFEROUT_SIZE = 4;
    public static final int SLOT_FILTER_MODULE = SLOT_BUFFEROUT + BUFFEROUT_SIZE;

    public static final ContainerFactory CONTAINER_FACTORY = new ContainerFactory(10 + BUFFER_SIZE + BUFFEROUT_SIZE + 1) {
        @Override
        protected void setup() {
            box(SlotDefinition.ghost(), CONTAINER_CONTAINER, SLOT_CRAFTINPUT, 193, 7, 3, 3);
            slot(SlotDefinition.ghostOut(), CONTAINER_CONTAINER, SLOT_CRAFTOUTPUT, 193, 65);
            box(SlotDefinition.input(), CONTAINER_CONTAINER, SLOT_BUFFER, 13, 97, 13, 2);
            box(SlotDefinition.output(), CONTAINER_CONTAINER, SLOT_BUFFEROUT, 31, 142, 2, 2);
            slot(SlotDefinition.specific(ItemStack.EMPTY /* @todo 1.14 filter_module) */), CONTAINER_CONTAINER, SLOT_FILTER_MODULE, 157, 43);
            playerSlots(85, 142);
        }
    };


    public CrafterContainer(int id, ContainerFactory factory, BlockPos pos, @Nullable GenericTileEntity te) {
        super(CONTAINER_CRAFTER.get(), id, factory, pos, te);
//        generateSlots();
    }

    @Override
    protected Slot createSlot(SlotFactory slotFactory, IItemHandler inventory, int index, int x, int y, SlotType slotType) {
        CrafterBaseTE c = (CrafterBaseTE) te;
        if (index >= SLOT_BUFFER && index < SLOT_BUFFEROUT && slotType == SlotType.SLOT_INPUT) {
            return new BaseSlot(inventory, te, index, x, y) {
                @Override
                public boolean isItemValid(ItemStack stack) {
                    if (!c.isItemValidForSlot(getSlotIndex(), stack)) {
                        return false;
                    }
                    return super.isItemValid(stack);
                }

                @Override
                public void onSlotChanged() {
                    c.noRecipesWork = false;
                    super.onSlotChanged();
                }
            };
        } else if (index >= SLOT_BUFFEROUT && index < SLOT_FILTER_MODULE) {
            return new BaseSlot(inventory, te, index, x, y) {
                @Override
                public boolean isItemValid(ItemStack stack) {
                    if (!c.isItemValidForSlot(getSlotIndex(), stack)) {
                        return false;
                    }
                    return super.isItemValid(stack);
                }

                @Override
                public void onSlotChanged() {
                    c.noRecipesWork = false;
                    super.onSlotChanged();
                }
            };
        }
        return super.createSlot(slotFactory, inventory, index, x, y, slotType);
    }

    @Override
    public ItemStack slotClick(int index, int button, ClickType mode, PlayerEntity player) {
        // Allow replacing input slot ghost items by shift-clicking.
        if (mode == ClickType.QUICK_MOVE &&
            index >= CrafterContainer.SLOT_BUFFER &&
            index < CrafterContainer.SLOT_BUFFEROUT) {

            CrafterBaseTE c = (CrafterBaseTE) te;

            int offset = index - CrafterContainer.SLOT_BUFFER;
            ItemStackList ghostSlots = c.getGhostSlots();
            ItemStack ghostSlot = ghostSlots.get(offset);
            ItemStack clickedWith = player.inventory.getItemStack();
            if (!ghostSlot.isEmpty() && !ghostSlot.isItemEqual(clickedWith)) {
                ItemStack copy = clickedWith.copy();
                copy.setCount(1);
                ghostSlots.set(offset, copy);
                detectAndSendChanges();
                return ItemStack.EMPTY;
            }
        }

        return super.slotClick(index, button, mode, player);
    }
}
