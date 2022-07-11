package test.gui_inv_cont.container.slot;

import javax.annotation.*;

import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;

public class StandartSlot extends Slot {
	//��� �� ���� ������ �������� ���������� �����

    private final EntityPlayer thePlayer;
    private int removeCount;

    public StandartSlot(EntityPlayer player, IInventory inventoryIn, int slotIndex, int xPosition, int yPosition){
        super(inventoryIn, slotIndex, xPosition, yPosition);
        this.thePlayer = player;
    }

    /* ����� �� ����� ���� ���� ������� � ���� ����. ����� ����� ���� ��������, �������� ���� �� ������
        ���� � ���� ������ ���� �������� ������, ���������� ����� �� ������� � ����� ������, ���� ��, ��
        ���������� false */
    public boolean isItemValid(@Nullable ItemStack stack){
        return true;
    }

    public ItemStack decrStackSize(int amount){
        if (this.getHasStack()){
            this.removeCount += Math.min(amount, this.getStack().getCount());
        }
        return super.decrStackSize(amount);
    }

    //��� ����������, ���� ������� ������� �� �����
    public ItemStack onTake(EntityPlayer player, ItemStack stack){
        this.onCrafting(stack);
        super.onTake(player, stack);
        return stack;
    }

    protected void onCrafting(ItemStack stack, int amount){
        this.removeCount += amount;
        this.onCrafting(stack);
    }

    protected void onCrafting(ItemStack stack){
        stack.onCrafting(this.thePlayer.world, this.thePlayer, this.removeCount);
        this.removeCount = 0;
    }
}
