package de.ilovejava.command;

import java.io.File;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import de.ilovejava.command.subcommands.Sub_Command_Change;
import de.ilovejava.command.subcommands.Sub_Command_Create;
import de.ilovejava.command.subcommands.Sub_Command_Open;
import de.ilovejava.enums.Enum_MessageEnum;
import de.ilovejava.utils.Util_Utils;

public class Command_NaticTDG extends AbstartcCommands{
	public Command_NaticTDG(String commandName, Plugin pl) {
		super(commandName, pl);
	}

	@Override
	public boolean command(CommandSender sender, String[] args) {
		Player p = (Player) sender;
			switch(args.length) {
				case 0 :
					if(p.hasPermission(Util_Utils.getCfg().getString("Config.Command.Permission"))) {
					p.sendMessage("§b/"+Util_Utils.getCfg().getString("Config.Command.natic") + "§b create {Name}");
					p.sendMessage("§b/"+Util_Utils.getCfg().getString("Config.Command.natic") + "§b delete {Name}");
					p.sendMessage("§b/"+Util_Utils.getCfg().getString("Config.Command.natic") + "§b chage {Name}");
					}else {
						p.sendMessage("§b/"+Util_Utils.getCfg().getString("Config.Command.natic") + "§b open {Name}");
					}
				break;
				case 1 :
					if(p.hasPermission(Util_Utils.getCfg().getString("Config.Command.Permission"))) {
						p.sendMessage("§b/"+Util_Utils.getCfg().getString("Config.Command.natic") + "§b create {Name}");
						p.sendMessage("§b/"+Util_Utils.getCfg().getString("Config.Command.natic") + "§b delete {Name}");
						p.sendMessage("§b/"+Util_Utils.getCfg().getString("Config.Command.natic") + "§b chage {Name}");
						}else {
							p.sendMessage("§b/"+Util_Utils.getCfg().getString("Config.Command.natic") + "§b open {Name}");
						}
				break;
				case 2 :
					if(args[0].equalsIgnoreCase("open")) {
						subCommand(p, args);
					}else {
						if(p.hasPermission(Util_Utils.getCfg().getString("Config.Command.Permission"))) {
							subCommand(p, args);
						}else {p.sendMessage(Util_Utils.getPrefix() + Enum_MessageEnum.No_Perm.getMessage());}
					}
				break;
				case 3 :
					p.sendMessage(Util_Utils.getPrefix() + Enum_MessageEnum.No_Args.getMessage());
				break;
			}
		return true;
	}
	
	private void subCommand(Player p, String[] args) {
		switch (args[0]) {
		case "create":
			new Sub_Command_Create(args[1], p);
			break;
		case "change":
			new Sub_Command_Change(args[1], p);
			break;
		case "open":
			new Sub_Command_Open(p, args[1]);
			break;
		case "delete":
			File f = new File("plugins/NaticDTG/database", args[1]+".yml");
			if(f.exists()) {
				f.delete();
				p.sendMessage(Util_Utils.getPrefix() + Enum_MessageEnum.TDG_SUC_DELETE.getMessage());
			}else {p.sendMessage(Util_Utils.getPrefix() + Enum_MessageEnum.TDG_NOT_EXISTS.getMessage());}
			break;
		default:
			p.sendMessage(Util_Utils.getPrefix() + Enum_MessageEnum.No_Args.getMessage());
			break;
		}
	}
	
}
