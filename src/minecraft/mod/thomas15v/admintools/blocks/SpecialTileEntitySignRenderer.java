package mod.thomas15v.admintools.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelSign;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraftforge.client.ForgeHooksClient;

import org.lwjgl.opengl.GL11;

public class SpecialTileEntitySignRenderer extends TileEntitySpecialRenderer {

	//Begin New code
	private RenderItem itemrender;
	private ItemStack test;
		
	public SpecialTileEntitySignRenderer(){
		itemrender = new RenderItem();
		itemrender.setRenderManager(RenderManager.instance);
		test = new ItemStack(Block.enchantmentTable);
		
	}
	
	//end new code
	
	//begin minecraft code
	private ModelSign modelSign = new ModelSign();

    public void renderTileEntitySignAt(TileEntitySign TileEntity, double par2, double par4, double par6, float par8)
    {
        Block var9 = TileEntity.getBlockType();
        GL11.glPushMatrix();
        float var10 = 0.6666667F;
        float var12;

        if (var9 == Block.signPost)
        {
            GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 0.75F * var10, (float)par6 + 0.5F);
            float var11 = (float)(TileEntity.getBlockMetadata() * 360) / 16.0F;
            GL11.glRotatef(-var11, 0.0F, 1.0F, 0.0F);
            this.modelSign.signStick.showModel = true;
        }
        else
        {
            int var16 = TileEntity.getBlockMetadata();
            var12 = 0.0F;

            if (var16 == 2)
            {
                var12 = 180.0F;
            }

            if (var16 == 4)
            {
                var12 = 90.0F;
            }

            if (var16 == 5)
            {
                var12 = -90.0F;
            }

            GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 0.75F * var10, (float)par6 + 0.5F);
            GL11.glRotatef(-var12, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(0.0F, -0.3125F, -0.4375F);
            this.modelSign.signStick.showModel = false;
        }

        this.bindTextureByName("/item/sign.png");
        GL11.glPushMatrix();
        GL11.glScalef(var10, -var10, -var10);
        this.modelSign.renderSign();
        GL11.glPopMatrix();
        FontRenderer fontRenderer = this.getFontRenderer();
        var12 = 0.016666668F * var10;
        GL11.glTranslatef(0.0F, 0.5F * var10, 0.07F * var10);
        GL11.glScalef(var12, -var12, var12);
        GL11.glNormal3f(0.0F, 0.0F, -1.0F * var12);
        GL11.glDepthMask(false);
        byte var13 = 0;
        
        //end minecraft code
        //begin code
        
        for (int i = 0; i < TileEntity.signText.length; ++i)
        {
            String currentstring = TileEntity.signText[i];
            
            if (i == TileEntity.lineBeingEdited)
            {
            	currentstring = "> " + currentstring + " <";
                fontRenderer.drawString(currentstring, -fontRenderer.getStringWidth(currentstring) / 2, i * 10 - TileEntity.signText.length * 5, var13);
            }
            else
            {
            	fontRenderer.drawString(currentstring, -fontRenderer.getStringWidth(currentstring) / 2, i * 10 - TileEntity.signText.length * 5, var13);
            }
        }
        
        Minecraft mc = Minecraft.getMinecraft();
        itemrender.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, test, 0, 0);
        
        //itemrender.renderItemIntoGUI(mc.fontRenderer, mc.renderEngine, test, 0, 0);
        
        
       
        //end code

        GL11.glDepthMask(true);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
    }

    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        this.renderTileEntitySignAt((TileEntitySign)par1TileEntity, par2, par4, par6, par8);
    }
}

