package me.KeybordPiano459.AntiHax.checks.fight;

import me.KeybordPiano459.AntiHax.AntiHax;
import me.KeybordPiano459.AntiHax.checks.Check;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Forcefield extends Check implements Listener {
	AntiHax plugin;
    public Forcefield(AntiHax plugin) {
        this.plugin = plugin;
    }
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onAttack(EntityDamageByEntityEvent event) {
		Player player = (Player) event.getDamager();
		Entity entity = event.getEntity();
		Location loc = entity.getLocation();
		Location eye = player.getEyeLocation();
		if (!player.hasPermission("antihax.check.forcefield")) {
			if (eye != loc) {
				event.setCancelled(true);
				PlayerKick(player, plugin, "Don't use forcefield!", "tried to use forcefield");
			}
		}
	}
}
