package de.ilovejava.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import de.ilovejava.api.API_Change_TDG;
import de.ilovejava.changeinventory.Chage_Select_Action;
import de.ilovejava.changeinventory.Change_Command_Source;
import de.ilovejava.changeinventory.Change_Select_Item;
import de.ilovejava.changeinventory.Change_Select_ItemType;
import de.ilovejava.changeinventory.Change_Select_Op_Command;
import de.ilovejava.changeinventory.Change_Set_Permission;
import de.ilovejava.changeinventory.Change_Skull_Value;
import de.ilovejava.command.subcommands.Sub_Command_Change;
import de.ilovejava.command.subcommands.Sub_Command_Create;

public class Event_InventoryClick implements Listener {
	public static Integer item;
	@EventHandler
	public void InventoryClick(InventoryClickEvent e) {
		if(e.getCurrentItem() == null)return;
		if(e.getInventory() == null)return;
		if(e.getCurrentItem().getItemMeta() == null)return;
		Player p = (Player) e.getWhoClicked();
		if(e.getInventory().getName().equalsIgnoreCase("§eLegth Select!")) {
			e.setCancelled(true);
			switch(e.getCurrentItem().getItemMeta().getDisplayName()) {
				case "§c1 Solts":
					Sub_Command_Create.selectHigh(e.getInventory(),1);
					break;
				case "§c2 Solts":
					Sub_Command_Create.selectHigh(e.getInventory(),2);
					break;
				case "§c3 Solts":
					Sub_Command_Create.selectHigh(e.getInventory(),3);
					break;
				case "§c4 Solts":
					Sub_Command_Create.selectHigh(e.getInventory(),4);
					break;
				case "§c5 Solts":
					Sub_Command_Create.selectHigh(e.getInventory(),5);
					break;
				case "§c6 Solts":
					Sub_Command_Create.selectHigh(e.getInventory(),6);
					break;
				case "§c7 Solts":
					Sub_Command_Create.selectHigh(e.getInventory(),7);
					break;
				case "§c8 Solts":
					Sub_Command_Create.selectHigh(e.getInventory(),8);
					break;
				case "§c9 Solts":
					Sub_Command_Create.selectHigh(e.getInventory(),9);
					break;
				case "§c1 High":
					Sub_Command_Create.highs = 1;
					Sub_Command_Create.createTDG();
					break;
				case "§c2 High":
					Sub_Command_Create.highs = 2;
					Sub_Command_Create.createTDG();
					break;
				case "§c3 High":
					Sub_Command_Create.highs = 3;
					Sub_Command_Create.createTDG();
					break;
			}
		}else if(e.getInventory().getName().equalsIgnoreCase("§eTDG_Change")) {
			e.setCancelled(true);
			String name = e.getCurrentItem().getItemMeta().getDisplayName();
			if(Sub_Command_Change.Slot_Integer.containsKey(name)) {
				String itemSlot = e.getCurrentItem().getItemMeta().getDisplayName().replace("§c", "").replace("TDG", "").replace(" ", "");
				item = Integer.valueOf(itemSlot);
				new API_Change_TDG(Sub_Command_Change.Name, Sub_Command_Change.Slot_Integer.get(name), p);
			}
		}else if(e.getInventory().getName().equalsIgnoreCase("§eSelect TDG")) {
			e.setCancelled(true);
			//{"§cAction","§cCommand Source","§cItem Type","§cItem Source","§cSkull Value","§cSet Permission","§cOp Command Activ"};
			switch(e.getCurrentItem().getItemMeta().getDisplayName()) {
				case "§cAction":
					Event_InventoryClick_Change_Action.item = item;
					new Chage_Select_Action(p, e.getInventory());
					break;
				case "§cCommand Source":
					Change_Command_Source.item = item;
					new Change_Command_Source(p);
					break;
				case "§cItem Type":
					Event_InventoryClick_Change_Action_Item_Type.item = item;
					new Change_Select_ItemType(p, e.getInventory());
					break;
				case "§cItem Source":
					Change_Select_Item.item = item;
					new Change_Select_Item(p);
					break;
				case "§cSkull Value":
					Change_Skull_Value.item = item;
					new Change_Skull_Value(p);
					break;
				case "§cSet Permission":
					Change_Set_Permission.item = item;
					new Change_Set_Permission(p);
					break;
				case "§cOp Command Activ":
					Change_Select_Op_Command.item = item;
					new Change_Select_Op_Command(p);
					break;
			}
		}
	}
}
