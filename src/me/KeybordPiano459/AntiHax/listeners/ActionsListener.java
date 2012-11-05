package me.KeybordPiano459.AntiHax.listeners;

import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.KeybordPiano459.AntiHax.AntiHax;

public class ActionsListener extends BaseListener{

	public ActionsListener(AntiHax instance) {
		super(instance);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onGamemodeChange(PlayerGameModeChangeEvent event) {
		Player player = event.getPlayer();
		GameMode playergamemode = player.getGameMode();
		String gamemode = "NULL";
		if (playergamemode == GameMode.SURVIVAL) {
			gamemode = "survival";
		} else if (playergamemode == GameMode.CREATIVE) {
			gamemode = "creative";
		} else if (playergamemode == GameMode.ADVENTURE) {
			gamemode = "adventure";
		}
		plugin.logAction(player.getDisplayName() + " has changed their gamemode to " + gamemode);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onWorldChange(PlayerChangedWorldEvent event) {
		Player player = event.getPlayer();
		World world = player.getWorld();
		plugin.logAction(player.getDisplayName() + " has teleported to the world " + world);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerLogin(PlayerLoginEvent event) {
		Player player = event.getPlayer();
		plugin.logPlayerLogin(player.getDisplayName() + " joined.");
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerLeave(PlayerKickEvent event) {
		Player player = event.getPlayer();
		plugin.logPlayerLogin(player.getDisplayName() + " was kicked.");
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		plugin.logPlayerLogin(player.getDisplayName() + " left.");
	}
}
