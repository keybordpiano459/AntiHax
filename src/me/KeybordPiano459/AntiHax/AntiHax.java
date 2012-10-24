package me.KeybordPiano459.AntiHax;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import me.KeybordPiano459.AntiHax.checks.blockevents.Fullbright;
import me.KeybordPiano459.AntiHax.checks.blockevents.Nuker;
import me.KeybordPiano459.AntiHax.checks.blockevents.Reach;
import me.KeybordPiano459.AntiHax.checks.chat.Cursing;
import me.KeybordPiano459.AntiHax.checks.fight.Forcefield;
import me.KeybordPiano459.AntiHax.checks.fight.HitSelf;
import me.KeybordPiano459.AntiHax.checks.inventory.AutoEnchant;
import me.KeybordPiano459.AntiHax.checks.inventory.DropInv;
import me.KeybordPiano459.AntiHax.checks.mods.CJB;
import me.KeybordPiano459.AntiHax.checks.mods.REI;
import me.KeybordPiano459.AntiHax.checks.mods.Zombe;
import me.KeybordPiano459.AntiHax.checks.movement.Flight;
import me.KeybordPiano459.AntiHax.checks.movement.HighJump;
import me.KeybordPiano459.AntiHax.checks.movement.SprintNoFood;
import me.KeybordPiano459.AntiHax.checks.movement.WalkOnWater;
import me.KeybordPiano459.AntiHax.util.Metrics;
import me.KeybordPiano459.AntiHax.util.UpdateEvent;
import me.KeybordPiano459.AntiHax.util.UpdateNotifier;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiHax extends JavaPlugin {
	
	public static boolean update;
	private Date now = new Date();
	private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	public void onEnable() {
		getLogger().info("AntiHax 0.3b has been enabled!");
		
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
		getLogger().info("AntiHax 0.3b has been disabled.");
	}
	
	public void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		//Actions
		pm.registerEvents(new Actions(this), this);
		pm.registerEvents(new PlayerLogin(this), this);
		
		//Block Event Checks
		pm.registerEvents(new Nuker(this), this); // Also blocks 'smasher'
		pm.registerEvents(new Fullbright(this), this);
		pm.registerEvents(new HighJump(), this); // Also blocks 'step' and maybe 'spider'
		pm.registerEvents(new Reach(this), this);
		
		//Chat
		pm.registerEvents(new Cursing(this), this);
		
		//Fight Checks
		pm.registerEvents(new Forcefield(this), this);
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
		
		//Inventory
		pm.registerEvents(new AutoEnchant(this), this);
		pm.registerEvents(new DropInv(), this);
		
		if(update){pm.registerEvents(new UpdateEvent(),this);}
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
