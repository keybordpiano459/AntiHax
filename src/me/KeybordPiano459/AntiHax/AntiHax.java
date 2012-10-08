package me.KeybordPiano459.AntiHax;

import java.io.IOException;

import me.KeybordPiano459.AntiHax.util.Metrics;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiHax extends JavaPlugin {
	public void onEnable() {
		getLogger().info("AntiHax 1.0 has been enabled!");
		
		registerEvents();
		
		try {
		    Metrics metrics = new Metrics(this);
		    metrics.start();
		} catch (IOException e) {
		    // Failed to submit stats :-(
		}
	}
	
	public void onDisable() {
		getLogger().info("AntiHax 1.0 has been disabled.");
	}
	
	public void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new Reach(this), this);
	}
}
