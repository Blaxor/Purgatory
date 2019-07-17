package ro.deiutzblaxo.Purgatory.Spigot.Factory;

import java.util.Set;

import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;

public class TaskFactory {
	MainSpigot plugin;
	public TaskFactory(MainSpigot main){
		plugin = main;
	}

	public Set<String> getTasks(){

		return plugin.getConfigManager().getTasks().getConfigurationSection("").getKeys(false);

	}
	public void test() {
		getTasks().toString();
	}

}
