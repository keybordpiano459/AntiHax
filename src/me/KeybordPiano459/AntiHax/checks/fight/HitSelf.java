package me.KeybordPiano459.AntiHax.checks.fight;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.KeybordPiano459.AntiHax.AntiHax;
import me.KeybordPiano459.AntiHax.checks.Check;

public class HitSelf extends Check implements Listener {
	AntiHax plugin;
    public HitSelf(AntiHax plugin) {
        this.plugin = plugin;
    }
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onEntityDmg(EntityDamageByEntityEvent event) {
		Player player = (Player) event.getEntity();
		Entity attacker = event.getDamager();
		Entity victim = event.getEntity();
		if (!player.hasPermission("antihax.check.hitself")) {
			if (attacker == player && victim == attacker) {
				event.setCancelled(true);
				TellPlayer(player, "[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] You tried to hit yourself!");
			}
		}
	}
}
