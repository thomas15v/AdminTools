package mod.thomas15v.admintools;

import java.util.HashMap;
import java.util.Map;

import javax.management.loading.PrivateClassLoader;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraftforge.client.ForgeHooksClient;

public class ShopSign
{
    String username;
    String[] Price = new String[2];
    ItemStack item;

    double NO_PRICE = -1;
    double FREE = 0;

    String FREE_TEXT = "free";

    char BUY_INDICATOR = 'b';
    char SELL_INDICATOR = 's';

    private RenderItem itemrender;

    Minecraft mc = Minecraft.getMinecraft();

    TileEntitySign sign;

    public ShopSign(TileEntitySign sign)
    {
        this.sign = sign;
        itemrender = new RenderItem();
        itemrender.setRenderManager(RenderManager.instance);
        this.username = sign.signText[0];
        this.item = GetStackFromName(sign.signText[3], Integer.parseInt(sign.signText[1]));
        for (int i = 0; i < 2 ;i++)
        	Price[i] = GetPrice(sign.signText[2], i);
        	
        
    }

    public static ItemStack GetStackFromName(String name)
    {
        return GetStackFromName(name, 1);
    }

    public static ItemStack GetStackFromName(String name, int amount)
    {
		try{
	    	if (name.contains(":")){
	    		int id = Integer.parseInt(name.replace("X", "").split(":")[0]);
	    		int subid = Integer.parseInt(name.replace("X", "").split(":")[1]);
	    		return new ItemStack(id, amount, subid);
	    	}
		}
		catch(Exception e){}
		
        return null;
    }

    private void handleRenderItem(ItemStack is)
    {
        //Credits to neptunepink creator of factorization, this code is used in the barrel of his mod.
        GL11.glPushMatrix();
        GL11.glTranslatef(-45F, -10F, 0F);
        GL11.glScalef(1.5F, 1.5F, 0.01F);
        itemrender.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, is, 0, 0);
        GL11.glPopMatrix();
    }

    public void render()
    {
        handleRenderItem(item);
        mc.fontRenderer.drawString(this.username, -mc.fontRenderer.getStringWidth(this.username) / 2, -20, 0x0000000);
        
        int i = 0;
        for (String Printprice : Price){
        	if (Printprice != null){
        		mc.fontRenderer.drawString(Printprice + "", -20, -10 +10*i, 0x0000000);
        		i++;
        	}
        }
        	
        
        mc.fontRenderer.drawString(this.item.stackSize + "", -20, 8, 0x111111);
    }

    private String GetPrice(String text, int i)
    {
    	char indicator;
    	if (i == 0)
    		indicator = BUY_INDICATOR;
    	else
    		indicator = SELL_INDICATOR;
    		
    	 admintools.notifications.ShowNotification(indicator + " ");
        String[] split = text.replace(" ", "").toLowerCase().split(":");
        String character = String.valueOf(indicator).toLowerCase();

        for (String part : split)
        {
            if (!part.contains(character))
            {
                continue;
            }

            part = part.replace(character, "");

            if (part.equals(FREE_TEXT))
            {
            	if (i == 0)
            		return "Buy: Free";
            	else
            		return "Sell: Free";
            }

            if (isDouble(part))
            {
                double price = Double.valueOf(part);

                if (price <= 0)
                {
                    return null;
                }
                else
                {
                	if (i == 0)
                		return "Buy: " + price;
                	else
                		return "Sell: " + price;
                }
            }
        }
        return null;
    }

    private boolean isDouble(String string)
    {
        try
        {
            Double.parseDouble(string);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }

    public String getUsername()
    {
        return username;
    }

    public ItemStack getItem()
    {
        return item;
    }
}
