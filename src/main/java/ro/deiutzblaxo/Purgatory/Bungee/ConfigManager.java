package ro.deiutzblaxo.Purgatory.Bungee;

import java.io.File;
import java.io.IOException;

import net.md_5.bungee.api.chat.TextComponent;

public class ConfigManager {
	private File configfile;
	private File messagesfile;
	private File BanDataBasefile;
	private File WarningDataBasefile;
	private File PluginFolder;
	private File DatabaseFolder;
	private MainBungee plugin = MainBungee.getInstance();
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

	}
}
