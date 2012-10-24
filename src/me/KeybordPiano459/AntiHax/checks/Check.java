package me.KeybordPiano459.AntiHax.checks;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class Check {
	
	static Map<String, Integer> violation = new HashMap<String, Integer>();
	
	public static void PlayerKick(Player player, Plugin plugin, String kickmessage, String adminmessage) {
		player.kickPlayer(kickmessage);
		Bukkit.getServer().broadcastMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] " + player.getDisplayName() + " was kicked for hacking.");
		Player[] players = plugin.getServer().getOnlinePlayers();
		for (Player p : players) {
			if (p.isOp() || p.hasPermission("antihax.notify")) {
				p.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] " + ChatColor.RED + adminmessage);
			}
		}
		
		violation.put(player.getName(), 10);
		ViolationAmount(player);
	}
	
	public static void TellPlayer(Player player, String msg) {
		player.sendMessage(msg.toLowerCase()); //Send player message
		
		Player[] players = Bukkit.getServer().getOnlinePlayers(); //Get all online players
		for (Player p : players) {
			if (p.isOp() || p.hasPermission("antihax.notify")) {
				p.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] " + player.getDisplayName() + " tried to hack."); //Send admins message
			}
		}
		
		violation.put(player.getName(), 5);
		ViolationAmount(player);
	}
	
	public static void ViolationAmount(Player player) {
		if (violation.get(player.getName()) > 50) {
			violation.put(player.getName(), -(violation.get(player.getName()) - 50));
		}
	}
	
	public class PlayerLogin implements Listener {
		@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled=true)
		public void onJoin(PlayerJoinEvent event) {
			Player player = event.getPlayer();
			if (player.hasPlayedBefore() == false) {
				violation.put(player.getName(), 50);
			}
		}
	}
	
	public class ViolationCommand implements CommandExecutor {
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
						if (violation.get(target.getName()) != null) {
							log.info("[AntiHax] " + target.getDisplayName() + "'s violation amount is " + violation.get(target.getName()));
						} else {
							log.info("[AntiHax] " + args[0] + " doesn't have a violation amount or hasn't logged on to this server.");
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
}