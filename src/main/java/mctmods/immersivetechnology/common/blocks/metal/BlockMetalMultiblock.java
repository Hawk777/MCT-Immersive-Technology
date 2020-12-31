package mctmods.immersivetechnology.common.blocks.metal;

import mctmods.immersivetechnology.api.ITProperties;
import mctmods.immersivetechnology.common.blocks.BlockITMultiblock;
import mctmods.immersivetechnology.common.blocks.ItemBlockITBase;
import mctmods.immersivetechnology.common.blocks.metal.types.BlockType_MetalMultiblock;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.world.World;

public class BlockMetalMultiblock extends BlockITMultiblock<BlockType_MetalMultiblock> {
	public BlockMetalMultiblock() {
		super("metal_multiblock", Material.IRON, PropertyEnum.create("type", BlockType_MetalMultiblock.class), ItemBlockITBase.class, ITProperties.RENDER);
		setHardness(3.0F);
		setResistance(15.0F);
		//this.setMetaBlockLayer(BlockType_MetalMultiblock.STEEL_TANK.getMeta(), BlockRenderLayer.CUTOUT);
		this.setAllNotNormalBlock();
		lightOpacity = 0;
	}

	@Override
	public boolean allowHammerHarvest(IBlockState state) {
		return true;
	}

	@Override
	public TileEntity createBasicTE(World worldIn, BlockType_MetalMultiblock type) {
		return type.createTE();
	}
}