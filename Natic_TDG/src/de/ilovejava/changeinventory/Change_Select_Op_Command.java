package de.ilovejava.changeinventory;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.ilovejava.command.subcommands.Sub_Command_Change;
import de.ilovejava.enums.Enum_MessageEnum;
import de.ilovejava.utils.Util_Utils;
public class Change_Select_Op_Command {
	public static Integer item;
	public Change_Select_Op_Command(Player p) {
		p.closeInventory();
		File f = new File("plugins/NaticDTG/database", Sub_Command_Change.Name+".yml");
		if(f.exists()) {
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
			if(cfg.isSet("Config."+item+".OpCommand")) {
				if(cfg.getBoolean("Config."+item+".OpCommand")) {
					cfg.set("Config."+item+".OpCommand", false);
				}else {
					cfg.set("Config."+item+".OpCommand", true);
				}
			}else {
				cfg.set("Config."+item+".OpCommand", true);
			}
			try {cfg.save(f);}catch(Exception e) {}
			p.sendMessage(Util_Utils.getPrefix() + Enum_MessageEnum.TDG_SUC_CHANGE.getMessage());
		}else {
			p.sendMessage(Util_Utils.getPrefix() + Enum_MessageEnum.TDG_SUC_CHANGE.getMessage());
		}
	}
}
