package de.ilovejava.events;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.ilovejava.enums.Enum_MessageEnum;
import de.ilovejava.hider.API_EntityHider;
import de.ilovejava.hider.API_EntityHider.Policy;
import de.ilovejava.tdgsavestock.Util_SaveStock;
import de.ilovejava.updatecheck.Update;
import de.ilovejava.utils.Util_Utils;

public class Event_TDG_Join implements Listener {
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		try {
			if(Util_Utils.isResourceCheck()) {
				Update u = new Update(123);
				if(u.checkUpdate()) {
					if(e.getPlayer().hasPermission(Util_Utils.getPermission())) {
						e.getPlayer().sendMessage(Util_Utils.getPrefix() + Enum_MessageEnum.TDG_NEW_VERSION.getMessage() + " " + u.getLastVersion());
					}
				}
			}
		}catch(Exception e2) {}
		
		API_EntityHider hide = new API_EntityHider(Util_Utils.getInstance(),Policy.BLACKLIST);
		for(Entity en : Util_SaveStock.getStand()) {
			hide.hideEntity(e.getPlayer(), en);
		}
		hide.close();
	}
}
