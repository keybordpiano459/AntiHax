package me.KeybordPiano459.AntiHax;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.kitteh.tag.PlayerReceiveNameTagEvent;

public class AdminTag implements Listener {
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onNameTagRecieve(PlayerReceiveNameTagEvent event) {
		if (!event.isModified()) {
			if (event.getNamedPlayer().hasPermission("antihax.admin")) {
				event.setTag(ChatColor.RED + event.getNamedPlayer().getDisplayName());
			}
		}
	}
}