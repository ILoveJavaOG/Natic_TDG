package de.ilovejava.utils;

import org.bukkit.configuration.file.FileConfiguration;

import de.ilovejava.natictdg.NaticTDG;
import lombok.Getter;
import lombok.Setter;

public class Util_Utils {
	@Getter @Setter
	public static NaticTDG instance;
	@Getter @Setter
	public static FileConfiguration cfg;
	@Getter @Setter
	public static String prefix;
	@Getter @Setter
	public static boolean protocollib;
}
