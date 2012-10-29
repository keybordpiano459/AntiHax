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
<<<<<<< HEAD
=======
import org.bukkit.event.player.PlayerKickEvent;
>>>>>>> Improved Nuker Check
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Nuker extends Check implements Listener {
	AntiHax plugin;
    public Nuker(AntiHax plugin) {
        this.plugin = plugin;
    }
    
    Map<String, Integer> blocks = new HashMap<String, Integer>();
    
    @EventHandler(priority = EventPriority.MONITOR)
<<<<<<< HEAD
    public void onPlayerLogin(PlayerLoginEvent event){
    	String player = event.getPlayer().getName();
    	blocks.put(player, 0);
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerLogoff(PlayerQuitEvent event){
    	String player = event.getPlayer().getName();
=======
    public void onPlayerLogin(PlayerLoginEvent event) {
    	Player player = event.getPlayer();
    	blocks.put(player.getName(), 0);
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerLogoff(PlayerQuitEvent event) {
    	Player player = event.getPlayer();
    	blocks.remove(player);
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerKick(PlayerKickEvent event) {
    	Player player = event.getPlayer();
>>>>>>> Improved Nuker Check
    	blocks.remove(player);
    }
    
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockBreak(final BlockBreakEvent event) {
		Player player = event.getPlayer();
		blocks.put(player.getName(), blocks.get(player.getName())+1);
		if (!player.hasPermission("antihax.check.nuker")) {
			if (blocks.get(player.getName()) > 1) {
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
<<<<<<< HEAD
	
}
=======
}
>>>>>>> Improved Nuker Check
