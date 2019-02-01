package de.ilovejava.command.subcommands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import de.ilovejava.enums.Enum_MessageEnum;
import de.ilovejava.utils.Util_Utils;

public class Sub_Command_Create implements Listener {
	static Integer slots;
	public static Integer highs;
	static Player p;
	static String Name;
	public Sub_Command_Create(String a, Player b) {
		p = b;
		Name = a;
		selectLegth();
	}

	private void selectLegth() {
		Inventory inv = Bukkit.createInventory(null, 27,"§eLegth Select!");
		String[] names = {"9","8","7","6","5","4","3","2","1"};
		Material m = Material.DIAMOND_BLOCK;
		ItemStack i = new ItemStack(m);
		ItemMeta im = i.getItemMeta();
		for(String na : names) {
			im.setDisplayName("§c"+na+" Solts");
			i.setItemMeta(im);
			inv.addItem(i);
		}
		p.openInventory(inv);
	}
	
	public static void selectHigh(Inventory inv, Integer slot) {
		slots = slot;
		inv.clear();
		String[] names = {"1","2","3"};
		Material m = Material.CHEST;
		ItemStack i = new ItemStack(m);
		ItemMeta im = i.getItemMeta();
		for(String s : names) {
			im.setDisplayName("§c"+s+" High");
			i.setItemMeta(im);
			inv.addItem(i);
		}
		new BukkitRunnable() {
			@Override
			public void run() {
				try {p.updateInventory();}catch(Exception e) {}
			}
		}.runTaskLaterAsynchronously(Util_Utils.getInstance(), 15);
	}
	
	public static void createTDG() {
		File f = new File("plugins/NaticDTG/database", Name+".yml");
		if(!f.exists()) {
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
			cfg.set("Config.Name", Name);
			cfg.set("Config.Slots", slots);
			cfg.set("Config.high", highs);
			cfg.set("Config.Dis.x1", 8);
			cfg.set("Config.Dis.x2", 6);
			cfg.set("Config.Dis.x3", 4);
			cfg.set("Config.Dis.x4", 2);
			cfg.set("Config.Dis.x5", 0);
			cfg.set("Config.Dis.x6", 2);
			cfg.set("Config.Dis.x7", 4);
			cfg.set("Config.Dis.x8", 6);
			cfg.set("Config.Dis.x9", 8);
			try {cfg.save(f);}catch(Exception e) {}
			p.sendMessage(Util_Utils.getPrefix() + Enum_MessageEnum.TDG_Create.getMessage());
			p.closeInventory();
		}else {p.sendMessage(Util_Utils.getPrefix() + Enum_MessageEnum.TDG_Exists.getMessage());}
	}
}
