package me.KeybordPiano459.AntiHax;

import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

public class Actions implements Listener {
	AntiHax plugin;
    public Actions(AntiHax plugin) {
        this.plugin = plugin;
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
		plugin.logAction(player.getDisplayName() + " has changed gamemodes to " + gamemode);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onWorldChange(PlayerChangedWorldEvent event) {
		Player player = event.getPlayer();
		World world = player.getWorld();
		plugin.logAction(player.getDisplayName() + " has teleported to the world " + world);
	}
}