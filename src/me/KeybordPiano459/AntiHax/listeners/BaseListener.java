package me.KeybordPiano459.AntiHax.listeners;

import me.KeybordPiano459.AntiHax.AntiHax;

import org.bukkit.event.Listener;

public class BaseListener implements Listener {
	protected AntiHax plugin;
	
	public BaseListener(AntiHax instance){
		plugin = instance;
	}
}
