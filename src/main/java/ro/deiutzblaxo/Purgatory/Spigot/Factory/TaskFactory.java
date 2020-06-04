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
package ro.deiutzblaxo.Purgatory.Spigot.Factory;

import java.util.Set;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;

public class TaskFactory {
	MainSpigot plugin;
	public TaskFactory(MainSpigot main){
		plugin = main;
		plugin.getConfigManager().loadTasks();
	}

	public Set<String> getTasks(){
		plugin.getConfigManager().loadTasks();
		return plugin.getConfigManager().getTasks().getConfigurationSection("Tasks").getKeys(false);

	}
	public void setTasks(UUID uuid) {
		plugin.getConfigManager().loadTasksDataBase();
		plugin.getConfigManager().loadTasks();
		for(String str : getTasks()) {
			plugin.getConfigManager().getTasksDataBase().set(  uuid.toString() + "." + str, 0);
		}
		plugin.getConfigManager().saveTasksDataBases();
	}
	public void resetTasks(String uuid) {
		plugin.getConfigManager().loadTasksDataBase();
		for(String str : getTasks()) {
			plugin.getConfigManager().getTasksDataBase().set( uuid + "." + str, 0);
		}
		plugin.getConfigManager().saveTasksDataBases();


	}
	public void setProgress(String uuid , String TaskName , Integer newProgress) {
		plugin.getConfigManager().loadTasksDataBase();
		plugin.getConfigManager().getTasksDataBase().set( uuid + "." +TaskName , newProgress);
		plugin.getConfigManager().saveTasksDataBases();
	}
	public void removeTasks(UUID uuid) {
		plugin.getConfigManager().loadTasksDataBase();
		plugin.getConfigManager().getTasksDataBase().set( uuid.toString(), null);
		plugin.getConfigManager().saveTasksDataBases();

	}

	public Integer getProgress(String uuid , String TaskName) {
		plugin.getConfigManager().loadTasksDataBase();
		return plugin.getConfigManager().getTasksDataBase().getInt( uuid +"." + TaskName);
	}

	public Integer getCount(String TaskName) {
		return plugin.getConfigManager().getTasks().getInt("Tasks." + TaskName + ".count");

	}

	public String getType(String TaskName) {
		return plugin.getConfigManager().getTasks().getString("Tasks." + TaskName + ".type");
	}

	public Boolean isMaterial(String string, Material material) {
		plugin.getConfigManager().loadTasks();
		if(material == Material.matchMaterial(plugin.getConfigManager().getTasks().getString("Tasks." + string + ".block"))) {
			return true;
		}
		return false;
	}

	public EntityType getEntityType(String TaskName) {
		return EntityType.valueOf(plugin.getConfigManager().getTasks().getString("Tasks." + TaskName + ".entity").toUpperCase());
	}

	public boolean existTasks(String uuid) {
		plugin.getConfigManager().loadTasksDataBase();
		if(plugin.getConfigManager().getTasksDataBase().contains( uuid)) {
			return true;
		}
		return false;

	}

}
