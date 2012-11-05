package me.KeybordPiano459.AntiHax.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class ExtendedConfig extends YamlConfiguration{

	@Override
	public boolean getBoolean(String path, boolean def){
		if(contains(path)){
			return (Boolean)get(path);
		} else {
			set(path, def);
			return def;
		}
	}
	
	@Override
	public String getString(String path, String def){
		if(contains(path)){
			return (String)get(path);
		} else {
			set(path, def);
			return def;
		}
	}
	
	@Override
	public int getInt(String path, int def){
		if(contains(path)){
			return (Integer)get(path);
		} else {
			set(path, def);
			return def;
		}
	}
	
	public static ExtendedConfig loadConfiguration(File file) {
        if (file == null) {
            throw new IllegalArgumentException("File cannot be null");
        }

        ExtendedConfig config = new ExtendedConfig();

        try {
            config.load(file);
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Cannot load " + file, ex);
        } catch (InvalidConfigurationException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Cannot load " + file , ex);
        }

        return config;
    }
}
