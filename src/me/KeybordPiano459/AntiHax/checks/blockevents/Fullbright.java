package me.KeybordPiano459.AntiHax.checks.blockevents;

import me.KeybordPiano459.AntiHax.AntiHax;
import me.KeybordPiano459.AntiHax.checks.Check;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class Fullbright extends Check implements Listener {
	AntiHax plugin;
    public Fullbright(AntiHax plugin) {
        this.plugin = plugin;
    }
    
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		int light = block.getLightLevel();
		if (!player.hasPermission("antihax.check.fullbright")) {
			if (light > 2) {
				event.setCancelled(true);
				TellPlayer(player, "[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] It's too dark here. You should probably place a torch or two.");
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		int light = block.getLightLevel();
		if (!player.hasPermission("antihax.check.fullbright")) {
			if (light > 3) {
				event.setCancelled(true);
				TellPlayer(player, "[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] It's too dark here. You should probably place a torch or two.");
			}
		}
	}
}
