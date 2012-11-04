package me.KeybordPiano459.AntiHax;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpy implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Logger log = Logger.getLogger("Minecraft");
		
		if (cmd.getName().equalsIgnoreCase("spy")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				
				if(!player.hasPermission("antihax.spy") || player.isOp()){
					player.sendMessage(ChatColor.RED + "You don't have permission to execute that command!");
					return true;
				}
				
				if (args.length == 0) {
					for(Player p : Bukkit.getServer().getOnlinePlayers()){
						p.hidePlayer(player);
					}
					
					player.setFlying(true);
					player.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] You are now hidden and flying! To teleport to a player, type /spy [player]");
				} else if (args.length == 1) {
					if (sender.getServer().getPlayer(args[0]) != null) {
						Player targetplayer = sender.getServer().getPlayer(args[0]);
						
						for(Player p : Bukkit.getServer().getOnlinePlayers()){
							p.hidePlayer(player);
						}
						
						player.setFlying(true);
						player.teleport(targetplayer.getLocation());
						player.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] You are now spying on " + targetplayer);
					} else {
						player.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] " + args[0] + " may be offline.");
					}
				} else {
					player.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] Incorrect usage! Type /spy");
				}
			} else {
				log.info("This command can only be used by players!");
			}
			return true;
		}
		return false;
	}
}
