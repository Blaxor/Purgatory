package ro.deiutzblaxo.Purgatory.Spigot;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import ro.deiutzblaxo.Purgatory.Spigot.Commands.BanCommand;
import ro.deiutzblaxo.Purgatory.Spigot.Factory.BanFactory;
import ro.deiutzblaxo.Purgatory.Spigot.Factory.TaskFactory;
import ro.deiutzblaxo.Purgatory.Spigot.Factory.WarningFactory;

public class MainSpigot extends JavaPlugin {
	private static MainSpigot instance;
	private ConfigManager configmanager;
	private BanFactory BanFactory;
	private WarningFactory WarningFactory;
	private TaskFactory TaskFactory;
	private CommandMap commandMap;
	@Override
	public void onEnable() {
		instance = this;
		configmanager = new ConfigManager();
		BanFactory = new BanFactory();
		WarningFactory = new WarningFactory();
		TaskFactory = new TaskFactory(this);

		getConfigManager().createConfigs();
		loadCommandMap();

		this.commandMap.register("purgatory", new BanCommand("ban", this));



	}
	@Override
	public void onDisable() {

	}
	public Boolean isBungeeEnabled() {
		return this.getConfig().getBoolean("BungeeCord");
	}

	private void loadCommandMap() {
		try {
			if (Bukkit.getPluginManager() instanceof SimplePluginManager) {
				Field f = SimplePluginManager.class.getDeclaredField("commandMap");

				f.setAccessible(true);
				this.commandMap = (CommandMap) f.get(Bukkit.getPluginManager());
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
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
	public TaskFactory getTaskFactory() {
		return TaskFactory;
	}



}
