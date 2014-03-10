package mod.thomas15v.admintools;

import java.util.logging.Logger;

import mod.thomas15v.admintools.hud.Notifications;
import mod.thomas15v.admintools.listener.ChatListener;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(name="admintools", modid="admintools", version="1.0.0")
@SideOnly(Side.CLIENT)
public class admintools {
	@Instance(value = "admintools")
	public static admintools instance;
	
	@Init
	public void init(FMLInitializationEvent e){
		Notifications notifications = new Notifications();
		TickRegistry.registerTickHandler(notifications, Side.CLIENT);
		NetworkRegistry.instance().registerChatListener(new ChatListener(notifications));
		
	}	
	
	public static Logger getModLogger(final String name) {
		  final Logger result = Logger.getLogger(name);
		  return result;
	}
}
