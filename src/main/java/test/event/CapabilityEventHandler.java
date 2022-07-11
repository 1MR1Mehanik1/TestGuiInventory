package test.event;

import net.minecraft.entity.player.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.*;
import net.minecraftforge.event.*;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import test.Main;
import test.api.*;
import test.gui_inv_cont.inventory.CustomInventory;

public class CapabilityEventHandler {
	public static void register(){
        MinecraftForge.EVENT_BUS.register(new CapabilityEventHandler());
    }

    public static final ResourceLocation INVENTORY_CAP = new ResourceLocation(Main.MODID, "inventory");

    //����� �����! ��������� ���� ������ ��� ��� ������ ��������

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent event) {
        if (event.getObject() instanceof EntityPlayer) event.addCapability(INVENTORY_CAP, new CAPCustomInventoryProvider());
    }
    
    //����������� ���������, ���� �� �����-�� �������� ��������� ������������ ������. ����� ���� ��������
    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event) {
        EntityPlayer player = event.getEntityPlayer();
        ICAPCustomInventory newCap = player.getCapability(CAPCustomInventoryProvider.INVENTORY_CAP, null);
        ICAPCustomInventory oldCap = event.getOriginal().getCapability(CAPCustomInventoryProvider.INVENTORY_CAP, null);
        newCap.copyInventory(oldCap);
    }

    //���� ����� ����� �� ������ �� �������. ����� �������� ���� �������. �����������
    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event) {
        if(event.getEntity() instanceof EntityPlayer) {
            //������� ����, ����� ���������
            EntityPlayer player = (EntityPlayer)event.getEntity();
            ICAPCustomInventory cap = player.getCapability(CAPCustomInventoryProvider.INVENTORY_CAP, null);
            CustomInventory inv = cap.getInventory();
            //����������� ��� ���� �� ���������
            dropAllItems(player, inv);
            inv.clear();
        }
    }

    private static void dropAllItems(EntityPlayer player, CustomInventory inventory){
        NonNullList<ItemStack> aitemstack = inventory.getStacks();
        for (int i = 0; i < aitemstack.size(); ++i) {
            if (!aitemstack.get(i).isEmpty()) {
                player.dropItem(aitemstack.get(i), true, false);
            }
        }
    }
}
