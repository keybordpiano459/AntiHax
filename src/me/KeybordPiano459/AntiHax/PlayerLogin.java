package me.KeybordPiano459.AntiHax;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLogin implements Listener {
	AntiHax plugin;
    public PlayerLogin(AntiHax plugin) {
        this.plugin = plugin;
    }
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerLogin(PlayerLoginEvent event) {
		Player player = event.getPlayer();
		plugin.logPlayerLogin(player.getDisplayName() + " joined.");
		if (player.hasPlayedBefore() == false) {
			plugin.playerHackAmt.put(player.getName(), plugin.playerHackAmt.get(player.getName()) + 10);
		}
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
