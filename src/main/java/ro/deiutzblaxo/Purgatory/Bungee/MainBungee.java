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
package ro.deiutzblaxo.Purgatory.Bungee;



import net.md_5.bungee.api.plugin.Plugin;
import ro.deiutzblaxo.Purgatory.Bungee.Commands.BanCommand;
import ro.deiutzblaxo.Purgatory.Bungee.Commands.InfoCommand;
import ro.deiutzblaxo.Purgatory.Bungee.Commands.PurgatoryCommand;
import ro.deiutzblaxo.Purgatory.Bungee.Commands.Teleport2Command;
import ro.deiutzblaxo.Purgatory.Bungee.Commands.TeleportCommand;
import ro.deiutzblaxo.Purgatory.Bungee.Commands.TempBanCommand;
import ro.deiutzblaxo.Purgatory.Bungee.Commands.UnbanCommand;
import ro.deiutzblaxo.Purgatory.Bungee.Commands.WarningCommand;
import ro.deiutzblaxo.Purgatory.Bungee.Event.Events;



public class MainBungee extends Plugin {
	private ConfigManager ConfigManager;
	private ServerManager ServerManager;
	private ro.deiutzblaxo.Purgatory.Bungee.Factory.BanFactory BanFactory;
	private SpigotCommunication SpigotCommunication;
	private ro.deiutzblaxo.Purgatory.Bungee.Factory.WarningFactory WarningFactory;

	@SuppressWarnings("unused")
	@Override
	public void onEnable() {
		setConfigManager(new ConfigManager(this));
		setServerManager(new ServerManager(this));
		setBanFactory(new ro.deiutzblaxo.Purgatory.Bungee.Factory.BanFactory(this));
		setWarningFactory(new ro.deiutzblaxo.Purgatory.Bungee.Factory.WarningFactory(this));
		setSpigotCommunication(new SpigotCommunication(this));

		getProxy().getPluginManager().registerCommand(this, new BanCommand(getConfigManager().getConfig().getString("Command.Ban"), this));
		getProxy().getPluginManager().registerCommand(this, new TempBanCommand(getConfigManager().getConfig().getString("Command.TempBan"), this));
		getProxy().getPluginManager().registerCommand(this, new UnbanCommand(getConfigManager().getConfig().getString("Command.UnBan"), this));
		getProxy().getPluginManager().registerCommand(this, new InfoCommand(getConfigManager().getConfig().getString("Command.Info") , this));
		getProxy().getPluginManager().registerCommand(this, new WarningCommand(getConfigManager().getConfig().getString("Command.Warning"), this));
		getProxy().getPluginManager().registerCommand(this, new TeleportCommand(getConfigManager().getConfig().getString("Command.tpp") ,this));
		getProxy().getPluginManager().registerCommand(this, new Teleport2Command(getConfigManager().getConfig().getString("Command.tpo") ,this));
		getProxy().getPluginManager().registerCommand(this, new PurgatoryCommand("Purgatory",this));
		getProxy().getPluginManager().registerListener(this, new Events(this));


		getConfigManager().loadTempBan();
		new Metrics(this);
	}
	@Override
	public void onDisable() {
		getConfigManager().saveTempBan();

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
	public ro.deiutzblaxo.Purgatory.Bungee.Factory.WarningFactory getWarningFactory() {
		return WarningFactory;
	}
	public void setWarningFactory(ro.deiutzblaxo.Purgatory.Bungee.Factory.WarningFactory warningFactory) {
		WarningFactory = warningFactory;
	}

}
