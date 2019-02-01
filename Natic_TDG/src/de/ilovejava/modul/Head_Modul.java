package de.ilovejava.modul;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import de.ilovejava.command.Command_NaticTDG;
import de.ilovejava.config.Config_Section;
import de.ilovejava.config.Config_Section_UPI;
import de.ilovejava.events.Event_InventoryClick;
import de.ilovejava.events.Event_InventoryClick_Change_Action;
import de.ilovejava.events.Event_InventoryClick_Change_Action_Item_Type;
import de.ilovejava.events.Event_TDG_Events;
import de.ilovejava.events.Event_TDG_InterAcct;
import de.ilovejava.events.Event_TDG_Join;
import de.ilovejava.events.Event_TDG_Leav;
import de.ilovejava.natictdg.NaticTDG;
import de.ilovejava.tdgsavestock.Util_SaveStock;
import de.ilovejava.utils.Util_Utils;

public class Head_Modul extends Config_Section_UPI{
	public Head_Modul(NaticTDG pl) {
		loadInstance(pl);
		loadEvent();
		loadConfig();
		checkPlugins();
		loadCommands();
	}

	private void checkPlugins() {
		if(Bukkit.getPluginManager().isPluginEnabled("ProtocolLib")) {
			System.out.println("ProtocolLib hook enabled");
			Util_Utils.setProtocollib(true);
		}else {Bukkit.getPluginManager().disablePlugin(Util_Utils.getInstance());}
	}

	private void loadConfig() {
		new Config_Section();
	}

	private void loadCommands() {
		new Command_NaticTDG(Command(),Util_Utils.getInstance());
	}

	private void loadEvent() {
		HashSet<Listener> events = new HashSet<>();
		events.add(new Event_InventoryClick());
		events.add(new Event_InventoryClick_Change_Action());
		events.add(new Event_InventoryClick_Change_Action_Item_Type());
		events.add(new Event_TDG_InterAcct());
		events.add(new Event_TDG_Events());
		events.add(new Event_TDG_Join());
		events.add(new Event_TDG_Leav());
		for(Listener l : events) {
			Bukkit.getPluginManager().registerEvents(l, Util_Utils.getInstance());
		}
	}

	private void loadInstance(NaticTDG pl) {
		Util_Utils.setInstance(pl);
		Util_Utils.setCfg(pl.getConfig());
		Util_SaveStock.setHides(new ArrayList<Player>());
		Util_SaveStock.setLastLoc(new HashMap<Player,Location>());
		Util_SaveStock.setShowStands(new HashMap<>());
		Util_SaveStock.setMenu(new HashMap<>());
		Util_SaveStock.setStand(new ArrayList<>());
	}
}
