package me.KeybordPiano459.AntiHax;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import me.KeybordPiano459.AntiHax.checks.blockevents.Reach;
import me.KeybordPiano459.AntiHax.checks.movement.SprintNoFood;
import me.KeybordPiano459.AntiHax.checks.movement.WalkOnWater;
import me.KeybordPiano459.AntiHax.util.Metrics;
import me.KeybordPiano459.AntiHax.util.UpdateEvent;
import me.KeybordPiano459.AntiHax.util.UpdateNotifier;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiHax extends JavaPlugin {
	
	public static boolean update;
	public int version = Integer.parseInt(Bukkit.getServer().getPluginManager().getPlugin("AntiHax").getDescription().getVersion());
	private Date now = new Date();
	private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	public void onEnable() {
		getLogger().info("AntiHax " + version + " has been enabled!");
		
		registerEvents();
		UpdateNotifier.updateNotifier();
		
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
		//Actions
		pm.registerEvents(new Actions(this), this);
		pm.registerEvents(new PlayerLogin(this), this);
		
		//Block Event Checks
		pm.registerEvents(new Reach(this), this);
		
		//Movement Checks
		pm.registerEvents(new WalkOnWater(this), this);
		pm.registerEvents(new SprintNoFood(this), this);
		
		if(update){pm.registerEvents(new UpdateEvent(),this);}
	}
	
	public void logCheat(String message) {
		try {
			File dataFolder = getDataFolder();
			if(!dataFolder.exists()) {
            	dataFolder.mkdir();
			}
			File saveTo = new File(getDataFolder(), "hack.txt");
			if (!saveTo.exists()) {
				saveTo.createNewFile();
			}
			FileWriter fw = new FileWriter(saveTo, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("[" + format.format(now) + "] " + message);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void logAction(String message) {
		try {
			File dataFolder = getDataFolder();
			if(!dataFolder.exists()) {
            	dataFolder.mkdir();
			}
			File saveTo = new File(getDataFolder(), "action.txt");
			if (!saveTo.exists()) {
				saveTo.createNewFile();
			}
			FileWriter fw = new FileWriter(saveTo, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("[" + format.format(now) + "] " + message);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void logPlayerLogin(String message) {
		try {
			File dataFolder = getDataFolder();
			if(!dataFolder.exists()) {
            	dataFolder.mkdir();
			}
			File saveTo = new File(getDataFolder(), "login.txt");
			if (!saveTo.exists()) {
				saveTo.createNewFile();
			}
			FileWriter fw = new FileWriter(saveTo, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("[" + format.format(now) + "] " + message);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
