package me.KeybordPiano459.AntiHax.checks.movement;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class NoFall implements Listener {
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onDamage(EntityDamageEvent event) {
		//Player player = (Player) event.getEntity();
		if (event.getCause() == DamageCause.FALL) {
			//int health = player.getHealth();
		}
	}
}
