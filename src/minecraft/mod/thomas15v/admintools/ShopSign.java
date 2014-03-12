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

public class ShopSign {
	
	static public Map<String, Item> NameList = new HashMap<String, Item>();
	
	String username;
	int buyPrice;
	int sellPrice;
	ItemStack item;
	
    double NO_PRICE = -1;
    double FREE = 0;

    String FREE_TEXT = "free";

    char BUY_INDICATOR = 'b';
    char SELL_INDICATOR = 's';
    
    private RenderItem itemrender;
    
    Minecraft mc = Minecraft.getMinecraft();    
    
   
    
    TileEntitySign sign;
	
    private ShopSign(String username, int buyPrice, int sellPrice, ItemStack item ){
			this.username = username;
			this.buyPrice = buyPrice;
			this.sellPrice = sellPrice;
			this.item = item;
	}
	
	public ShopSign(TileEntitySign sign){
		this.sign = sign;
		itemrender = new RenderItem();
		itemrender.setRenderManager(RenderManager.instance);
		
		this.username = sign.signText[0];
		this.item = GetStackFromName(sign.signText[3], Integer.parseInt(sign.signText[1]));
		this.buyPrice = 0;
		this.sellPrice = 0;	
	}
	
	public static ItemStack GetStackFromName(String name) {
		return GetStackFromName(name, 1);
	}
	
	public static ItemStack GetStackFromName(String name, int amount) {
			
			if (NameList.containsKey(name)){
				if (NameList.get(name) == null) return null;
				return new ItemStack(NameList.get(name), 1);
			}
			else{
				for (Item item : Item.itemsList){
					if (item != null){
						try{
							ItemStack itemstack = new ItemStack(item, amount);
							String displayname = itemstack.getDisplayName();
							//String displayname = item.getu
							if (displayname!= null && displayname.equalsIgnoreCase(name)){
								NameList.put(name, item);
								return itemstack;
							}
						}
						catch (Exception e){
							e.printStackTrace();
						}
					}
				}
			}
			NameList.put(name, null);
			return null;
		}
	
	private void handleRenderItem(ItemStack is)
	{
    	//Credits to neptunepink creator of factorization, this code is used in the barrel of his mod.
    	GL11.glPushMatrix();
    	GL11.glTranslatef(-40F,-10F,0F);
    	GL11.glScalef(1.5F, 1.5F, 0.01F);
    	itemrender.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, is, 0, 0);
    	GL11.glPopMatrix();
    }
	
	 public void render(){
		handleRenderItem(item);
		mc.fontRenderer.drawString(this.username, -mc.fontRenderer.getStringWidth(this.username) / 2,-20, 0x0000000);
		mc.fontRenderer.drawString(this.buyPrice + "", -10, 0, 0x0000000);
		mc.fontRenderer.drawString(this.sellPrice + "", -10, -10, 0x0000000);
		mc.fontRenderer.drawString(this.item.stackSize + "", -20, 8, 0x111111);
	 }
	
	
	 private double GetPrice(String text, char indicator) {
	        String[] split = text.replace(" ", "").toLowerCase().split(":");
	        String character = String.valueOf(indicator).toLowerCase();
	        
	        for (String part : split) {
	            if (!part.contains(character)) {
	                continue;
	            }
	
	            part = part.replace(character, "");
	
	            if (part.equals(FREE_TEXT)) {
	                return FREE;
	            }
	
	            if (isDouble(part)) {
	                double price = Double.valueOf(part);
	
	                if (price <= 0) {
	                    return NO_PRICE;
	                } else {
	                    return price;
	                }
	            }
	        }
	
	        return NO_PRICE;
	    }
	    
    private boolean isDouble(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

	public String getUsername() {
		return username;
	}

	public int getBuyPrice() {
		return buyPrice;
	}

	public int getSellPrice() {
		return sellPrice;
	}

	public ItemStack getItem() {
		return item;
	}
}
