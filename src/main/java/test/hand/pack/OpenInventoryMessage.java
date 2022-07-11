package test.hand.pack;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.*;
import test.Main;
import test.gui_inv_cont.inventory.CustomInventory;

public class OpenInventoryMessage implements IMessage {
	//���� ������ ������ ������� GUI � ��������� �������, ������ ������ ���������� �� �����.

    //������������ ����������� ��� ����������
    public OpenInventoryMessage() { }

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }


    //�����-���������� ������. ��������� ����� onMessage, ������� ����������� ����� ����� �������� �� ������(� ������ ������)
    public static class Handler implements IMessageHandler<OpenInventoryMessage, IMessage> {

        //����� ��� ��, ��� ������ ��������� ����� ����� ������ �� �������
        @Override
        public IMessage onMessage(OpenInventoryMessage message, MessageContext ctx) {
            /*� ������ ������ ������� ������, ������� �������� ����� � ��������� ���.
               TestMod.INSTANCE - ������� �������� ������, �.�. ��� ������.
               �������� ��� �������� ������ TestMod, � modid = "testmod", ����� � ������� ������ �����: @Mod.Instance("testmod") public static TestMod INSTANCE;
               GuiHandler.INVENTORY_GUI_ID - ������������� ������ ���. � �������� �������� 0.
            */
            EntityPlayerMP player = ctx.getServerHandler().player;
            player.openGui(Main.instance, CustomInventory.INV_SIZE, player.getEntityWorld(), (int)player.posX, (int)player.posY, (int)player.posZ);
            return null;
        }
    }
}
