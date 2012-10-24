package me.KeybordPiano459.AntiHax.checks.inventory;

import me.KeybordPiano459.AntiHax.checks.Check;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class DropInv extends Check implements Listener {
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onItemDrop(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		ItemStack item = event.getItemDrop().getItemStack();
		int amount = item.getAmount();
		if (!player.hasPermission("antihax.check.dropinv")) {
			if (amount > 64) {
				event.setCancelled(true);
				TellPlayer(player, "[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] You dropped items too fast!");
			}
		}
	}
}
