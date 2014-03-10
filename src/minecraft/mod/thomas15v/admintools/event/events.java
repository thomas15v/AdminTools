package mod.thomas15v.admintools.event;

import mod.thomas15v.admintools.hud.Notifications;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class events {
	private Notifications notifications;
	public events(Notifications notifications) {
		this.notifications = notifications;
	}

	@ForgeSubscribe
	public void PlayerJoinedTheGame(EntityJoinWorldEvent e){
		if (e.entity instanceof EntityPlayer) {
			notifications.ShowNotification(((EntityPlayer) e.entity));
		}
		
	}
}
