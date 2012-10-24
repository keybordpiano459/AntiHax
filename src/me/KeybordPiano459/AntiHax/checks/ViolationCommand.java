package me.KeybordPiano459.AntiHax.checks;

import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ViolationCommand implements CommandExecutor {
	private Map<String, Integer> violation = Check.violation;
	Logger log = Logger.getLogger("Minecraft");
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("check")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (args.length == 0) {
					player.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] Your hack amount is " + violation.get(player.getName()));
				} else if (args.length == 1) {
					Player target = sender.getServer().getPlayer(args[0]);
					if (violation.get(target.getName()) != null) {
						player.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] " + target.getDisplayName() + "'s violation amount is " + violation.get(target.getName()));
					} else {
						player.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] " + args[0] + "doesn't have a violation amount or hasn't logged on to this server.");
					}
				} else {
					player.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] Incorrect usage! Type /check [player]");
				}
			} else {
				if (args.length == 1) {
					Player target = sender.getServer().getPlayer(args[0]);
					if (target != null) {
						if (violation.get(target.getName()) != null) {
							log.info("[AntiHax] " + target.getDisplayName() + "'s violation amount is " + violation.get(target.getName()));
						} else {
							log.info("[AntiHax] " + args[0] + " doesn't have a violation amount or hasn't logged on to this server.");
						}
					}
				} else {
					log.info("[AntiHax] Incorrect usage! Type /check <player>");
				}
			}
			return true;
		}
		return false;
	}
}