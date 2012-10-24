package me.KeybordPiano459.AntiHax.checks.inventory;

import me.KeybordPiano459.AntiHax.AntiHax;
import me.KeybordPiano459.AntiHax.checks.Check;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;

public class AutoEnchant extends Check implements Listener {
	AntiHax plugin;
    public AutoEnchant(AntiHax plugin) {
        this.plugin = plugin;
    }
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPrepareEnchant(final PrepareItemEnchantEvent event) {
		final Player player = event.getEnchanter();
		final int[] enchantments = event.getExpLevelCostsOffered();
		if (!player.hasPermission("antihax.check.autoenchant")) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				public void run() {
					int[] enchantmentsafter = event.getExpLevelCostsOffered();
					if (enchantments != enchantmentsafter) {
						event.setCancelled(true);
						TellPlayer(player, "[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] You tried to auto-enchant your tool!");
					}
				}
			}, 20L);
		}
	}
}
