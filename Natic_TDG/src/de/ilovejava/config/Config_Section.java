package de.ilovejava.config;

import de.ilovejava.utils.Util_Utils;
import net.md_5.bungee.api.ChatColor;

public class Config_Section {
	public Config_Section() {
		keySet("Config.Prefix", "&8[&6Natic&8]&f ");
		keySet("Config.GUI.SavePath", "{dir}/database/");
		keySet("Config.GUI.SaveData", "{file}.yml");
		keySet("Config.Command.natic", "NaticTDG");
		keySet("Config.Command.Permission", "NaticTDG.*");
		keySet("Config.Update.Version", 64514);
		keySet("Config.Update.check", true);
		
		Util_Utils.setPermission(Util_Utils.getCfg().getString("Config.Command.Permission"));
		Util_Utils.setResourceCheck(Util_Utils.getCfg().getBoolean("Config.Update.check"));
		Util_Utils.setResourceID(Util_Utils.getCfg().getInt("Config.Update.Version"));
		Util_Utils.setPrefix(ChatColor.translateAlternateColorCodes('&', Util_Utils.getCfg().getString("Config.Prefix")));
	}
	
	private void keySet(String key, Object obj) {
		if(!Util_Utils.getCfg().isSet(key)) {
			Util_Utils.getCfg().set(key, obj);
			Util_Utils.getInstance().saveConfig();
		}
	}
}
