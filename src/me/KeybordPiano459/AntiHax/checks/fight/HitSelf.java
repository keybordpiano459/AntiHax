package me.KeybordPiano459.AntiHax.checks.fight;

import me.KeybordPiano459.AntiHax.AntiHax;
import me.KeybordPiano459.AntiHax.listeners.BaseListener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class HitSelf extends BaseListener {
	
	public HitSelf(AntiHax instance) {
		super(instance);
	}

	@EventHandler (priority = EventPriority.HIGHEST)
	public void onEntityDmg(EntityDamageByEntityEvent event) {
		Player player = (Player) event.getEntity();
		Entity attacker = event.getDamager();
		Entity victim = event.getEntity();
		if (!player.hasPermission("antihax.check.hitself")) {
			if(player.getLastDamageCause().getCause() == DamageCause.PROJECTILE){
				//Arrows can now hit the player without showing an error in the log.
				return;
			}
			
			if (attacker == player && victim == attacker) {
				event.setCancelled(true);
				tellPlayer(player, "[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] You tried to hit yourself!");
			}
		}
	}
}
