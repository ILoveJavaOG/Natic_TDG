package de.ilovejava.events;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import de.ilovejava.enums.Enum_MessageEnum;
import de.ilovejava.tdgapi.API_TDG;
import de.ilovejava.tdgsavestock.Util_SaveStock;
import de.ilovejava.utils.Util_Utils;
import net.md_5.bungee.api.ChatColor;

public class Event_TDG_InterAcct implements Listener {
	@EventHandler
	public void onInter(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(Util_SaveStock.getHides().contains(p)) {
			if(e.getAction().equals(Action.LEFT_CLICK_AIR)) {
				for(Entity en : Util_SaveStock.getShowStands().get(p)) {
					if(API_TDG.getTargetEntity(p) == en) {
						String name = en.getName();
						String menu = Util_SaveStock.getMenu().get(p);
						File f = new File("plugins/NaticDTG/database", menu+".yml");
						YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
						for(String s : cfg.getConfigurationSection("Config").getKeys(false)) {
							if(cfg.isSet("Config."+s+".Name")) {
								String configName = ChatColor.translateAlternateColorCodes('&', cfg.getString("Config."+s+".Name"));
								if(configName.equalsIgnoreCase(name)) {
									if(cfg.isSet("Config."+s+".Permission")) {
										if(!p.hasPermission(cfg.getString("Config."+s+".Permission"))) {
											p.sendMessage(Util_Utils.getPrefix() + Enum_MessageEnum.No_Perm.getMessage());
											return;
										}
									}
									if(cfg.isSet("Config."+s+".Action")) {
										subAction(p, cfg.getString("Config."+s+".Action"), cfg, s);
									}
									break;
								}else {continue;}
							}else {continue;}
						}
					}else {continue;}
				}
			}else {return;}
		}else {return;}
	}
	private void subAction(Player p, String MSG, YamlConfiguration cfg, String line) {
		switch(MSG) {
			case "ACTION_RUN_COMMAND":
				if(cfg.isSet("Config."+line+".CommandSource")) {
					String msg = cfg.getString("Config."+line+".CommandSource");
					msg = msg.replace("%player%", p.getName());
					Bukkit.dispatchCommand(p, msg);
				}
			break;
			case "ACTION_SEND_MESSAGE":
				if(cfg.isSet("Config."+line+".CommandSource")) {
					String msg = ChatColor.translateAlternateColorCodes('&', cfg.getString("Config."+line+".CommandSource"));
					msg = msg.replace("%player%", p.getName());
					p.sendMessage(msg);
				}
				break;
			case "ACTION_GO_TO_WEBSITE":
				if(cfg.isSet("Config."+line+".CommandSource")) {
					String msg = ChatColor.translateAlternateColorCodes('&', cfg.getString("Config."+line+".CommandSource"));
					msg = msg.replace("%player%", p.getName());
					p.sendMessage(msg);
				}
				break;
			case "ACTION_RUN_OP_COMMAND":
				if(cfg.isSet("Config."+line+".CommandSource")) {
					if(cfg.isSet("Config."+line+".OpCommand")) {
						if(cfg.getBoolean("Config."+line+".OpCommand")) {
							String msg = cfg.getString("Config."+line+".CommandSource");
							msg = msg.replace("%player%", p.getName());
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), msg);
						}
					}
				}
				break;
			case "ACTION_CLOSE_TDG":
				Util_SaveStock.getHides().remove(p);
				break;
			default:
				
				break;
		}
	}
}
