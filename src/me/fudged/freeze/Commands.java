package me.fudged.freeze;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Commands implements CommandExecutor {
	
	// Class name is conflicting with the command object. So I renamed the class to "Commands"
	
	Freeze freeze;
	public Commands(Freeze f){
		this.freeze = f;
	}
	
	@Override
	public boolean onCommand(CommandSender Sender, Command cmd, String label, String[] args) {
		if(!(Sender instanceof Player)){
			Sender.sendMessage("Only players can use this command");
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("freeze")){
			Player p = (Player) Sender;
			if(args.length != 1){
				p.sendMessage(ChatColor.RED + "Usage: /freeze <player>");
				return true;
			}
			
			if(args.length == 1){
				
				if(Bukkit.getServer().getPlayer(args[0]) == null){
					p.sendMessage(ChatColor.RED + args[0] + " is not online");
					return true;
				}
				
				Player t = Bukkit.getServer().getPlayer(args[0]);
				if(freeze.getFrozenPlayers().contains(t.getUniqueId())){
					freeze.unFreeze(t);
					t.sendMessage(ChatColor.RED + "You are now unfrozen");
					p.sendMessage(ChatColor.RED + "You have unfrozen " + args[0]);
					return true;
				}else{
					freeze.freeze(t);
					t.sendMessage(ChatColor.RED + "You are now frozen");
					p.sendMessage(ChatColor.RED + "You have frozen " + t.getName());
					return true;
				}
			}
		}
		return false;
	}

}
