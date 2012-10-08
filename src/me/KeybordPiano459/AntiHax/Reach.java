package me.KeybordPiano459.AntiHax;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class Reach implements Listener {
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
				player.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] " + ChatColor.RED + "Don't use reach! The admins have been warned.");
				Player[] players = plugin.getServer().getOnlinePlayers();
				for (Player p : players) {
					if (p.isOp() || p.hasPermission("antihax.notify")) {
						p.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] " + ChatColor.RED + player.getDisplayName() + " has reached!");
					}
				}
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
				player.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] " + ChatColor.RED + "Don't use reach! The admins have been warned.");
				Player[] players = plugin.getServer().getOnlinePlayers();
				for (Player p : players) {
					if (p.isOp() || p.hasPermission("antihax.notify")) {
						p.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] " + ChatColor.RED + player.getDisplayName() + " has reached!");
					}
				}
			}
		}
	}
}
