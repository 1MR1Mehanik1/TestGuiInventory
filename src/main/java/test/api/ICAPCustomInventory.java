package test.api;

import test.gui_inv_cont.inventory.CustomInventory;

public interface ICAPCustomInventory {
	public void copyInventory(ICAPCustomInventory inventory);
    public CustomInventory getInventory();
}
