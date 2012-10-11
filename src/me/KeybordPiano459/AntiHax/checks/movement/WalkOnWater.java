package me.KeybordPiano459.AntiHax.checks.movement;

import me.KeybordPiano459.AntiHax.AntiHax;
import me.KeybordPiano459.AntiHax.checks.Check;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class WalkOnWater implements Listener {
	//private static AntiHax AntiHax = null;
	AntiHax plugin;
    public WalkOnWater(AntiHax plugin) {
        this.plugin = plugin;
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
							Check.PlayerKick(player, plugin, "Don't walk on water!", "tried to walk on water!");
							plugin.logCheat(player.getDisplayName() + " walked on water!");
							//AntiHax.violate(player, 20);
						}
					}
				}, 20L);
			}
		}
	}
}