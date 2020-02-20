package mctmods.immersivetechnology.common.gui;

import blusunrize.immersiveengineering.common.gui.ContainerIEBase;
import blusunrize.immersiveengineering.common.gui.IESlot;
import mctmods.immersivetechnology.common.blocks.metal.tileentities.TileEntitySolarTowerMaster;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class ContainerSolarTower extends ContainerIEBase<TileEntitySolarTowerMaster> {
	public ContainerSolarTower(InventoryPlayer inventoryPlayer, TileEntitySolarTowerMaster tile) {
		super(inventoryPlayer, tile);

		final TileEntitySolarTowerMaster tileF = tile;
		this.addSlotToContainer(new IESlot.FluidContainer(this, this.inv, 0, 80, 17, 2) {
			@Override
			public boolean isItemValid(ItemStack itemStack) {
				IFluidHandler h = itemStack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
				if(h == null || h.getTankProperties().length == 0) return false;
				FluidStack fs = h.getTankProperties()[0].getContents();
				if(fs == null) return false;
				if(tileF.tanks[0].getFluidAmount() > 0 && !fs.isFluidEqual(tileF.tanks[0].getFluid())) return false;
				return true;
			}
		});
		this.addSlotToContainer(new IESlot.Output(this, this.inv, 1, 80, 53));
		this.addSlotToContainer(new IESlot.FluidContainer(this, this.inv, 2, 148, 17, 0) {
			@Override
			public boolean isItemValid(ItemStack itemStack) {
				return super.isItemValid(itemStack) || (itemStack != null && itemStack.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null));
			}
		});
		this.addSlotToContainer(new IESlot.Output(this, this.inv, 3, 148, 53));
		slotCount=4;

		for(int i = 0 ; i < 3 ; i++) {
			for(int j = 0 ; j < 9 ; j++) {
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 85 + i * 18));
			}
		}
		for(int i = 0 ; i < 9 ; i++) addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 143));
	}

}