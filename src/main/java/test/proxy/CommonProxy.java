package test.proxy;

import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import test.Main;
import test.api.*;
import test.event.*;
import test.hand.*;
import test.hand.pack.GuiHandler;
import test.hand.pack.NetworkHandler;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		NetworkHandler.init();
	}

	public void init(FMLInitializationEvent event) {
		CapabilityManager.INSTANCE.register(ICAPCustomInventory.class, new CAPCustomInventoryStorage(), CAPCustomInventory.class);
		CapabilityEventHandler.register();
		
		KeyHandler.register();
		
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
	}

	public void postInit(FMLPostInitializationEvent event) {}

}
