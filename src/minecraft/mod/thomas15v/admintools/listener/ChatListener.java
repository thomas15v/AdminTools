package mod.thomas15v.admintools.listener;

import mod.thomas15v.admintools.admintools;
import mod.thomas15v.admintools.hud.Notifications;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet3Chat;
import cpw.mods.fml.common.network.IChatListener;

public class ChatListener implements IChatListener
{
    private Notifications notifications;
    public ChatListener(Notifications notifications)
    {
        this.notifications = notifications;
    }

    @Override
    public Packet3Chat serverChat(NetHandler handler, Packet3Chat message)
    {
        return message;
    }

    @Override
    public Packet3Chat clientChat(NetHandler handler, Packet3Chat message)
    {
        //notifications.ShowNotification(message.message);
        return message;
    }
}
