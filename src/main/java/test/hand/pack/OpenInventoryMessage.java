package test.hand.pack;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.*;
import test.Main;
import test.gui_inv_cont.inventory.CustomInventory;

public class OpenInventoryMessage implements IMessage {
	//Наша задача просто открыть GUI с серверной стороны, ничего больше передавать не нужно.

    //ОБЯЗАТЕЛЬНЫЙ конструктор без параметров
    public OpenInventoryMessage() { }

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }


    //Класс-обработчик пакета. Реализует метод onMessage, который срабатывает когда пакет приходит на сервер(в данном случае)
    public static class Handler implements IMessageHandler<OpenInventoryMessage, IMessage> {

        //Пишем тут то, что должно произойти когда пакет дойдет до сервера
        @Override
        public IMessage onMessage(OpenInventoryMessage message, MessageContext ctx) {
            /*В данном случае достаем игрока, который отправил пакет и открываем ГУИ.
               TestMod.INSTANCE - инстанс главного класса, т.е. его обьект.
               Допустим имя главного класса TestMod, а modid = "testmod", тогда в главном классе пишем: @Mod.Instance("testmod") public static TestMod INSTANCE;
               GuiHandler.INVENTORY_GUI_ID - идентификатор нашего ГУИ. Я присвоил значение 0.
            */
            EntityPlayerMP player = ctx.getServerHandler().player;
            player.openGui(Main.instance, CustomInventory.INV_SIZE, player.getEntityWorld(), (int)player.posX, (int)player.posY, (int)player.posZ);
            return null;
        }
    }
}
