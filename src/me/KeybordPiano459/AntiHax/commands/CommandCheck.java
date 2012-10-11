package me.KeybordPiano459.AntiHax.commands;

import java.util.logging.Logger;

import me.KeybordPiano459.AntiHax.AntiHax;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandCheck implements CommandExecutor {
	AntiHax plugin;
    public CommandCheck(AntiHax plugin) {
        this.plugin = plugin;
    }
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Logger log = Logger.getLogger("Minecraft");
		if (cmd.getName().equalsIgnoreCase("check")) {
			if (args.length == 0) {
				if (sender instanceof Player) {
					Player player = (Player) sender;
					int hackamt = plugin.playerHackAmt.put(player.getName(), plugin.playerHackAmt.get(player.getName()));
					if (hackamt >= 30) {
						player.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] " + ChatColor.GREEN + "Your violation amount is " + hackamt);
					} else if (hackamt >= 15 && hackamt < 30) {
						player.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] " + ChatColor.YELLOW + "Your violation amount is " + hackamt);
					} else {
						player.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] " + ChatColor.RED + "Your violation amount is " + hackamt);
					}
				} else {
					if (args.length == 1) {
						if (sender.getServer().getPlayer(args[0]) != null) {
							Player targetplayer = sender.getServer().getPlayer(args[0]);
							int targethackamt = plugin.playerHackAmt.put(sender.getName(), plugin.playerHackAmt.get(args[0]));
							log.info(targetplayer + "'s violation amount is " + targethackamt);
						}
					}
				}
			} else if (args.length == 1) {
				if (sender instanceof Player) {
					Player player = (Player) sender;
					if (sender.getServer().getPlayer(args[0]) != null) {
						Player targetplayer = sender.getServer().getPlayer(args[0]);
						int targethackamt = plugin.playerHackAmt.put(player.getName(), plugin.playerHackAmt.get(args[0]));
						if (targethackamt >= 30) {
							player.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] " + ChatColor.GREEN + targetplayer + "'s violation amount is " + targethackamt);
						} else if (targethackamt >= 15 && targethackamt < 30) {
							player.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] " + ChatColor.YELLOW + targetplayer + "'s violation amount is " + targethackamt);
						} else {
							player.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] " + ChatColor.RED + targetplayer + "'s violation amount is " + targethackamt);
						}
					} else {
						player.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] " + ChatColor.RED + "That player may be offline.");
					}
				} else {
					if (sender.getServer().getPlayer(args[0]) != null) {
						Player targetplayer = sender.getServer().getPlayer(args[0]);
						int targethackamt = plugin.playerHackAmt.put(sender.getName(), plugin.playerHackAmt.get(args[0]));
						log.info(targetplayer + "'s violation amount is " + targethackamt);
					} else {
						log.info("That player may be offline");
					}
				}
			} else {
				if (sender instanceof Player) {
					Player player = (Player) sender;
					player.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] " + ChatColor.RED + "Incorrect usage! Type /check <player>");
				} else {
					log.info("Incorrect usage! Type /check [player]");
				}
			}
		}
		return false;
	}
}
