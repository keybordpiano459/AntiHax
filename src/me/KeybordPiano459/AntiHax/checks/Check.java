package me.KeybordPiano459.AntiHax.checks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Check {
	public static void PlayerKick(Player player, Plugin plugin, String kickmessage, String adminmessage) {
		player.kickPlayer(kickmessage);
		Bukkit.getServer().broadcastMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] " + ChatColor.GREEN + player.getDisplayName() + " was kicked for hacking.");
		Player[] players = plugin.getServer().getOnlinePlayers();
		for (Player p : players) {
			if (p.isOp() || p.hasPermission("antihax.notify")) {
				p.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] " + ChatColor.RED + adminmessage);
			}
		}
	}
}