package ro.deiutzblaxo.Purgatory.Spigot.Factory;

import java.util.Set;

import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;

public class TaskFactory {
	MainSpigot plugin;
	public TaskFactory(MainSpigot main){
		plugin = main;
	}

	public Set<String> getTasks(){
		plugin.getConfigManager().loadTasks();
		return plugin.getConfigManager().getTasks().getConfigurationSection("Tasks").getKeys(false);

	}
	public void setTasks(String uuid) {
		plugin.getConfigManager().loadTasksDataBase();
		plugin.getConfigManager().loadTasks();
		for(String str : getTasks()) {
			plugin.getConfigManager().getTasksDataBase().set("Tasks." + uuid + "." + str, 0);
		}
		plugin.getConfigManager().saveTasksDataBases();
	}
	public void resetTasks(String uuid) {

	}
	public void removeTasks(String uuid) {

	}
	public void getProgress(String uuid , String TaskName) {

	}
	public void getCount(String TaskName) {

	}

}
