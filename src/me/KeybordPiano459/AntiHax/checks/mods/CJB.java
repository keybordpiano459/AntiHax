package me.KeybordPiano459.AntiHax.checks.mods;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class CJB implements Listener {
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (!player.hasPermission("antihax.mod.cjb")) {
			player.sendMessage("§3 §9 §2 §0 §0 §0");
		}
	}
}