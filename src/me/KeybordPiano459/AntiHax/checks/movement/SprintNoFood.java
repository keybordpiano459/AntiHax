package me.KeybordPiano459.AntiHax.checks.movement;

import me.KeybordPiano459.AntiHax.AntiHax;
import me.KeybordPiano459.AntiHax.listeners.BaseListener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerToggleSprintEvent;

public class SprintNoFood extends BaseListener {
	
    public SprintNoFood(AntiHax instance) {
        super(instance);
    }
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onSprint(final PlayerToggleSprintEvent event) {
		final Player player = event.getPlayer();
		final int food = player.getFoodLevel();
		if (!player.hasPermission("antihax.check.sprintnofood")) {
			if (6 >= food) {
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						if (6 >= food) {
							event.setCancelled(true);
							tellPlayer(player, "[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] You sprinted without enough food!");
						}
					}
				}, 200L);
			}
		}
	}
}
