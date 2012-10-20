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
import me.KeybordPiano459.AntiHax.checks.chat.Cursing;
import me.KeybordPiano459.AntiHax.checks.fight.HitSelf;
import me.KeybordPiano459.AntiHax.checks.mods.CJB;
import me.KeybordPiano459.AntiHax.checks.mods.REI;
import me.KeybordPiano459.AntiHax.checks.mods.Zombe;
import me.KeybordPiano459.AntiHax.checks.movement.Flight;
import me.KeybordPiano459.AntiHax.checks.movement.SprintNoFood;
import me.KeybordPiano459.AntiHax.checks.movement.WalkOnWater;
import me.KeybordPiano459.AntiHax.util.Metrics;
import me.KeybordPiano459.AntiHax.util.UpdateEvent;
import me.KeybordPiano459.AntiHax.util.UpdateNotifier;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiHax extends JavaPlugin {
	
	public static boolean update;
	public Map<String, Integer> playerHackAmt = new HashMap<String, Integer>();
	private Date now = new Date();
	private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	public static int cursing;
	public static int flight;
	public static int hitself;
	public static int reach;
	public static int sprintnofood;
	public static int walkonwater;
	
	public void onEnable() {
		getLogger().info("AntiHax 0.3b has been enabled!");
		
		registerEvents();
		UpdateNotifier.updateNotifier();
		Bukkit.getServer().getPluginCommand("spy").setExecutor(new Spying());
		
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
		getLogger().info("AntiHax 0.3b has been disabled.");
	}
	
	public void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		//Actions
		pm.registerEvents(new Actions(this), this);
		pm.registerEvents(new PlayerLogin(this), this);
		
		//Block Event Checks
		pm.registerEvents(new Reach(this), this);
		
		//Chat
		pm.registerEvents(new Cursing(this), this);
		
		//Fight Checks
		pm.registerEvents(new HitSelf(this), this);
		
		//Misc
		if(TagAPI()){pm.registerEvents(new AdminTag(), this);}
		
		//Mods
		pm.registerEvents(new CJB(), this);
		pm.registerEvents(new REI(), this);
		pm.registerEvents(new Zombe(), this);
		
		//Movement Checks
		pm.registerEvents(new Flight(this), this);
		pm.registerEvents(new WalkOnWater(this), this);
		pm.registerEvents(new SprintNoFood(this), this);
		
		if(update){pm.registerEvents(new UpdateEvent(),this);}
	}
	
	public void violate(Player player, int violation) {
		playerHackAmt.put(player.getName(), playerHackAmt.get(player.getName()) - violation);
		if (playerHackAmt.get(player.getName()) == 0) {
			 player.kickPlayer("You have hacked too much.");
			 player.setBanned(true);
		}
	}
	
	public boolean TagAPI() {
		if (getServer().getPluginManager().getPlugin("TagAPI") == null) {
			return false;
		} else {
			return true;
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
