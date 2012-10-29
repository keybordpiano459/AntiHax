package me.KeybordPiano459.AntiHax;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import me.KeybordPiano459.AntiHax.checks.*;
import me.KeybordPiano459.AntiHax.checks.blockevents.*;
import me.KeybordPiano459.AntiHax.checks.chat.*;
import me.KeybordPiano459.AntiHax.checks.inventory.*;
import me.KeybordPiano459.AntiHax.checks.mods.*;
import me.KeybordPiano459.AntiHax.checks.movement.*;
import me.KeybordPiano459.AntiHax.util.*;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiHax extends JavaPlugin {
	
	public static boolean update;
	private Date now = new Date();
	private SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
	
	public void onEnable() {
		getLogger().info("AntiHax 0.31b has been enabled!");
		
		registerEvents();
		UpdateNotifier.updateNotifier();
		//Bukkit.getServer().getPluginCommand("check").setExecutor(new ViolationCommand());
		//Bukkit.getServer().getPluginCommand("spy").setExecutor(new CommandSpy());
		
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
		getLogger().info("AntiHax 0.31b has been disabled.");
	}
	
	public void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		//Actions
		pm.registerEvents(new Actions(this), this);
		pm.registerEvents(new PlayerLogin(this), this);
		
		//Block Events
		pm.registerEvents(new Nuker(this), this); // Also blocks 'smasher'
		pm.registerEvents(new Fullbright(this), this);
		pm.registerEvents(new HighJump(), this); // Also blocks 'step'
		pm.registerEvents(new Reach(this), this);
		
		//Chat
		pm.registerEvents(new Cursing(this), this);
		
		//Fight
		//pm.registerEvents(new Forcefield(this), this); Figure out why event is cancelled if player hits mob directly
		//pm.registerEvents(new HitSelf(this), this); Figure out why whenever a bow hits another entity it prints a stack trace
		
		//Misc
		pm.registerEvents(new ViolationLogin(), this);
		if(TagAPI()){pm.registerEvents(new AdminTag(), this);}
		if(update){pm.registerEvents(new UpdateEvent(),this);}
		
		//Mods
		pm.registerEvents(new CJB(), this);
		pm.registerEvents(new REI(), this);
		pm.registerEvents(new Zombe(), this);
		
		//Movement
		pm.registerEvents(new Flight(this), this);
		pm.registerEvents(new WalkOnWater(this), this);
		pm.registerEvents(new SprintNoFood(this), this);
		
		//Inventory
		pm.registerEvents(new AutoEnchant(this), this);
		pm.registerEvents(new DropInv(), this);
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
