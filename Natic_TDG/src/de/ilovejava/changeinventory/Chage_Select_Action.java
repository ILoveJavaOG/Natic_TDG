package de.ilovejava.changeinventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Chage_Select_Action {
	public Chage_Select_Action(Player p, Inventory p_Inv) {
		Inventory inv = p_Inv;
		inv.clear();
		int item = 0;
		Material[] m = {Material.CHEST,Material.TRAPPED_CHEST,Material.ENDER_CHEST,Material.WORKBENCH};
		String[] name = {"§cRun Command","§cSend Message","§cGo to Website","§cRun Op Command","§cClose-Button"};
		Integer[] slots = {10,13,16,30,32};
		for(String s : name) {
			ItemStack i = new ItemStack(m[0]);
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(s);
			i.setItemMeta(im);
			inv.setItem(slots[item], i);
			item++;
		}
		p.updateInventory();
	}
}
