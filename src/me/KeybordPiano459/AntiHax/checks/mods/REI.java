package me.KeybordPiano459.AntiHax.checks.mods;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class REI implements Listener {
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (player.hasPermission("antihax.mod.rei")) {
			player.sendMessage("§0§0§1§2§3§4§5§6§7§e§f");
		}
	}
}