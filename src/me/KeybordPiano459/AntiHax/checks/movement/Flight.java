package me.KeybordPiano459.AntiHax.checks.movement;

import me.KeybordPiano459.AntiHax.AntiHax;
import me.KeybordPiano459.AntiHax.checks.Check;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class Flight extends Check implements Listener {
	AntiHax plugin;
	public Flight(AntiHax plugin) {
		this.plugin = plugin;
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onFly(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (!player.hasPermission("antihax.check.fly")) {
			if (player.isFlying() && player.getGameMode() != GameMode.CREATIVE) {
				event.setCancelled(true);
				PlayerKick(player, plugin, "No Flying Allowed!", "tried to fly!");
				AntiHax.flight++;
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onToggleFly(PlayerToggleFlightEvent event) {
		Player player = event.getPlayer();
		if (!player.hasPermission("antihax.check.fly")) {
			if (event.isFlying() && player.getGameMode() != GameMode.CREATIVE) {
				event.setCancelled(true);
				PlayerKick(player, plugin, "No Flying Allowed!", "tried to fly!");
				AntiHax.flight++;
			}
		}
	}
}
