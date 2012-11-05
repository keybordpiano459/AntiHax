package me.KeybordPiano459.AntiHax.commands;

import me.KeybordPiano459.AntiHax.AntiHax;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandViolation extends BaseCommand {

	public CommandViolation(AntiHax instance) {
		super(instance);
	}
	
	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0){
			player.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] You currently have " + plugin.getViolations().get(player.getName()) + " violations!");
		} else if(args.length == 1){
			Player target = server.getPlayer(args[0]);
			
			if(target == null){
				player.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] That player is currently not online!");
				return;
			}
			
			player.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] " + target.getName() + " currently has " + plugin.getViolations().get(target.getName()) + " violations!");
		}
	}

	@Override
	public void Run(CommandSender sender, Server server, String[] args){
		if(args.length == 1){
			Player target = server.getPlayer(args[0]);
			
			if(target == null){
				sender.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] That player is currently not online!");
				return;
			}
			
			sender.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] " + target.getName() + " currently has " + plugin.getViolations().get(target.getName()) + " violations!");
		}
	}
}
