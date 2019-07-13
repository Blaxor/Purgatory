package ro.deiutzblaxo.Purgatory.Spigot;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;



public class ConfigManager {
	private File configfile;
	private File messagesfile;
	private File BanDataBasefile;
	private File WarningDataBasefile;
	private File TasksDataBasefile;
	private File PluginFolder;
	private File DatabaseFolder;
	private MainSpigot plugin = MainSpigot.getInstance();
	public void createConfigs() {

		Bukkit.getConsoleSender().sendMessage("["+plugin.getDescription().getName()+"] " + "Working at configs");

		PluginFolder = plugin.getDataFolder();
		if(!PluginFolder.exists()) {
			PluginFolder.mkdirs();
		}

		configfile = new File(PluginFolder , "config.yml");

		if(!configfile.exists()) {
			Bukkit.getConsoleSender().sendMessage("["+plugin.getDescription().getName()+"] " + "config.yml created.");
			plugin.saveResource("resources/spigot/config.yml", false);
		}

		messagesfile = new File(PluginFolder, "messages.yml");
		if(!messagesfile.exists()) {
			try {

				messagesfile.createNewFile();
				Bukkit.getConsoleSender().sendMessage("["+plugin.getDescription().getName()+"] " + "messages.yml created.");

			} catch (IOException e) {

				Bukkit.getConsoleSender().sendMessage("["+plugin.getDescription().getName()+"] " + "messages.yml can`t be created , please contact the developer!");
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
				Bukkit.getConsoleSender().sendMessage("["+plugin.getDescription().getName()+"] " + "BanDataBase.yml created.");

			} catch (IOException e) {

				Bukkit.getConsoleSender().sendMessage("["+plugin.getDescription().getName()+"] " + "BanDataBase.yml can`t be created , please contact the developer!");
				e.printStackTrace();

			}
		}
		WarningDataBasefile = new File(DatabaseFolder , "WarningDataBase.yml");
		if(!WarningDataBasefile.exists()) {
			try {
				WarningDataBasefile.createNewFile();
				Bukkit.getConsoleSender().sendMessage("["+plugin.getDescription().getName()+"] " + "WarningDataBase.yml created.");

			} catch (IOException e) {

				Bukkit.getConsoleSender().sendMessage("["+plugin.getDescription().getName()+"] " + "WarningDataBase.yml can`t be created , please contact the developer!");
				e.printStackTrace();

			}
		}
		TasksDataBasefile = new File(DatabaseFolder , "TasksDataBase.yml");
		if(!TasksDataBasefile.exists()) {
			try {
				TasksDataBasefile.createNewFile();
				Bukkit.getConsoleSender().sendMessage("["+plugin.getDescription().getName()+"] " + "TasksDataBase.yml created.");

			} catch (IOException e) {

				Bukkit.getConsoleSender().sendMessage("["+plugin.getDescription().getName()+"] " + "TasksDataBase.yml can`t be created , please contact the developer!");
				e.printStackTrace();
			}
		}

	}
}
