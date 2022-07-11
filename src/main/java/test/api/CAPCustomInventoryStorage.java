package test.api;

import net.minecraft.nbt.*;
import net.minecraft.util.*;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.capabilities.Capability.*;

public class CAPCustomInventoryStorage implements IStorage<ICAPCustomInventory> {
	//���������� ���������� (�.�. ���������, ��� ������ � ���������) � ������� writeToNBT

    @Override
    public NBTBase writeNBT(Capability<ICAPCustomInventory> capability, ICAPCustomInventory instance, EnumFacing side) {
        NBTTagCompound properties = new NBTTagCompound();
        //�������� ����� writeToNBT �� ��������� � ���������� ���� � ��������� � ��� ������� � ���
        instance.getInventory().writeToNBT(properties);
        return properties;
    }

    //������ ���������� (�.�. ���������, ��� ������ � ���������) � ���������� �� � ��������� � ������� readFromNBT
    @Override
    public void readNBT(Capability<ICAPCustomInventory> capability, ICAPCustomInventory instance, EnumFacing side, NBTBase nbt) {
        NBTTagCompound properties = (NBTTagCompound)nbt;
        //�������� ����� readFromNBT �� ��������� � ������ � ����(���.) ����� ������� ��������� � ����
        instance.getInventory().readFromNBT(properties);
    }
}
