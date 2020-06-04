//    Purgatory , a ban system for servers of Minecraft
//    Copyright (C) 2020  Deiutz
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <https://www.gnu.org/licenses/>.
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
