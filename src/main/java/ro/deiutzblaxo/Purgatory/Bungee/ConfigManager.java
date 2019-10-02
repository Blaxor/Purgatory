package ro.deiutzblaxo.Purgatory.Bungee;

import java.io.File;
import java.io.IOException;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class ConfigManager {
	private MainBungee plugin;
	public ConfigManager(MainBungee main) {
		plugin = main;
		createConfigs();
	}
	private File configfile , messagesfile , BanDataBasefile,WarningDataBasefile,PluginFolder, DatabaseFolder;
	private Configuration messages,config,ban,warning;

	public void createConfigs() {

		plugin.getProxy().getConsole().sendMessage(new TextComponent("["+plugin.getDescription().getName()+"] " + "Working at configs"));

		PluginFolder = plugin.getDataFolder();
		if(!PluginFolder.exists()) {
			PluginFolder.mkdirs();
		}

		configfile = new File(PluginFolder , "config.yml");
		if(!configfile.exists()) {
			try {
				plugin.getProxy().getConsole().sendMessage(new TextComponent("["+plugin.getDescription().getName()+"] " + "config.yml created."));
				configfile.createNewFile();

			} catch (IOException e) {

				plugin.getProxy().getConsole().sendMessage(new TextComponent("["+plugin.getDescription().getName()+"] " + "config.yml can`t be created , please contact the developer!"));
				e.printStackTrace();
			}
		}

		messagesfile = new File(PluginFolder, "messages.yml");
		if(!messagesfile.exists()) {
			try {

				messagesfile.createNewFile();
				plugin.getProxy().getConsole().sendMessage(new TextComponent("["+plugin.getDescription().getName()+"] " + "messages.yml created."));

			} catch (IOException e) {

				plugin.getProxy().getConsole().sendMessage(new TextComponent("["+plugin.getDescription().getName()+"] " + "messages.yml can`t be created , please contact the developer!"));
				e.printStackTrace();

			}
		}

		DatabaseFolder = new File(PluginFolder, "/BaseDataYML/");
		if(!DatabaseFolder.exists()) {
			DatabaseFolder.mkdir();
		}

		BanDataBasefile = new File(DatabaseFolder , "BanDataBase.yml");
		if(!BanDataBasefile.exists()) {
			try {

				BanDataBasefile.createNewFile();
				plugin.getProxy().getConsole().sendMessage(new TextComponent("["+plugin.getDescription().getName()+"] " + "BanDataBase.yml created."));

			} catch (IOException e) {

				plugin.getProxy().getConsole().sendMessage(new TextComponent("["+plugin.getDescription().getName()+"] " + "BanDataBase.yml can`t be created , please contact the developer!"));
				e.printStackTrace();

			}
		}
		WarningDataBasefile = new File(DatabaseFolder , "WarningDataBase.yml");
		if(!WarningDataBasefile.exists()) {
			try {
				WarningDataBasefile.createNewFile();
				plugin.getProxy().getConsole().sendMessage(new TextComponent("["+plugin.getDescription().getName()+"] " + "WarningDataBase.yml created."));

			} catch (IOException e) {

				plugin.getProxy().getConsole().sendMessage(new TextComponent("["+plugin.getDescription().getName()+"] " + "WarningDataBase.yml can`t be created , please contact the developer!"));
				e.printStackTrace();

			}
		}
		loadConfig();
		set(config, "Purgatory-Server", "purgatory");
		set(config, "Hub-Server" , "hub");
		set(config, "Remove-Warnings-On-Ban" , true);
		set(config, "Command.Ban" , "Ban");
		saveConfig();

		loadMessages();
		set(messages,"NoPermission","&4You don`t have permission!");
		set(messages,"Ban.InvalidCommand" , "&4Please try /ban <player> <reason>");
		set(messages, "Ban.isBan" , "&4%player% is aleardy banned!");
		set(messages, "Ban.DefaultReason" , "This is a default reason for ban");
		set(messages, "Ban.Format" , "&4&bYou have been banned!%newline% &4Reason:&r %reason%");
		set(messages, "Ban.Broadcast","&e%player% have been banned by %admin% because %reason%");
		set(messages, "Ban.PlayerOffline" , "This player is offline!");
		saveMessages();

		loadMessages();

		loadConfig();


	}
	public Configuration getBans() {
		return ban;
	}
	public Configuration getMessages() {
		return messages;
	}
	public Configuration getConfig() {
		return config;
	}
	public Configuration getWarnings() {
		return warning;
	}
	public void loadBans() {
		try {
			ban = ConfigurationProvider.getProvider(YamlConfiguration.class).load(BanDataBasefile);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	private void loadMessages() {
		try {
			messages = ConfigurationProvider.getProvider(YamlConfiguration.class).load(messagesfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void loadConfig() {
		try {
			config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configfile);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	public void loadWarnings() {
		try {
			warning = ConfigurationProvider.getProvider(YamlConfiguration.class).load(WarningDataBasefile);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	public void saveBans() {
		try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(ban, BanDataBasefile);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	public void saveWarnings() {
		try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(warning, WarningDataBasefile);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	public void saveConfig() {
		try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, configfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void saveMessages() {
		try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(messages, messagesfile);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	private void set(Configuration configuration, String path, Object value) {

		if (!configuration.contains(path)) {
			configuration.set(path, value);
		}
	}
	public String getString(Configuration configuration , String path) {
		return configuration.getString(path).replaceAll("%newline%", "\n");

	}
}











