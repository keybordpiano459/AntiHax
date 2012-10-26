package me.KeybordPiano459.AntiHax.checks.blockevents;

import java.util.HashMap;
import java.util.Map;

import me.KeybordPiano459.AntiHax.AntiHax;
import me.KeybordPiano459.AntiHax.checks.Check;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Nuker extends Check implements Listener {
	AntiHax plugin;
    public Nuker(AntiHax plugin) {
        this.plugin = plugin;
    }
    
    Map<String, Integer> blocks = new HashMap<String, Integer>();
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerLogin(PlayerLoginEvent event){
    	String player = event.getPlayer().getName();
    	blocks.put(player, 0);
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerLogoff(PlayerQuitEvent event){
    	String player = event.getPlayer().getName();
    	blocks.remove(player);
    }
    
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockBreak(final BlockBreakEvent event) {
		Player player = event.getPlayer();
		blocks.put(player.getName(), blocks.get(player.getName())+1);
		if (!player.hasPermission("antihax.check.nuker")) {
			if (blocks.get(player.getName()) > 1 && player.getGameMode() != GameMode.CREATIVE) {
				TellPlayer(player, "[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] You broke blocks too quickly!");
			}
			if (blocks.get(player.getName()) > 5 && player.getGameMode() == GameMode.CREATIVE){
				TellPlayer(player, "[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] You broke blocks too quickly!");
			}
		}
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
		   	public void run() {
		   		Player player = event.getPlayer();
		   		if (!player.hasPermission("antihax.check.nuker")) {
		   			blocks.put(player.getName(), 0);
		   		}
		   	}
		}, 1L);
	}
}
