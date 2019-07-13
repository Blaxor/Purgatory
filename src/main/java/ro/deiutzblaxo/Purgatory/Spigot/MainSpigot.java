package ro.deiutzblaxo.Purgatory.Spigot;

import org.bukkit.plugin.java.JavaPlugin;

public class MainSpigot extends JavaPlugin {
	private static MainSpigot instance;
	private ConfigManager configmanager;
	@Override
	public void onEnable() {
		instance = this;
		configmanager = new ConfigManager();
		getConfigManager().createConfigs();



	}
	@Override
	public void onDisable() {

	}
	public Boolean isBungeeEnabled() {
		return this.getConfig().getBoolean("BungeeCord");
	}
	public static MainSpigot getInstance() {
		return instance;
	}
	public ConfigManager getConfigManager() {
		return configmanager;
	}


}
