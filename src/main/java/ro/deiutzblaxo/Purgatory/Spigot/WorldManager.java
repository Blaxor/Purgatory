package ro.deiutzblaxo.Purgatory.Spigot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;

public class WorldManager {
	MainSpigot plugin;
	public WorldManager(MainSpigot main) {
		plugin = main;
		HookMultiverse();
		if(existWorlds()) {
			Bukkit.getConsoleSender().sendMessage(
					ChatColor.translateAlternateColorCodes('&', "["+plugin.getDescription().getName()+"]" + " &aATTENTION! Both worlds exist!  &9"));
		}else {
			Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
					"["+plugin.getDescription().getName()+"]" + " &4ATTENTION! One of the worlds don`t exist, please re-check the config.  &9"));
		}
	}
	public boolean isMultiverse() {
		if(plugin.getServer().getPluginManager().getPlugin("Multiverse-Core") != null) {
			return true;
		}
		return false;

	}
	public boolean isMultiverseEnabled() {
		if(plugin.getServer().getPluginManager().isPluginEnabled("Multiverse-Core")) {
			return true;
		}
		return false;
	}
	public boolean existWorlds() {
		if(plugin.getServer().getWorld(plugin.getConfig().getString("Worlds.Purgatory")) != null
				&& plugin.getServer().getWorld(plugin.getConfig().getString("Worlds.Default")) != null) {
			return true;
		}
		return false;
	}
	public void HookMultiverse() {
		if(isMultiverse() && !isMultiverseEnabled()) {
			plugin.getServer().getPluginManager().enablePlugin(plugin.getServer().getPluginManager().getPlugin("Multiverse-Core"));
		}

	}
	public World getPurgatory() {
		return	plugin.getServer().getWorld(plugin.getConfig().getString("Worlds.Purgatory"));

	}
	public World getDefault() {
		return	plugin.getServer().getWorld(plugin.getConfig().getString("Worlds.Default"));

	}


}
