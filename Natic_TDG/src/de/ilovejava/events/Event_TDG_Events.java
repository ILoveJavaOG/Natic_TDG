package de.ilovejava.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import de.ilovejava.tdgsavestock.Util_SaveStock;

public class Event_TDG_Events implements Listener {
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		if(Util_SaveStock.getStand().contains(e.getEntity())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onArmorStand(PlayerArmorStandManipulateEvent e) {
		if(Util_SaveStock.getStand().contains(e.getRightClicked())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onWordlChange(PlayerChangedWorldEvent e) {
		if(Util_SaveStock.getHides().contains(e.getPlayer())) {
			Util_SaveStock.getHides().remove(e.getPlayer());
		}
	}
}
