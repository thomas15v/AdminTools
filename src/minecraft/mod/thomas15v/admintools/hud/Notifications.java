package mod.thomas15v.admintools.hud;

import java.util.EnumSet;

import net.java.games.input.Controller;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class Notifications implements ITickHandler
{
    public static Notifications instance;

    public Notifications()
    {
        instance = this;
    }

    private String text = "test";
    private int lifetime;

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {}

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {
        Minecraft mc = Minecraft.getMinecraft();

        if (type.equals(EnumSet.of(TickType.RENDER)) && mc.theWorld != null)
        {
            mc.fontRenderer.drawString(text, 2, 2, 0xFFFFFF);
        }
        else if (type.equals(EnumSet.of(TickType.CLIENT)) && mc.theWorld != null)
        {
            int i = 0;

            for (Object obj: mc.theWorld.playerEntities.toArray())
            {
                if (obj instanceof EntityOtherPlayerMP)
                {
                    EntityOtherPlayerMP player = (EntityOtherPlayerMP) obj;

                    if (!player.isDead)
                    {
                        //TODO write notifcation methode
                        //mc.fontRenderer.drawString(player.username + " Is in your area!", 2, 2+10*i, 0xFFFFFF);
                        i++;
                    }
                }
            }
        }
    }

    @Override
    public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.CLIENT, TickType.RENDER);
    }

    @Override
    public String getLabel()
    {
        return "Notifications";
    }

    public void ShowNotification(String text)
    {
        lifetime = 1200;
        this.text = text;
    }
}
