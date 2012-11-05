package me.KeybordPiano459.AntiHax.checks.chat;

import me.KeybordPiano459.AntiHax.AntiHax;
import me.KeybordPiano459.AntiHax.listeners.BaseListener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Cursing extends BaseListener {
	
	public Cursing(AntiHax instance) {
		super(instance);
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		String msg = event.getMessage().toLowerCase();
		if (!player.hasPermission("antihax.check.curse")) {
			for(String badword : plugin.getConfig().getStringList("blacklisted-words")) {
				if (msg.contains(" " + badword + " ")) {
					event.setCancelled(true);
					tellPlayer(player, "[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] Cursing isn't allowed!");
				}
			}
		}
	}
}
