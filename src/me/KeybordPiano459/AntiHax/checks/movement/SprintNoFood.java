package me.KeybordPiano459.AntiHax.checks.movement;

import me.KeybordPiano459.AntiHax.AntiHax;
import me.KeybordPiano459.AntiHax.checks.Check;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSprintEvent;

public class SprintNoFood extends Check implements Listener {
	AntiHax plugin;
    public SprintNoFood(AntiHax plugin) {
        this.plugin = plugin;
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
							TellPlayer(player, "[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] You sprinted without enough food!");
						}
					}
				}, 200L);
			}
		}
	}
}
