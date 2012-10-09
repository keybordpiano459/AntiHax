package me.KeybordPiano459.AntiHax.checks;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Check {
	public static void PlayerKick(Player player, Plugin plugin, String kickmessage, String adminmessage) {
		player.kickPlayer(player.getDisplayName() + " " + kickmessage);
		Player[] players = plugin.getServer().getOnlinePlayers();
		for (Player p : players) {
			if (p.isOp() || p.hasPermission("antihax.notify")) {
				p.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] " + ChatColor.RED + adminmessage);
			}
		}
	}
}
