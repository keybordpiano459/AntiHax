package me.KeybordPiano459.AntiHax.checks.mods;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Zombe implements Listener {
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (!player.hasPermission("antihax.mod.zombe")) {
			player.sendMessage("§f §f §1 §0 §2 §4 §3 §9 §2 §0 §0 §1"); //Fly
			player.sendMessage("§f §f §2 §0 §4 §8 §3 §9 §2 §0 §0 §2"); //Cheat
		}
	}
}
