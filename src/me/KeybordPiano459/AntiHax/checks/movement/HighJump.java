package me.KeybordPiano459.AntiHax.checks.movement;

import me.KeybordPiano459.AntiHax.checks.Check;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class HighJump extends Check implements Listener {
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		Location from = event.getFrom();
		Location to = event.getTo();
		double fromy = from.getY();
		double toy = to.getY();
		if (!player.hasPermission("antihax.check.highjump")) {
			if (fromy - toy >= 2 || toy - fromy >= 2) {
				event.setCancelled(true);
				TellPlayer(player, "[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] You went up too quickly!");
			}
		}
	}
}
