package me.KeybordPiano459.AntiHax.checks.blockevents;

import me.KeybordPiano459.AntiHax.AntiHax;
import me.KeybordPiano459.AntiHax.checks.Check;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Reach implements Listener {
	private static AntiHax AntiHax = null;
	AntiHax plugin;
    public Reach(AntiHax plugin) {
        this.plugin = plugin;
    }
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockBreak(BlockBreakEvent event) {
		Block block = event.getBlock();
		Player player = event.getPlayer();
		double distance = player.getEyeLocation().distance(block.getLocation());
		if (!player.hasPermission("antihax.check.reach")) {
			if (distance > 5) {
				event.setCancelled(true);
				Check.PlayerKick(player, plugin, "Don't reach!", "tried to reach");
				AntiHax.violate(player, 5);
			} else {
				plugin.playerHackAmt.put(player.getName(), plugin.playerHackAmt.get(player.getName()) + 1);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockPlace(BlockPlaceEvent event) {
		Block block = event.getBlock();
		Player player = event.getPlayer();
		double distance = player.getEyeLocation().distance(block.getLocation());
		if (!player.hasPermission("antihax.check.reach")) {
			if (distance > 5) {
				event.setCancelled(true);
				Check.PlayerKick(player, plugin, "Don't reach!", "tried to reach");
				AntiHax.violate(player, 5);
			} else {
				plugin.playerHackAmt.put(player.getName(), plugin.playerHackAmt.get(player.getName()) + 1);
			}
		}
	}
	
	public void onFight(EntityDamageByEntityEvent event) {
		Player player = (Player) event.getEntity();
		double distance = player.getEyeLocation().distance(event.getEntity().getLocation());
		if (!player.hasPermission("antihax.check.reach")) {
			if (distance > 5) {
				event.setCancelled(true);
				Check.PlayerKick(player, plugin, "Don't reach!", "tried to reach");
				AntiHax.violate(player, 5);
			} else {
				plugin.playerHackAmt.put(player.getName(), plugin.playerHackAmt.get(player.getName()) + 1);
			}
		}
	}
}
