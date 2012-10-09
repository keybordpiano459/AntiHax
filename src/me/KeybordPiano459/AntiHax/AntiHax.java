package me.KeybordPiano459.AntiHax;

import java.io.IOException;

import me.KeybordPiano459.AntiHax.util.Metrics;
import me.KeybordPiano459.AntiHax.util.UpdateEvent;
import me.KeybordPiano459.AntiHax.util.UpdateNotifier;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiHax extends JavaPlugin {
	
	public static boolean update;
	public int version = Integer.parseInt(Bukkit.getServer().getPluginManager().getPlugin("AntiHax").getDescription().getVersion());
	
	public void onEnable() {
		getLogger().info("AntiHax " + version + " has been enabled!");
		
		registerEvents();
		UpdateNotifier.updateNotifier();
		getConfig().options().copyDefaults(true);
	    saveConfig();
		
		try {
		    Metrics metrics = new Metrics(this);
		    metrics.start();
		} catch (IOException e) {
		    // Failed to submit stats :-(
		}
	}
	
	public void onDisable() {
		getLogger().info("AntiHax " + version + " has been disabled.");
	}
	
	public void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new Reach(this), this);
		
		if (update) {
			pm.registerEvents(new UpdateEvent(), this);
		}
	}
}
