package de.ilovejava.changeinventory;

import java.io.File;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.ilovejava.api.API_AnvilGUI;
import de.ilovejava.api.API_AnvilGUI.AnvilClickEvent;
import de.ilovejava.api.API_AnvilGUI.AnvilClickEventHandler;
import de.ilovejava.api.API_AnvilGUI.AnvilSlot;
import de.ilovejava.command.subcommands.Sub_Command_Change;
import de.ilovejava.enums.Enum_MessageEnum;
import de.ilovejava.utils.Util_Utils;

public class Change_Command_Source {
	public static Integer item;
	public Change_Command_Source(Player p) {
		p.closeInventory();
		API_AnvilGUI gui = new API_AnvilGUI(p, new AnvilClickEventHandler() {
			
			@Override
			public void onAnvilClick(AnvilClickEvent event) {
				if(event.getSlot().equals(AnvilSlot.OUTPUT)) {
					event.setWillClose(true);
					event.setWillDestroy(true);
					setCommandSource(p, event.getName());
				}else {
					event.setWillClose(false);
					event.setWillDestroy(false);
				}
			}
		});
		ItemStack i = new ItemStack(Material.CHEST);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName("�cSet Command");
		i.setItemMeta(im);
		gui.setSlot(AnvilSlot.INPUT_LEFT, i);
		try {gui.open();}catch(Exception e) {}
	}
	
	private void setCommandSource(Player p, String commandScr) {
		File f = new File("plugins/NaticDTG/database", Sub_Command_Change.Name+".yml");
		if(f.exists()) {
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
			cfg.set("Config."+item+".CommandSource", commandScr);
			try {cfg.save(f);}catch(Exception e) {}
			p.sendMessage(Util_Utils.getPrefix() + Enum_MessageEnum.TDG_SUC_CHANGE.getMessage());
		}else {p.sendMessage(Util_Utils.getPrefix() + Enum_MessageEnum.TDG_NOT_EXISTS.getMessage());}
	}
}
