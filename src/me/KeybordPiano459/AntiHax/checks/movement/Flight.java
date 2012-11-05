package me.KeybordPiano459.AntiHax.checks.movement;

import me.KeybordPiano459.AntiHax.AntiHax;
import me.KeybordPiano459.AntiHax.listeners.BaseListener;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class Flight extends BaseListener {
	
	public Flight(AntiHax instance) {
		super(instance);
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onFly(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (!player.hasPermission("antihax.check.fly")) {
			if (player.isFlying() && player.getGameMode() != GameMode.CREATIVE) {
				event.setCancelled(true);
				
				kickPlayer(player, "No Flying Allowed!", player.getName() + " tried to fly!");
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onToggleFly(PlayerToggleFlightEvent event) {
		Player player = event.getPlayer();
		if (!player.hasPermission("antihax.check.fly")) {
			if (event.isFlying() && player.getGameMode() != GameMode.CREATIVE) {
				event.setCancelled(true);
				
				kickPlayer(player, "No Flying Allowed!", player.getName() + " tried to fly!");
			}
		}
	}
}
