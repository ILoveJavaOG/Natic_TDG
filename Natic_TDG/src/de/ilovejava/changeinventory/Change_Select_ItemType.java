package de.ilovejava.changeinventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Change_Select_ItemType {
	public Change_Select_ItemType(Player p, Inventory inv) {
		inv.clear();
		int item = 0;
		String[] Name = {"§cTool","§cBlock","§cSkull"};
		Integer[] Slots = {10,13,16};
		Material[] m = {Material.DIAMOND_PICKAXE,Material.GOLD_BLOCK,Material.SKULL_ITEM};
		for(String s : Name) {
			ItemStack i = new ItemStack(m[item]);
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(s);
			i.setItemMeta(im);
			inv.setItem(Slots[item], i);
			item ++;
		}
		p.updateInventory();
	}
}
