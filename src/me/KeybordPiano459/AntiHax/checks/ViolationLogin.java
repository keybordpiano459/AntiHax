package me.KeybordPiano459.AntiHax.checks;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ViolationLogin extends Check implements Listener {
		@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled=true)
		public void onJoin(PlayerJoinEvent event) {
			Player player = event.getPlayer();
			if (player.hasPlayedBefore() == false) {
				violation.put(player.getName(), 50);
			}
		}
	}