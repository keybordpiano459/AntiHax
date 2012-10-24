package me.KeybordPiano459.AntiHax.checks.blockevents;

import java.util.HashMap;
import java.util.Map;

import me.KeybordPiano459.AntiHax.AntiHax;
import me.KeybordPiano459.AntiHax.checks.Check;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class Nuker extends Check implements Listener {
	AntiHax plugin;
    public Nuker(AntiHax plugin) {
        this.plugin = plugin;
    }
    
    Map<String, Integer> blocks = new HashMap<String, Integer>();
    
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockBreak(final BlockBreakEvent event) {
		Player player = event.getPlayer();
		if (!player.hasPermission("antihax.check.nuker")) {
			if (blocks.get(player.getName()) > 1) {
				TellPlayer(player, "[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] You broke blocks too quickly!");
			}
		}
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
		   	public void run() {
		   		Player player = event.getPlayer();
		   		if (!player.hasPermission("antihax.check.nuker")) {
		   			blocks.put(player.getName(), blocks.put(player.getName(), -1));
		   		}
		   	}
		}, 1L);
	}
}
