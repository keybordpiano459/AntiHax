package me.KeybordPiano459.AntiHax.checks.blockevents;

import me.KeybordPiano459.AntiHax.AntiHax;
import me.KeybordPiano459.AntiHax.listeners.BaseListener;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Reach extends BaseListener {
	
    public Reach(AntiHax instance) {
    	super(instance);
    }
    
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockBreak(BlockBreakEvent event) {
		Block block = event.getBlock();
		Player player = event.getPlayer();
		double distance = player.getEyeLocation().distance(block.getLocation());
		if (!player.hasPermission("antihax.check.reach")) {
			if (distance > 6 && player.getGameMode() != GameMode.CREATIVE) {
				event.setCancelled(true);
				tellPlayer(player, "[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] You reached too far!");
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockPlace(BlockPlaceEvent event) {
		Block block = event.getBlock();
		Player player = event.getPlayer();
		double distance = player.getEyeLocation().distance(block.getLocation());
		if (!player.hasPermission("antihax.check.reach")) {
			if (distance > 6 && player.getGameMode() != GameMode.CREATIVE) {
				event.setCancelled(true);
				tellPlayer(player, "[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] You reached too far!");
			}
		}
	}
	
	public void onFight(EntityDamageByEntityEvent event) {
		Player player = (Player) event.getEntity();
		double distance = player.getEyeLocation().distance(event.getEntity().getLocation());
		if (!player.hasPermission("antihax.check.reach")) {
			if (distance > 6 && player.getGameMode() != GameMode.CREATIVE) {
				event.setCancelled(true);
				tellPlayer(player, "[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] You reached too far!");
			}
		}
	}
}
