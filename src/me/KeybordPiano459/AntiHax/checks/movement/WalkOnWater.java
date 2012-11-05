package me.KeybordPiano459.AntiHax.checks.movement;

import me.KeybordPiano459.AntiHax.AntiHax;
import me.KeybordPiano459.AntiHax.listeners.BaseListener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;

public class WalkOnWater extends BaseListener {
	
	public WalkOnWater(AntiHax instance) {
		super(instance);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onMove(final PlayerMoveEvent event) {
		final Player player = event.getPlayer();
		Location loc = player.getLocation();
		loc.setY(loc.getY() - 1);
		final Material block = loc.getBlock().getType();
		if (!player.hasPermission("antihax.check.walkonwater")) {
			if (block == Material.WATER) {
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						if (block == Material.WATER) {
							event.setCancelled(true);
							tellPlayer(player, "[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] You walked on water!");
						}
					}
				}, 20L);
			}
		}
	}
}