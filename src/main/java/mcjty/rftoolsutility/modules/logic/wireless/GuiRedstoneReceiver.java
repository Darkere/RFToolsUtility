package mcjty.rftoolsutility.modules.logic.wireless;

import mcjty.lib.container.EmptyContainer;
import mcjty.lib.container.GenericContainer;
import mcjty.lib.gui.GenericGuiContainer;
import mcjty.lib.gui.Window;
import mcjty.lib.gui.widgets.ToggleButton;
import mcjty.rftoolsutility.RFToolsUtility;
import mcjty.rftoolsutility.setup.RFToolsUtilityMessages;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;

public class GuiRedstoneReceiver extends GenericGuiContainer<RedstoneReceiverTileEntity, GenericContainer> {

    public GuiRedstoneReceiver(RedstoneReceiverTileEntity te, GenericContainer container, PlayerInventory inventory) {
        super(RFToolsUtility.instance, te, container, inventory, 0, /*@todo 1.15 */"redrec");
    }

    @Override
    public void init() {
        window = new Window(this, tileEntity, RFToolsUtilityMessages.INSTANCE, new ResourceLocation(RFToolsUtility.MODID, "gui/redstone_receiver.gui"));
        super.init();

        initializeFields();
    }

    private void initializeFields() {
        ToggleButton analog = window.findChild("analog");
        analog.setPressed(tileEntity.getAnalog());
    }
}
