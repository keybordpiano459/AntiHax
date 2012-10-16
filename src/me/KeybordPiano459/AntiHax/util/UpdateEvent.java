package me.KeybordPiano459.AntiHax.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UpdateEvent implements Listener {
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (player.hasPermission("antihax.update")) {
			player.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] " + ChatColor.RED + "A new version of AntiHax is out!");
		}
	}
}
