package me.KeybordPiano459.AntiHax.checks.fight;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;

public class Heal implements Listener {
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onHeal(EntityRegainHealthEvent event) {
		Entity entity = event.getEntity();
		Player player = (Player) event.getEntity();
		RegainReason reason = event.getRegainReason();
		if (entity != player) {
			return;
		} else {
			if (reason != RegainReason.CUSTOM || reason != RegainReason.SATIATED /* Etc. */) {
				//Cancel event. ALSO - HOW DO I CHECK IF A CLIENT MOD IS REGENERATING THE PLAYER
			}
		}
	}
}
