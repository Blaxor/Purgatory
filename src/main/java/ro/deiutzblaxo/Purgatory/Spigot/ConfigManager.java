package ro.deiutzblaxo.Purgatory.Spigot;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;



public class ConfigManager {
	private File messagesfile, DatabaseFolder , PluginFolder , TasksDataBasefile , WarningDataBasefile , BanDataBasefile , CustomTasksFile;
	private FileConfiguration messages, TasksDataBase, WarningDataBase , BanDataBase , CustomTasks;


	private MainSpigot plugin = MainSpigot.getInstance();
	public void createConfigs() {

		PluginFolder = plugin.getDataFolder();
		if(!PluginFolder.exists()) {
			PluginFolder.mkdirs();

		}


		plugin.getConfig().options().copyDefaults(true);
		plugin.saveConfig();

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
		CustomTasksFile = new File(PluginFolder, "CustomTasks.yml");

		if(!CustomTasksFile.exists()) {
			try {
				CustomTasksFile.createNewFile();
				CustomTasks = YamlConfiguration.loadConfiguration(CustomTasksFile);
				getTasks().set("Stone.type", "place");
				getTasks().set("Stone.count", 10000);
				getTasks().set("Stone.block", "stone");
				getTasks().set("Iron.type", "break");
				getTasks().set("Iron.count", 1500);
				getTasks().set("Iron.block", "iron_ore");
				getTasks().set("LevelUps.type", "level_up");
				getTasks().set("LevelUps.count", 150);
				getTasks().set("Zombies.type", "kill");
				getTasks().set("Zombies.entity", "zombie");
				getTasks().set("Zombies.count", 200);
				getTasks().save(TasksDataBasefile);
				Bukkit.getConsoleSender().sendMessage("["+plugin.getDescription().getName()+"] " + "CustomTasks.yml created.");
			} catch (IOException e) {
				Bukkit.getConsoleSender().sendMessage("["+plugin.getDescription().getName()+"] " + "CustomTasks.yml can`t be created , please contact the developer!");
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
		setMessages();

	}

	public void loadMessages() {
		messages = YamlConfiguration.loadConfiguration(messagesfile);
	}
	public void loadTasksDataBase() {
		TasksDataBase = YamlConfiguration.loadConfiguration(TasksDataBasefile);
	}
	public void loadWarningDataBase() {
		WarningDataBase = YamlConfiguration.loadConfiguration(WarningDataBasefile);
	}
	public void loadBanDataBase() {
		BanDataBase = YamlConfiguration.loadConfiguration(BanDataBasefile);
	}

	public void saveTasksDataBases() {
		try {
			TasksDataBase.save(TasksDataBasefile);
		} catch (IOException e) {
			e.printStackTrace();
			Bukkit.getConsoleSender().sendMessage("["+plugin.getDescription().getName()+"] " + "TasksDataBase.yml can`t be saved!");
		}
	}
	public void saveWarningDataBase() {
		try {
			WarningDataBase.save(WarningDataBasefile);
		}catch(IOException e) {
			e.printStackTrace();
			Bukkit.getConsoleSender().sendMessage("["+plugin.getDescription().getName()+"] " + "WarningDataBase.yml can`t be saved!");
		}
	}

	public void saveBanDataBase() {
		try {
			BanDataBase.save(BanDataBasefile);
		}catch (IOException e) {
			e.printStackTrace();
			Bukkit.getConsoleSender().sendMessage("["+plugin.getDescription().getName()+"] " + "BanDataBase.yml can`t be saved!");
		}
	}

	private void setMessages() {
		loadMessages();
		if(!plugin.isBungeeEnabled()) {
			getMessages().options().header("test");
			getMessages().options().copyHeader(true);
			getMessages().addDefault("test", "test");
			getMessages().options().copyDefaults(true);
		}
		try {
			getMessages().save(messagesfile);
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
	public FileConfiguration getMessages() {
		return messages;
	}
	public FileConfiguration getTasksDataBase() {
		return TasksDataBase;
	}
	public FileConfiguration getWarningDataBase(){
		return WarningDataBase;
	}
	public FileConfiguration getBanDataBase() {
		return BanDataBase;
	}
	public FileConfiguration getTasks() {
		return CustomTasks;
	}
}
