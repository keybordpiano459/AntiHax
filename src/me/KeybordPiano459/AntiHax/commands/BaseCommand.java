package me.KeybordPiano459.AntiHax.commands;

import me.KeybordPiano459.AntiHax.AntiHax;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BaseCommand implements CommandExecutor{
	protected AntiHax plugin;
	
	public BaseCommand(AntiHax instance){
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player){
			Player player = (Player)sender;
			
			if(player.hasPermission("antihax." + command.getName())){
				Run(player, plugin.getServer(), args);
			} else {
				player.sendMessage(ChatColor.RED + "You dont have permission to use this command!");
			}
		} else {
			Run(sender, plugin.getServer(), args);
		}
		
		return true;
	}
	
	public void Run(Player player, Server server, String[] args){
		player.sendMessage(ChatColor.RED + "You can't run this command in-game!");
	}
	
	public void Run(CommandSender sender, Server server, String[] args){
		sender.sendMessage(ChatColor.RED + "You can't run this command from console!");
	}
}
