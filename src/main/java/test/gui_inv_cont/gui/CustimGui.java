package test.gui_inv_cont.gui;

import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import test.Main;
import test.gui_inv_cont.container.*;
import test.gui_inv_cont.inventory.*;

public class CustimGui extends InventoryEffectRenderer {
	public static final int ID = 0;
	public ResourceLocation IMG_GUI = new ResourceLocation(Main.MODID + ":textures/gui/trinkets_inventori.png");
	private float oldMouseX;
    private float oldMouseY;
    
    public CustimGui(EntityPlayer player, InventoryPlayer inventoryPlayer, CustomInventory cInventory) {
        super(new CustomContainer(inventoryPlayer, cInventory, player));
        this.allowUserInput = true;
    }
    
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground(); //темный задник
		super.drawScreen(mouseX, mouseY, partialTicks);
        this.oldMouseX = (float)mouseX;
        this.oldMouseY = (float)mouseY;
    }
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRenderer.drawString(I18n.format("container.crafting"), 97, 8, 4210752);
	}
	
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.pushMatrix();
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		
		this.mc.getTextureManager().bindTexture(GuiInventory.INVENTORY_BACKGROUND);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		this.mc.getTextureManager().bindTexture(IMG_GUI);
		this.drawTexturedModalRect(this.guiLeft +- 53, this.guiTop, 0, 0, 51, 104);
		
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GuiInventory.drawEntityOnScreen(this.guiLeft + 51, this.guiTop + 75, 30, (float) (this.guiLeft + 51) - this.oldMouseX, (float) (this.guiTop + 75 - 50) - this.oldMouseY, this.mc.player);
		GlStateManager.popMatrix();
	}
}
