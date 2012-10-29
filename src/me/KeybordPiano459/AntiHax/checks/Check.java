package me.KeybordPiano459.AntiHax.checks;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
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
		CheckViolation(player);
	}
	
	public static void TellPlayer(Player player, String msg) {
		player.sendMessage(msg); //Send player message
		
		Player[] players = Bukkit.getServer().getOnlinePlayers(); //Get all online players
		for (Player p : players) {
			if (p.isOp() || p.hasPermission("antihax.notify")) {
				p.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] " + player.getDisplayName() + " tried to hack."); //Send admins message
			}
		}
		
		violation.put(player.getName(), 5);
		CheckViolation(player);
	}
	
	public static void CheckViolation(Player player) {
		
		//Checks if violation amount is too high - If it is, set it to 50
		if (violation.get(player.getName()) > 50) {
			violation.put(player.getName(), -(violation.get(player.getName()) - 50));
		}
		
		//Checks if violation amount is zero - If so, ban the player
		if (violation.get(player.getName()) == 0) {
			player.setBanned(true);
			player.kickPlayer("You have hacked too much!");
		}
		
	}
}