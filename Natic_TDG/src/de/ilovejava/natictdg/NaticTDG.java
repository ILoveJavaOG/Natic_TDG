package de.ilovejava.natictdg;

import org.bukkit.plugin.java.JavaPlugin;

import de.ilovejava.modul.Head_Modul;

public class NaticTDG extends JavaPlugin{
	@Override
	public void onEnable() {
		new Head_Modul(this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
}
