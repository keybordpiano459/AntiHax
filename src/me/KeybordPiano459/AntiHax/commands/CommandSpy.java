package me.KeybordPiano459.AntiHax.commands;

import me.KeybordPiano459.AntiHax.AntiHax;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class CommandSpy extends BaseCommand {
	
	public CommandSpy(AntiHax instance) {
		super(instance);
	}

	@Override
	public void Run(Player player, Server server, String[] args){
		if(args.length == 0){
			if(plugin.getHiddenPlayers().get(player.getName()) == false){
				for(Player p : server.getOnlinePlayers()){
					p.hidePlayer(player);
				}
				
				player.setFlying(true);
				plugin.getHiddenPlayers().put(player.getName(), true);
				
				player.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] You are now hidden and flying!");
			} else {
				for(Player p : server.getOnlinePlayers()){
					p.showPlayer(player);
				}

				player.setFlying(false);
				plugin.getHiddenPlayers().put(player.getName(), false);
			}
		} else if(args.length == 1){
			Player targetplayer = server.getPlayer(args[0]);
			
			if(targetplayer == null){
				player.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] That player is currently not online!");
			}
			
			if(plugin.getHiddenPlayers().get(player.getName()) == false){
				for(Player p : server.getOnlinePlayers()){
					p.hidePlayer(player);
				}
				
				player.setFlying(true);
				plugin.getHiddenPlayers().put(player.getName(), true);
				
				player.sendMessage("[" + ChatColor.RED + "AntiHax" + ChatColor.RESET + "] You are now hidden and flying!");
			}
			
			player.teleport(targetplayer.getLocation());
		}
	}
}
