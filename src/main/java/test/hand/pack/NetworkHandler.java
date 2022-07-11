package test.hand.pack;

import net.minecraftforge.fml.common.network.*;
import net.minecraftforge.fml.common.network.simpleimpl.*;
import net.minecraftforge.fml.relauncher.Side;
import test.Main;

public class NetworkHandler {
	public static SimpleNetworkWrapper network;

    public static void init() {

        //�������������� �����, ��� ���������� ��������� � ���������� ������� ����� �������� � ��������. TestMod.MOD_ID - ���� ����.
        network = NetworkRegistry.INSTANCE.newSimpleChannel(Main.MODID);
        /*    ������������ �����. ���������: ����� �����������(����������� �����, ������� ����� ������ OpenInventoryMessage),
              ����� ������ ���������, �������������, �������, �� ������� ����� �������������� �����.
              ��� ��� �� �������� ��� �� ������, ��� �������� GUI ����� ������, �� ��������� Side.SERVER
        */
        network.registerMessage(OpenInventoryMessage.Handler.class, OpenInventoryMessage.class, 0, Side.SERVER);
    }
}
