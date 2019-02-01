package de.ilovejava.tdgsavestock;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import lombok.Setter;

import lombok.Getter;

public class Util_SaveStock {
	@Getter @Setter
	public static ArrayList<Player> hides;
	@Getter @Setter
	public static HashMap<Player, Location> lastLoc;
	@Getter @Setter
	public static HashMap<Player, ArrayList<ArmorStand>> showStands;
	@Getter @Setter
	public static HashMap<Player, String> menu;
	@Getter @Setter
	public static ArrayList<Entity>stand;
}
