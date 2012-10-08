package me.KeybordPiano459.AntiHax;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiHax extends JavaPlugin {
	public void onEnable() {
		getLogger().info("AntiHax 1.0 has been enabled!");
	}
	
	public void onDisable() {
		getLogger().info("AntiHax 1.0 has been disabled.");
	}
	
	public void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new Reach(this), this);
	}
}
