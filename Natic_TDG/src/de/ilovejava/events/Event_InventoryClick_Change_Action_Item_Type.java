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

public class Event_InventoryClick_Change_Action_Item_Type implements Listener {
	public static Integer item;
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(e.getCurrentItem() == null)return;
		if(e.getInventory() == null)return;
		if(e.getCurrentItem().getItemMeta() == null)return;
		if(!e.getInventory().getName().equalsIgnoreCase("§eSelect TDG")) return;
		Player p = (Player) e.getWhoClicked();
		e.setCancelled(true);
		//{"§cTool","§cBlock","§cSkull"};
		switch(e.getCurrentItem().getItemMeta().getDisplayName()) {
			case "§cTool":
				setItemType(p, "ITEMTYPE_TOOLS");
			break;
			case "§cBlock":
				setItemType(p, "ITEMTYPE_BLOCKS");
				break;
			case "§cSkull":
				setItemType(p, "ITEMTYPE_SKULLS");
				break;
		}
	}
	private void setItemType(Player p, String Type) {
		File f = new File("plugins/NaticDTG/database", Sub_Command_Change.Name+".yml");
		if(f.exists()) {
			p.closeInventory();
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
			cfg.set("Config."+item+".ItemType" , Type);
			try {cfg.save(f);}catch(Exception e) {}
			p.sendMessage(Util_Utils.getPrefix() + Enum_MessageEnum.TDG_SUC_CHANGE.getMessage());
		}else {
			p.sendMessage(Util_Utils.getPrefix() + Enum_MessageEnum.TDG_NOT_EXISTS.getMessage());
		}
	}
}
