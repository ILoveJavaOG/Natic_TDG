package de.ilovejava.events;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import de.ilovejava.command.subcommands.Sub_Command_Change;
import de.ilovejava.enums.Enum_MessageEnum;
import de.ilovejava.utils.Util_Utils;

public class Event_InventoryClick_Change_Action implements Listener {
	public static Integer item = 0;
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(e.getCurrentItem() == null)return;
		if(e.getInventory() == null)return;
		if(e.getCurrentItem().getItemMeta() == null)return;
		if(!e.getInventory().getName().equalsIgnoreCase("§eSelect TDG")) return;
		Player p = (Player) e.getWhoClicked();
		e.setCancelled(true);
		//{"§cRun Command","§cSend Message","§cGo to Website","§cRun Op Command"};
		switch(e.getCurrentItem().getItemMeta().getDisplayName()) {
			case "§cRun Command":
				setCommandType(p, "ACTION_RUN_COMMAND");
				break;
			case "§cSend Message":
				setCommandType(p, "ACTION_SEND_MESSAGE");
				break;
			case "§cGo to Website":
				setCommandType(p, "ACTION_GO_TO_WEBSITE");
				break;
			case "§cRun Op Command":
				setCommandType(p, "ACTION_RUN_OP_COMMAND");
				break;
			case "§cClose-Button":
				setCommandType(p, "ACTION_CLOSE_TDG");
			break;
		}
	}
	private void setCommandType(Player p, String actio) {
		File f = new File("plugins/NaticDTG/database", Sub_Command_Change.Name+".yml");
		if(f.exists()) {
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
			cfg.set("Config."+item+".Action", actio);
			try {cfg.save(f);}catch(Exception e) {};
			p.sendMessage(Util_Utils.getPrefix() + Enum_MessageEnum.TDG_SUC_CHANGE.getMessage());
			p.closeInventory();
		}else {p.sendMessage(Util_Utils.getPrefix() + Enum_MessageEnum.TDG_NOT_EXISTS.getMessage());}
	}
}
