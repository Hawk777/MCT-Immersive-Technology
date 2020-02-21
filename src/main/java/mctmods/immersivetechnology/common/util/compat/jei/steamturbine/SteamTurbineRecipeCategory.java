package mctmods.immersivetechnology.common.util.compat.jei.steamturbine;

import mctmods.immersivetechnology.api.crafting.SteamTurbineRecipe;
import mctmods.immersivetechnology.common.ITContent;
import mctmods.immersivetechnology.common.blocks.metal.types.BlockType_MetalMultiblock;
import mctmods.immersivetechnology.common.util.compat.jei.ITRecipeCategory;
import mctmods.immersivetechnology.common.util.compat.jei.JEIHelper;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.*;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class SteamTurbineRecipeCategory extends ITRecipeCategory<SteamTurbineRecipe, SteamTurbineRecipeWrapper> {
	public static ResourceLocation background = new ResourceLocation("immersivetech:textures/gui/gui_steam_turbine.png");
	private final IDrawable tankOverlay;
	private final IDrawableAnimated turbineAndArrow;

	@SuppressWarnings("deprecation")
	public SteamTurbineRecipeCategory(IGuiHelper helper) {
		super("steamTurbine", "tile.immersivetech.metal_multiblock.steam_turbine.name", helper.createDrawable(background, 0, 0, 116, 69), SteamTurbineRecipe.class, new ItemStack(ITContent.blockMetalMultiblock, 1, BlockType_MetalMultiblock.STEAM_TURBINE.getMeta()));
		tankOverlay = helper.createDrawable(background, 118, 2, 16, 47, -2, 2, -2, 2);
		IDrawableStatic staticImage = helper.createDrawable(background, 0, 78, 32, 42);
		this.turbineAndArrow = helper.createAnimatedDrawable(staticImage, 200, IDrawableAnimated.StartDirection.LEFT, false);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, SteamTurbineRecipeWrapper recipeWrapper, IIngredients ingredients) {
		List<List<FluidStack>> inputs = ingredients.getInputs(FluidStack.class);
		List<List<FluidStack>> outputs = ingredients.getOutputs(FluidStack.class);

		int tankSize = 0;
		for (List<FluidStack> lists : inputs) {
			for (FluidStack fluid : lists) if (fluid.amount > tankSize) tankSize = fluid.amount;
		}
		for (List<FluidStack> lists : outputs) {
			for (FluidStack fluid : lists) if (fluid.amount > tankSize) tankSize = fluid.amount;
		}

		IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();
		guiFluidStacks.init(0, true, 11, 11, 16, 47, tankSize, true, tankOverlay);
		guiFluidStacks.set(0, inputs.get(0));
		if(outputs.get(0) != null) {
			guiFluidStacks.init(1, false, 89, 11, 16, 47, tankSize, true, tankOverlay);
			guiFluidStacks.set(1, outputs.get(0));
		}
		guiFluidStacks.addTooltipCallback(JEIHelper.fluidTooltipCallback);
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(SteamTurbineRecipe recipe) {
		return new SteamTurbineRecipeWrapper(recipe);
	}
	
	@Override
	public void drawExtras(Minecraft minecraft) {
		turbineAndArrow.draw(minecraft, 42, 18);
	}

}