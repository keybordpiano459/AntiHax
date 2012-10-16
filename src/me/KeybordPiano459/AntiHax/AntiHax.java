package me.KeybordPiano459.AntiHax;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import me.KeybordPiano459.AntiHax.checks.blockevents.Reach;
import me.KeybordPiano459.AntiHax.checks.movement.Flight;
import me.KeybordPiano459.AntiHax.checks.movement.SprintNoFood;
import me.KeybordPiano459.AntiHax.checks.movement.WalkOnWater;
import me.KeybordPiano459.AntiHax.util.Metrics;
import me.KeybordPiano459.AntiHax.util.UpdateEvent;
import me.KeybordPiano459.AntiHax.util.UpdateNotifier;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiHax extends JavaPlugin {
	
	public static boolean update;
	private Date now = new Date();
	private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	public Map<String, Integer> playerHackAmt = new HashMap<String, Integer>();
	
	public void onEnable() {
		getLogger().info("AntiHax 0.21b has been enabled!");
		
		//getCommands();
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
		getLogger().info("AntiHax 0.21b has been disabled.");
	}
	
	public void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		//Actions
		pm.registerEvents(new Actions(this), this);
		pm.registerEvents(new PlayerLogin(this), this);
		
		//Block Event Checks
		pm.registerEvents(new Reach(this), this);
		
		//Movement Checks
		pm.registerEvents(new Flight(this), this);
		pm.registerEvents(new WalkOnWater(this), this);
		pm.registerEvents(new SprintNoFood(this), this);
		
		if(update){pm.registerEvents(new UpdateEvent(),this);}
	}
	
	/*public void getCommands() {
		getCommand("check", new CommandCheck(this));
	}

	public void getCommand(String command, CommandExecutor commandexecutor) {
		Bukkit.getServer().getPluginCommand(command).setExecutor(commandexecutor);
	}*/
	
	public void violate(Player player, int violation) {
		playerHackAmt.put(player.getName(), playerHackAmt.get(player.getName()) - violation);
		if (playerHackAmt.get(player.getName()) == 0) {
			 player.kickPlayer("You have hacked too much.");
			 player.setBanned(true);
		}
	}
	
	public void logCheat(String message) {
		logToFile(message, "cheat.txt");
	}
	
	public void logAction(String message) {
		logToFile(message, "action.txt");
	}
	
	public void logPlayerLogin(String message) {
		logToFile(message, "login.txt");
	}
	
	public void logToFile(String message, String filename) {
		try {
			File dataFolder = getDataFolder();
			if(!dataFolder.exists()) {
            	dataFolder.mkdir();
			}
			File saveTo = new File(getDataFolder(), filename);
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
