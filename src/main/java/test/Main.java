package test;

import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.event.*;
import test.proxy.CommonProxy;

@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION, acceptedMinecraftVersions = Main.MC_VERSION)
public class Main {
	public static final String MODID = "test";
	public static final String NAME = "Test";
	public static final String VERSION = "0.1";
	public static final String MC_VERSION = "[1.12.2]";
	
	public static final String CLIENT = MODID + ".proxy.ClientProxy";
	public static final String SERVER = MODID + ".proxy.CommonProxy";
	
	@Instance
	public static Main instance;
	@SidedProxy(clientSide = CLIENT, serverSide = SERVER)
	public static CommonProxy proxy;
	
	@EventHandler public static void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}
	@EventHandler public static void init(FMLInitializationEvent event) {
		proxy.init(event);
	}
	@EventHandler public static void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
}
