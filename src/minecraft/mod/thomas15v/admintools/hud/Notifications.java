package mod.thomas15v.admintools.hud;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiScreen;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class Notifications implements ITickHandler {
	
	public static Notifications instance;
	
	public Notifications(){
		
		instance = this;
	}

	private String text = "test";
	private int lifetime;
	

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		Minecraft mc = Minecraft.getMinecraft();

        
        			
		if (type.equals(EnumSet.of(TickType.RENDER)) && mc.theWorld != null) {
			int i = 0;
			 for (Object obj: mc.theWorld.playerEntities.toArray()){
				if (obj instanceof EntityOtherPlayerMP){
					
					EntityOtherPlayerMP player = (EntityOtherPlayerMP) obj;
					if (!player.isDead){						
						mc.fontRenderer.drawString(player.username + "x:" + player.posX + "  z:" + player.posZ, 2, 2+10*i, 0xFFFFFF);
						i++;
					}
				}
				
			 }
			 
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT, TickType.RENDER);
	}

	@Override
	public String getLabel() {
		return "Notifications";
	}
	
	public void ShowNotification(String text) {
		lifetime = 1200;
		this.text = text;
	}

}
