package me.KeybordPiano459.AntiHax.checks.movement;

import me.KeybordPiano459.AntiHax.AntiHax;
import me.KeybordPiano459.AntiHax.checks.Check;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSprintEvent;

public class SprintNoFood implements Listener {
	AntiHax plugin;
    public SprintNoFood(AntiHax plugin) {
        this.plugin = plugin;
    }
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onSprint(PlayerToggleSprintEvent event) {
		Player player = event.getPlayer();
		int food = player.getFoodLevel();
		if (!player.hasPermission("antihax.check.sprintnofood")) {
			if (6 >= food) {
				event.setCancelled(true);
				Check.PlayerKick(player, plugin, "Don't sprint without enough food!", "tried to sprint without enough food!");
			}
		}
	}
}
