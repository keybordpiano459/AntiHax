package me.KeybordPiano459.AntiHax.checks.chat;

import me.KeybordPiano459.AntiHax.AntiHax;
import me.KeybordPiano459.AntiHax.checks.Check;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Cursing extends Check implements Listener {
	AntiHax plugin;
	public Cursing(AntiHax plugin) {
		this.plugin = plugin;
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		String msg = event.getMessage().toLowerCase();
		for(String badword : plugin.getConfig().getStringList("blacklisted-words")) {
			if (msg.contains(" " + badword + " ")) {
				event.setCancelled(true);
				PlayerKick(player, plugin, "No Cursing!", "tried to speak like a %$#!@!");
				AntiHax.cursing++;
			}
		}
	}
}
