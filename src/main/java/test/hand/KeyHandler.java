package test.hand;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.client.registry.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.common.gameevent.InputEvent.*;
import test.hand.pack.*;

public class KeyHandler {
	/*������� ����. ��������� �������� - Open Inventory
    ������ �����������, ���� ����� ������ �� ����� ����� H
    �������� ��������� - Custom Inventory Keys*/
	public static KeyBinding openKey = new KeyBinding("Open Inventory", Keyboard.KEY_H, "Custom Inventory Keys");

	//������������ ������� � ����
	public static void register() {
		MinecraftForge.EVENT_BUS.register(new KeyHandler());
		ClientRegistry.registerKeyBinding(openKey);
	}

	//�������, ������� ����������� ��� ������� ������� ������ �� ����������
	@SubscribeEvent
	public void onKey(KeyInputEvent event) {
		//���� ������ �� ���� ������ � �� ���������� ����� �� ������ � �������� ������� ���������
		if (openKey.isPressed()) NetworkHandler.network.sendToServer(new OpenInventoryMessage());
	}
}
