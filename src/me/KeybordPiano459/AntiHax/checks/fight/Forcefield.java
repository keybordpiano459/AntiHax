package me.KeybordPiano459.AntiHax.checks.fight;

import me.KeybordPiano459.AntiHax.AntiHax;
import me.KeybordPiano459.AntiHax.listeners.BaseListener;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Forcefield extends BaseListener {
	
	public Forcefield(AntiHax instance) {
		super(instance);
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
				tellPlayer(player, "Don't use forcefield!");
			}
		}
	}
}
