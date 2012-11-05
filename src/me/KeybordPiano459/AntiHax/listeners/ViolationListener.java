package me.KeybordPiano459.AntiHax.listeners;

import java.io.File;
import java.io.IOException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.KeybordPiano459.AntiHax.AntiHax;
import me.KeybordPiano459.AntiHax.util.ExtendedConfig;

public class ViolationListener extends BaseListener{

	public ViolationListener(AntiHax instance) {
		super(instance);
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = false)
	public void onPlayerJoin(PlayerJoinEvent event){
		File f = new File("plugins/AntiHax/violations.yml");
		ExtendedConfig config = ExtendedConfig.loadConfiguration(f);
		
		plugin.getViolations().put(event.getPlayer().getName(), config.getInt(event.getPlayer().getName(), 50));
	}
	
	
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
	public void onPlayerQuit(PlayerQuitEvent event){
		File f = new File("plugins/AntiHax/violations.yml");
		ExtendedConfig config = ExtendedConfig.loadConfiguration(f);
		
		config.set(event.getPlayer().getName(), plugin.getViolations().get(event.getPlayer().getName()));
		
		try {
			config.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
