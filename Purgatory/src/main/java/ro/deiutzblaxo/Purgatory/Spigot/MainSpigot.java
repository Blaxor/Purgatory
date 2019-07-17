package ro.deiutzblaxo.Purgatory.Spigot;

import org.bukkit.plugin.java.JavaPlugin;

import ro.deiutzblaxo.Purgatory.Spigot.Factory.BanFactory;
import ro.deiutzblaxo.Purgatory.Spigot.Factory.WarningFactory;

public class MainSpigot extends JavaPlugin {
	private static MainSpigot instance;
	private ConfigManager configmanager;
	private BanFactory BanFactory;
	private WarningFactory WarningFactory;
	@Override
	public void onEnable() {
		instance = this;
		configmanager = new ConfigManager();
		BanFactory = new BanFactory();
		WarningFactory = new WarningFactory();


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
	public BanFactory getBanFactory() {
		return BanFactory;
	}
	public WarningFactory getWarningFactory() {
		return WarningFactory;
	}



}
