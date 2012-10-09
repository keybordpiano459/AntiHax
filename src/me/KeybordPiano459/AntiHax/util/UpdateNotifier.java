package me.KeybordPiano459.AntiHax.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import me.KeybordPiano459.AntiHax.AntiHax;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class UpdateNotifier {
	public static void updateNotifier() {
		Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("AntiHax");
		if (plugin.getConfig().getBoolean("update-notifier")) {
			plugin.getLogger().info("Checking for an update...");
			try {
				URL url = new URL("https://raw.github.com/keybordpiano459/AntiHax/master/version.txt");
				BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
				String string;
				while ((string = in.readLine()) != null) {
					int currentVersion = Integer.parseInt(Bukkit.getServer().getPluginManager().getPlugin("AntiHax").getDescription().getVersion().replace(".", ""));
					int newVersion = Integer.parseInt(string.replace(".", ""));
					if (currentVersion != newVersion) {
						plugin.getLogger().severe("There is a new version of AntiHax!");
						AntiHax.update = true;
					} else {
						plugin.getLogger().info("AntiHax is currently up to date.");
					}
				}
				in.close();
			} catch (MalformedURLException e) {
				plugin.getLogger().warning("The AntiHax update URL is invalid!");
			} catch (IOException e) {
				plugin.getLogger().warning("Bad read/write!");
			}
		}
	}
}
