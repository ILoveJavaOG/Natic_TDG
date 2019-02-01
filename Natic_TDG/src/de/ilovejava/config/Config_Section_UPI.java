package de.ilovejava.config;

import de.ilovejava.interfaces.Interface_ConfigDatas;
import de.ilovejava.utils.Util_Utils;

public class Config_Section_UPI implements Interface_ConfigDatas{
	@Override
	public String SavePath() {
		return Util_Utils.getCfg().getString("Config.GUI.SavePath");
	}

	@Override
	public String SaveFormat() {
		return Util_Utils.getCfg().getString("Config.GUI.SaveData");
	}

	@Override
	public String Command() {
		return Util_Utils.getCfg().getString("Config.Command.natic");
	}

	@Override
	public String CommandPermission() {
		return Util_Utils.getCfg().getString("Config.Command.Permission");
	}
}
