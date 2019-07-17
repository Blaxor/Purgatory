package ro.deiutzblaxo.Purgatory.Bungee;

import net.md_5.bungee.api.plugin.Plugin;

public class MainBungee extends Plugin {
	private static MainBungee instance;

	@Override
	public void onEnable() {
		instance = this;

	}
	@Override
	public void onDisable() {

	}
	public static MainBungee getInstance() {
		return instance;
	}
}
