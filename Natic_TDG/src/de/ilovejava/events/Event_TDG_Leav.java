package de.ilovejava.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.ilovejava.tdgsavestock.Util_SaveStock;

public class Event_TDG_Leav implements Listener {
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		if(Util_SaveStock.getHides().contains(e.getPlayer())) {
			Util_SaveStock.getHides().remove(e.getPlayer());
		}
	}
}
