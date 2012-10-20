package me.KeybordPiano459.AntiHax.checks.movement;

import me.KeybordPiano459.AntiHax.AntiHax;
import me.KeybordPiano459.AntiHax.checks.Check;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSprintEvent;

public class SprintNoFood extends Check implements Listener {
	//private static AntiHax AntiHax = null;
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
							PlayerKick(player, plugin, "Don't sprint without enough food!", "tried to sprint without enough food!");
							AntiHax.sprintnofood++;
							//AntiHax.violate(player, 10);
						}
					}
				}, 200L);
			}
		}
	}
}
