package de.ilovejava.command.subcommands;

import java.io.File;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.ilovejava.enums.Enum_MessageEnum;
import de.ilovejava.utils.Util_Utils;

public class Sub_Command_Change {
	public static String Name;
	Player p;
	public static HashMap<String, Integer> Slot_Integer = new HashMap<>();
	public Sub_Command_Change(String a , Player b) {
		Name = a;
		p = b;
		changeInventory();
	}
	
	private void changeInventory() {
		File f = new File("plugins/NaticDTG/database", Name+".yml");
		if(f.exists()) {
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
			Integer solts = cfg.getInt("Config.Slots");
			Integer highs = cfg.getInt("Config.high");
			Integer value = solts*highs;
			
			Inventory inv = Bukkit.createInventory(null, 54, "§eTDG_Change");
			
			Material m = Material.DIAMOND_BLOCK;
			ItemStack i = new ItemStack(m);
			ItemMeta im = i.getItemMeta();
			for(int size = 1; size < value+1; size++) {
				String slotName = "§c"+size + " TDG";
				im.setDisplayName(slotName);
				i.setItemMeta(im);
				inv.addItem(i);
				Slot_Integer.put(slotName, size);
			}
			p.openInventory(inv);
		}else {p.sendMessage(Util_Utils.getPrefix() + Enum_MessageEnum.TDG_NOT_EXISTS.getMessage());}
	}
}
