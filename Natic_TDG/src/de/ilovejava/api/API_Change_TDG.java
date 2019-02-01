package de.ilovejava.api;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.ilovejava.api.API_AnvilGUI.AnvilClickEvent;
import de.ilovejava.api.API_AnvilGUI.AnvilClickEventHandler;
import de.ilovejava.api.API_AnvilGUI.AnvilSlot;
import de.ilovejava.enums.Enum_MessageEnum;
import de.ilovejava.utils.Util_Utils;


public class API_Change_TDG {
	String Name;
	Integer slot;
	Player p;
	File f;
	YamlConfiguration cfg;
	public API_Change_TDG(String a, Integer i, Player c) {
		Name = a;
		slot = i;
		p = c;
		checkSet();
	}
	
	private void checkSet() {
		f = new File("plugins/NaticDTG/database", Name+".yml");
		if(f.exists()) {
			cfg = YamlConfiguration.loadConfiguration(f);
			if(!cfg.isSet("Config."+slot)) {
				API_AnvilGUI gui = new API_AnvilGUI(p, new AnvilClickEventHandler() {
					@Override
					public void onAnvilClick(AnvilClickEvent event) {
						if(event.getSlot().equals(AnvilSlot.OUTPUT)) {
							event.setWillClose(true);
							event.setWillDestroy(false);
							setContains(event.getName());
						}else {
							event.setWillClose(false);
							event.setWillDestroy(false);
						}
					}
				});
				ItemStack i = new ItemStack(Material.CHEST);
				ItemMeta im = i.getItemMeta();
				im.setDisplayName("§cSet the Name of the TDG");
				i.setItemMeta(im);
				gui.setSlot(AnvilSlot.INPUT_LEFT, i);
				try {gui.open();}catch(Exception e) {}
			}else {
				Inventory i = Bukkit.createInventory(null, 54, "§eSelect TDG");
				String[] name = {"§cAction","§cCommand Source","§cItem Type","§cItem Source","§cSkull Value","§cSet Permission","§cOp Command Activ"};
				Integer[] Slots = {10,12,14,16,28,31,34};
				Material[] m = {Material.CHEST,Material.ANVIL,Material.WORKBENCH,Material.FURNACE,Material.JUKEBOX,Material.ENCHANTMENT_TABLE,Material.COMMAND};
				int item = 0;
				for(String s : name) {
					ItemStack ii = new ItemStack(m[item]);
					ItemMeta im = ii.getItemMeta();
					im.setDisplayName(s);
					ii.setItemMeta(im);
					i.setItem(Slots[item], ii);
					item++;
				}
				p.closeInventory();
				p.openInventory(i);
			}
		}
	}
	
	private void setContains(String TDG_Name) {
		cfg.set("Config."+slot+".Name", TDG_Name);
		cfg.set("Config."+slot+".dis.x1", getSlot(slot));
		cfg.set("Config."+slot+".dis.y1", getHigh(slot));
		try {cfg.save(f);}catch(Exception e) {}
		p.sendMessage(Util_Utils.getPrefix() + Enum_MessageEnum.TDG_SUC_CHANGE.getMessage());
	}
	
	private Integer getHigh(Integer i) {
		Integer high = 1;
		switch(i) {
			case 1:
				high = 1;
				break;
			case 2:
				high = 1;
				break;
			case 3:
				high = 1;
				break;
			case 4:
				high = 1;
				break;
			case 5:
				high = 1;
				break;
			case 6:
				high = 1;
				break;
			case 7:
				high = 1;
				break;
			case 8:
				high = 1;
				break;
			case 9:
				high = 1;
				break;
			case 10:
				high = 2;
				break;
			case 11:
				high = 2;
				break;
			case 12:
				high = 2;
				break;
			case 13:
				high = 2;
				break;
			case 14:
				high = 2;
				break;
			case 15:
				high = 2;
				break;
			case 16:
				high = 2;
				break;
			case 17:
				high = 2;
				break;
			case 18:
				high = 2;
				break;
			case 19:
				high = 3;
				break;
			case 20:
				high = 3;
				break;
			case 21:
				high = 3;
				break;
			case 22:
				high = 3;
				break;
			case 23:
				high = 3;
				break;
			case 24:
				high = 3;
				break;
			case 25:
				high = 3;
				break;
			case 26:
				high = 3;
				break;
			case 27:
				high = 3;
				break;
		}
		return high;
	}
	private Integer getSlot(Integer i) {
		Integer high = 1;
		switch(i) {
			case 1:
				high = 1;
				break;
			case 2:
				high = 2;
				break;
			case 3:
				high = 3;
				break;
			case 4:
				high = 4;
				break;
			case 5:
				high = 5;
				break;
			case 6:
				high = 6;
				break;
			case 7:
				high = 7;
				break;
			case 8:
				high = 8;
				break;
			case 9:
				high = 9;
				break;
			case 10:
				high = 1;
				break;
			case 11:
				high = 2;
				break;
			case 12:
				high = 3;
				break;
			case 13:
				high = 4;
				break;
			case 14:
				high = 5;
				break;
			case 15:
				high = 6;
				break;
			case 16:
				high = 7;
				break;
			case 17:
				high = 8;
				break;
			case 18:
				high = 9;
				break;
			case 19:
				high = 1;
				break;
			case 20:
				high = 2;
				break;
			case 21:
				high = 3;
				break;
			case 22:
				high = 4;
				break;
			case 23:
				high = 5;
				break;
			case 24:
				high = 6;
				break;
			case 25:
				high = 7;
				break;
			case 26:
				high = 8;
				break;
			case 27:
				high = 9;
				break;
		}
		return high;
	}
}
