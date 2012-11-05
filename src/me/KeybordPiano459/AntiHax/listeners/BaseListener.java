package me.KeybordPiano459.AntiHax.listeners;

import me.KeybordPiano459.AntiHax.AntiHax;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class BaseListener implements Listener {
	protected AntiHax plugin;
	
	public BaseListener(AntiHax instance){
		plugin = instance;
	}
	
	public void kickPlayer(Player player, String playerMessage, String adminMessage){
		Server server = player.getServer();
		
		plugin.getViolations().put(player.getName(), plugin.getViolations().get(player.getName()) - 10);
		
		server.broadcastMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] " + player.getDisplayName() + " was kicked for hacking.");
	
		for(Player p : server.getOnlinePlayers()){
			if(player.hasPermission("antihax.notify") || player.isOp()){
				p.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] " + ChatColor.RED + adminMessage);	
			}
		}
		
		if(plugin.getViolations().get(player.getName()) <= 0){
			player.setBanned(true);
		}
		
		player.kickPlayer(playerMessage);
	}
	
	public void tellPlayer(Player player, String msg){
		Server server = player.getServer();
		
		player.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] " + msg);
		
		for (Player p : server.getOnlinePlayers()) {
			if (p.isOp() || p.hasPermission("antihax.notify")) {
				p.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] " + player.getDisplayName() + " tried to hack."); //Send admins message
			}
		}
		
		plugin.getViolations().put(player.getName(), plugin.getViolations().get(player.getName()));
		
		if(plugin.getViolations().get(player.getName()) <= 0){
			player.setBanned(true);
			player.kickPlayer(ChatColor.RED + "You got to many violations!");
		}
	}
}
