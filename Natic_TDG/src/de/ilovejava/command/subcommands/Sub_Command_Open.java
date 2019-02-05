package de.ilovejava.command.subcommands;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.ilovejava.enums.Enum_MessageEnum;
import de.ilovejava.tdgapi.API_TDG;
import de.ilovejava.tdgsavestock.Util_SaveStock;
import de.ilovejava.utils.Util_Utils;

public class Sub_Command_Open {
	public Sub_Command_Open(Player p, String name) {
		File f = new File("plugins/NaticDTG/database", name+".yml");
		String[] nameArray = {"1","2","3","4","5","6","7","8","9",
				  "10","11","12","13","14","15","16","17","18",
				  "19","20","21","22","23","24","25","26","27"};
		List<String> names = Arrays.asList(nameArray);
		if(f.exists()) {
			Location loc = API_TDG.getLocationBF(p.getLocation(), 3.5);
			
			Util_SaveStock.getLastLoc().put(p, loc);
			Util_SaveStock.getHides().add(p);
			Util_SaveStock.getShowStands().put(p, new ArrayList<>());
			Util_SaveStock.getMenu().put(p, name);
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
			double x1 = cfg.getDouble("Config.Dis.x1");
			double x2 = cfg.getDouble("Config.Dis.x2");
			double x3 = cfg.getDouble("Config.Dis.x3");
			double x4 = cfg.getDouble("Config.Dis.x4");
			double x5 = cfg.getDouble("Config.Dis.x5");
			double x6 = cfg.getDouble("Config.Dis.x6");
			double x7 = cfg.getDouble("Config.Dis.x7");
			double x8 = cfg.getDouble("Config.Dis.x8");
			double x9 = cfg.getDouble("Config.Dis.x9");
			for(String s : cfg.getConfigurationSection("Config").getKeys(false)) {
				if(names.contains(s)) {
					if(isPositionSet(s, cfg)) {
						API_TDG api = new API_TDG();
						if(cfg.isSet("Config."+s+".ItemType")) {
							if(cfg.getString("Config."+s+".ItemType").equalsIgnoreCase("ITEMTYPE_BLOCKS")) {
								api.addIcon(p, API_TDG.setPosition(loc, getXPosition(s, cfg), getYPosition(s, cfg), x1, x2, x3, x4, x5, x6, x7, x8, x9), getName(s, cfg), new ItemStack(getItemMaterial(s, cfg)), getYPosition(s, cfg));
							}else if(cfg.getString("Config."+s+".ItemType").equalsIgnoreCase("ITEMTYPE_TOOLS")) {
								api.addIcon(p, API_TDG.setPosition(loc, getXPosition(s, cfg), getYPosition(s, cfg), x1, x2, x3, x4, x5, x6, x7, x8, x9), getName(s, cfg), new ItemStack(getItemMaterial(s, cfg)), true, true, getYPosition(s, cfg));
							}else if(cfg.getString("Config."+s+".ItemType").equalsIgnoreCase("ITEMTYPE_SKULLS")) {
								api.addIcon(p, API_TDG.setPosition(loc, getXPosition(s, cfg), getXPosition(s, cfg), x1, x2, x3, x4, x5, x6, x7, x8, x9), getName(s, cfg), cfg.getString("Config."+s+".SkinValue"), getYPosition(s, cfg));
							}
						}else {
							api.addIcon(p, API_TDG.setPosition(loc, getXPosition(s, cfg), getYPosition(s, cfg), x1, x2, x3, x4, x5, x6, x7, x8, x9), getName(s, cfg), new ItemStack(getItemMaterial(s, cfg)), getYPosition(s, cfg));
						}
					}else {continue;}
				}else {continue;}
			}
		}else {p.sendMessage(Util_Utils.getPrefix() + Enum_MessageEnum.TDG_NOT_EXISTS.getMessage());}
	}
	
	private boolean isPositionSet(String line, YamlConfiguration cfg) {
		if(cfg.isSet("Config."+line+".dis.x1") && cfg.isSet("Config."+line+".dis.y1")) {
			return true;
		}
		return false;
	}
	
	private String getName(String line, YamlConfiguration cfg) {
		return cfg.getString("Config."+line+".Name");
	}
	
	@SuppressWarnings("deprecation")
	private Material getItemMaterial(String line, YamlConfiguration cfg) {
		if(!cfg.isSet("Config."+line+".ItemMeta") || cfg.getInt("Config."+line+".ItemMeta") == 0) {
			return Material.STONE;
		}
		return Material.getMaterial(cfg.getInt("Config."+line+".ItemMeta"));
	}
	
	private Integer getXPosition(String line, YamlConfiguration cfg) {
		return cfg.getInt("Config."+line+".dis.x1");
	}
	
	private Integer getYPosition(String line, YamlConfiguration cfg) {
		return cfg.getInt("Config."+line+".dis.y1");
	}
}
