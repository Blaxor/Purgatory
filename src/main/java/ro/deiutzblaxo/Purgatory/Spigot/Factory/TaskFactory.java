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
			plugin.getConfigManager().getTasksDataBase().set("Tasks." + uuid.toString() + "." + str, 0);
		}
		plugin.getConfigManager().saveTasksDataBases();
	}
	public void resetTasks(String uuid) {
		plugin.getConfigManager().loadTasksDataBase();
		for(String str : getTasks()) {
			plugin.getConfigManager().getTasksDataBase().set("Tasks." + uuid + "." + str, 0);
		}
		plugin.getConfigManager().saveTasksDataBases();


	}
	public void setProgress(String uuid , String TaskName , Integer newProgress) {
		plugin.getConfigManager().loadTasksDataBase();
		plugin.getConfigManager().getTasksDataBase().set("Tasks." + uuid + "." +TaskName , newProgress);
		plugin.getConfigManager().saveTasksDataBases();
	}
	public void removeTasks(String uuid) {
		plugin.getConfigManager().loadTasksDataBase();
		plugin.getConfigManager().getTasksDataBase().set("Tasks." + uuid, null);
		plugin.getConfigManager().saveTasksDataBases();

	}

	public Integer getProgress(String uuid , String TaskName) {
		plugin.getConfigManager().loadTasksDataBase();
		return plugin.getConfigManager().getTasksDataBase().getInt("Tasks." + uuid +"." + TaskName);
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
		return EntityType.valueOf(plugin.getConfigManager().getTasks().getString("Tasks." + TaskName + ".entity"));
	}

	public boolean existTasks(String uuid) {
		plugin.getConfigManager().loadTasksDataBase();
		if(plugin.getConfigManager().getTasksDataBase().contains("Tasks." + uuid)) {
			return true;
		}
		return false;

	}

}
