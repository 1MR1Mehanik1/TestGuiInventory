package test.gui_inv_cont.container;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import test.gui_inv_cont.container.slot.StandartSlot;
import test.gui_inv_cont.inventory.CustomInventory;

public class CustomContainer extends Container {
	public final InventoryCrafting craftMatrix = new InventoryCrafting(this, 2, 2);
	public final InventoryCraftResult craftResult = new InventoryCraftResult();
	
	private static final EntityEquipmentSlot[] equipmentSlots = new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET};
	private final EntityPlayer _player;
	
	public CustomContainer(InventoryPlayer playerInv, CustomInventory cInv, EntityPlayer player) {
		this._player = player;
        int i;
        int j;
        
        //CustomSlot
        this.addSlotToContainer(new StandartSlot(player, cInv, 0, -45, 8));
        this.addSlotToContainer(new StandartSlot(player, cInv, 1, -26, 8));
        
        this.addSlotToContainer(new StandartSlot(player, cInv, 2, -45, 26));
        this.addSlotToContainer(new StandartSlot(player, cInv, 3, -26, 26));
        
        this.addSlotToContainer(new StandartSlot(player, cInv, 4, -45, 44));
        this.addSlotToContainer(new StandartSlot(player, cInv, 5, -26, 44));
        this.addSlotToContainer(new StandartSlot(player, cInv, 6, -45, 62));
        this.addSlotToContainer(new StandartSlot(player, cInv, 7, -26, 62));
        
        this.addSlotToContainer(new StandartSlot(player, cInv, 8, -45, 80));
        this.addSlotToContainer(new StandartSlot(player, cInv, 9, -26, 80));
        
        //Кравт
        for (i = 0; i < 2; ++i) for (j = 0; j < 2; ++j) this.addSlotToContainer(new Slot(this.craftMatrix, j + i * 2, 98 + j * 18, 18 + i * 18));
        this.addSlotToContainer(new SlotCrafting(playerInv.player, this.craftMatrix, this.craftResult, 0, 154, 28));
        //Броня
        for (i = 0; i < 4; ++i) {
            final EntityEquipmentSlot entityequipmentslot = equipmentSlots[i];
            this.addSlotToContainer(new Slot(playerInv, 36 + (3 - i), 8, 8 + i * 18) {
            	@Override public int getSlotStackLimit() { return 1; }
                @Override public boolean isItemValid(ItemStack stack) { return stack.getItem().isValidArmor(stack, entityequipmentslot, player); }
                @Override public boolean canTakeStack(EntityPlayer player) {
                    ItemStack stack = this.getStack();
                    return !stack.isEmpty() && !player.isCreative() && EnchantmentHelper.hasBindingCurse(stack) ? false : super.canTakeStack(player);
                }
                @Override public String getSlotTexture() { return ItemArmor.EMPTY_SLOT_NAMES[entityequipmentslot.getIndex()]; }
            });
        }
        //Инвентарь
        for (i = 0; i < 3; ++i) for (j = 0; j < 9; ++j) this.addSlotToContainer(new Slot(playerInv, j + (i + 1) * 9, 8 + j * 18, 84 + i * 18));
        
        //Хотбар
        for (i = 0; i < 9; ++i) this.addSlotToContainer(new Slot(playerInv, i, 8 + i * 18, 142));
        
        //Слот щита
        this.addSlotToContainer(new Slot(playerInv, 40, 77, 62) {
        	@Override public boolean isItemValid(ItemStack stack) { return super.isItemValid(stack); }
            @Override public String getSlotTexture() { return "minecraft:items/empty_armor_slot_shield"; }
        });
        this.onCraftMatrixChanged(this.craftMatrix);
	}
	
	@Override public boolean canInteractWith(EntityPlayer playerIn) { return true; }
}
