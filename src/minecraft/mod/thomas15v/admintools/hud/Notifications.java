package mod.thomas15v.admintools.hud;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
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
		 if (type.equals(EnumSet.of(TickType.RENDER))) {
             Minecraft mc = Minecraft.getMinecraft();
             if (mc.currentScreen == null) {
		    	 	if (lifetime > 0){
		    	 		 GuiIngame gig = new GuiIngame(mc);
	                     gig.drawRect(2, 2, 100, 100, 0x895948);
	                     gig.drawString(mc.fontRenderer, text, 2, 2, 0xFFFFFF);
	                     lifetime--;
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
