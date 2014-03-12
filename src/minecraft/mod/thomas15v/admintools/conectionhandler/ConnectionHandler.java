package mod.thomas15v.admintools.conectionhandler;

import java.io.UnsupportedEncodingException;

import mod.thomas15v.admintools.admintools;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class ConnectionHandler implements IPacketHandler
{
    public enum PacketType
    {
        ShopSignInfo
    }

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
    {
        if (packet.channel.equalsIgnoreCase("AdminTools"))
        {
            try {
				admintools.notifications.ShowNotification(new String(packet.data, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        // TODO Auto-generated method stub
    }
}
