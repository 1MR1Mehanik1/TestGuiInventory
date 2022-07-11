package test.hand.pack;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import test.api.*;
import test.gui_inv_cont.container.CustomContainer;
import test.gui_inv_cont.gui.CustimGui;
import test.gui_inv_cont.inventory.CustomInventory;

public class GuiHandler implements IGuiHandler {
	@Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        //Обьект инвентаря храниться в КАПе, значит будем брать его из нее
        ICAPCustomInventory inv = player.getCapability(CAPCustomInventoryProvider.INVENTORY_CAP, null);
        if(ID == CustomInventory.INV_SIZE) {
            return new CustomContainer(player.inventory, inv.getInventory(), player);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        //Обьект инвентаря храниться в КАПе, значит будем брать его из нее
        ICAPCustomInventory inv = player.getCapability(CAPCustomInventoryProvider.INVENTORY_CAP, null);
        if(ID == CustomInventory.INV_SIZE) {
            return new CustimGui(player, player.inventory, inv.getInventory());
        }
        return null;
    }
}
