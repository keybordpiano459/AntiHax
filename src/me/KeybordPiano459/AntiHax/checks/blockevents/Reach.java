package me.KeybordPiano459.AntiHax.checks.blockevents;

import me.KeybordPiano459.AntiHax.AntiHax;
import me.KeybordPiano459.AntiHax.checks.Check;

import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

<<<<<<< HEAD
public class Reach implements Listener {
=======
public class Reach extends Check implements Listener {
	//private static AntiHax AntiHax = null;
>>>>>>> Cleaned Up Code
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
<<<<<<< HEAD
			if (player.getGameMode() == GameMode.SURVIVAL) {
				if (distance > 5) {
					event.setCancelled(true);
					Check.PlayerKick(player, plugin, "Don't reach!", "tried to reach");
					//AntiHax.violate(player, 5);
				} else {
					//plugin.playerHackAmt.put(player.getName(), plugin.playerHackAmt.get(player.getName()) + 1);
				}
=======
			if (distance > 5 && player.getGameMode() != GameMode.CREATIVE) {
				event.setCancelled(true);
				PlayerKick(player, plugin, "Don't reach!", "tried to reach");
				//AntiHax.violate(player, 5);
			} else {
				//plugin.playerHackAmt.put(player.getName(), plugin.playerHackAmt.get(player.getName()) + 1);
>>>>>>> Cleaned Up Code
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockPlace(BlockPlaceEvent event) {
		Block block = event.getBlock();
		Player player = event.getPlayer();
		double distance = player.getEyeLocation().distance(block.getLocation());
		if (!player.hasPermission("antihax.check.reach")) {
<<<<<<< HEAD
			if (player.getGameMode() == GameMode.SURVIVAL) {
				if (distance > 5) {
					event.setCancelled(true);
					Check.PlayerKick(player, plugin, "Don't reach!", "tried to reach");
					//AntiHax.violate(player, 5);
				} else {
					//plugin.playerHackAmt.put(player.getName(), plugin.playerHackAmt.get(player.getName()) + 1);
				}
=======
			if (distance > 5 && player.getGameMode() != GameMode.CREATIVE) {
				event.setCancelled(true);
				PlayerKick(player, plugin, "Don't reach!", "tried to reach");
				//AntiHax.violate(player, 5);
			} else {
				//plugin.playerHackAmt.put(player.getName(), plugin.playerHackAmt.get(player.getName()) + 1);
>>>>>>> Cleaned Up Code
			}
		}
	}
	
	public void onFight(EntityDamageByEntityEvent event) {
		Player player = (Player) event.getEntity();
		double distance = player.getEyeLocation().distance(event.getEntity().getLocation());
		if (!player.hasPermission("antihax.check.reach")) {
<<<<<<< HEAD
			if (player.getGameMode() == GameMode.SURVIVAL) {
				if (distance > 5) {
					event.setCancelled(true);
					Check.PlayerKick(player, plugin, "Don't reach!", "tried to reach");
					//AntiHax.violate(player, 5);
				} else {
					//plugin.playerHackAmt.put(player.getName(), plugin.playerHackAmt.get(player.getName()) + 1);
				}
=======
			if (distance > 5 && player.getGameMode() != GameMode.CREATIVE) {
				event.setCancelled(true);
				PlayerKick(player, plugin, "Don't reach!", "tried to reach");
				//AntiHax.violate(player, 5);
			} else {
				//plugin.playerHackAmt.put(player.getName(), plugin.playerHackAmt.get(player.getName()) + 1);
>>>>>>> Cleaned Up Code
			}
		}
	}
}
