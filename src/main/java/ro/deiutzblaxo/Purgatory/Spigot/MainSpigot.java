package ro.deiutzblaxo.Purgatory.Spigot;

import org.bukkit.plugin.java.JavaPlugin;

public class MainSpigot extends JavaPlugin {
	private static MainSpigot instance;

	@Override
	public void onEnable() {
		instance = this;

	}
	@Override
	public void onDisable() {

	}
	public static MainSpigot getInstance() {
		return instance;
	}

}
