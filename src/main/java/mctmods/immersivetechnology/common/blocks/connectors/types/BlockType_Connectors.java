package mctmods.immersivetechnology.common.blocks.connectors.types;

import java.util.Locale;

import mctmods.immersivetechnology.common.blocks.BlockITBase;
import net.minecraft.util.IStringSerializable;

public enum BlockType_Connectors implements IStringSerializable, BlockITBase.IBlockEnum {
	CONNECTORS_TIMER;

	@Override
	public int getMeta() {
		return ordinal();
	}

	@Override
	public boolean listForCreative() {
		return true;
	}

	@Override
	public String getName() {
		return this.toString().toLowerCase(Locale.ENGLISH);
	}

}