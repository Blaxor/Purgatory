package ro.deiutzblaxo.Purgatory.Bungee;

import net.md_5.bungee.api.plugin.Plugin;
import ro.deiutzblaxo.Purgatory.Bungee.Commands.BanCommand;

public class MainBungee extends Plugin {
	private ConfigManager ConfigManager;
	private ServerManager ServerManager;
	private ro.deiutzblaxo.Purgatory.Bungee.Factory.BanFactory BanFactory;
	private SpigotCommunication SpigotCommunication;

	@Override
	public void onEnable() {
		setConfigManager(new ConfigManager(this));
		setServerManager(new ServerManager(this));
		setBanFactory(new ro.deiutzblaxo.Purgatory.Bungee.Factory.BanFactory(this));
		setSpigotCommunication(new SpigotCommunication(this));

		getProxy().getPluginManager().registerCommand(this, new BanCommand(getConfigManager().getConfig().getString("Command.Ban"), this));

	}
	@Override
	public void onDisable() {

	}
	public ConfigManager getConfigManager() {
		return ConfigManager;
	}
	private void setConfigManager(ConfigManager configManager) {
		ConfigManager = configManager;
	}
	public ServerManager getServerManager() {
		return ServerManager;
	}
	private void setServerManager(ServerManager serverManager) {
		ServerManager = serverManager;
	}
	public ro.deiutzblaxo.Purgatory.Bungee.Factory.BanFactory getBanFactory() {
		return BanFactory;
	}
	private void setBanFactory(ro.deiutzblaxo.Purgatory.Bungee.Factory.BanFactory banFactory) {
		BanFactory = banFactory;
	}
	public SpigotCommunication getSpigotCommunication() {
		return SpigotCommunication;
	}
	private void setSpigotCommunication(SpigotCommunication spigotCommunication) {
		SpigotCommunication = spigotCommunication;
	}

}
