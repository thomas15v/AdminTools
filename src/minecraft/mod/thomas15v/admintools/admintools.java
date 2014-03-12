package mod.thomas15v.admintools;

import java.util.logging.Logger;

import mod.thomas15v.admintools.blocks.SpecialTileEntitySignRenderer;
import mod.thomas15v.admintools.conectionhandler.ConnectionHandler;
import mod.thomas15v.admintools.hud.Notifications;
import mod.thomas15v.admintools.listener.ChatListener;
import net.minecraft.tileentity.TileEntitySign;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(name = "admintools", modid = "admintools", version = "1.0.0", acceptedMinecraftVersions = "1.6.4")
@SideOnly(Side.CLIENT)
public class admintools
{
    @Instance(value = "admintools")
    public static admintools instance;
    public static Notifications notifications = new Notifications();

    @Init
    public void init(FMLInitializationEvent e)
    {
        TickRegistry.registerTickHandler(notifications, Side.CLIENT);
        NetworkRegistry.instance().registerChatListener(new ChatListener(notifications));
        NetworkRegistry.instance().registerChannel(new ConnectionHandler(), "AdminTools");
        /*
        int signwallid = Block.signWall.blockID;
        Block.blocksList[signwallid] = null;
        Block signWall = (new SpecialSign(68, TileEntitySign.class, false)).setHardness(1.0F).setStepSound(new StepSound("wood", 1.0F, 1.0F)).setBlockName("sign").setRequiresSelfNotify();
        GameRegistry.registerBlock(signWall, "Sign");
        */
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySign.class, new SpecialTileEntitySignRenderer());
    }

    public static Logger getModLogger(final String name)
    {
        final Logger result = Logger.getLogger(name);
        return result;
    }
}
