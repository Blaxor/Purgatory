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
				getTasks().set("Tasks.Stone.type", "place");
				getTasks().set("Tasks.Stone.count", 10000);
				getTasks().set("Tasks.Stone.block", "stone");
				getTasks().set("Tasks.Iron.type", "break");
				getTasks().set("Tasks.Iron.count", 1500);
				getTasks().set("Tasks.Iron.block", "iron_ore");
				getTasks().set("Tasks.LevelUps.type", "level_up");
				getTasks().set("Tasks.LevelUps.count", 150);
				getTasks().set("Tasks.Zombies.type", "kill");
				getTasks().set("Tasks.Zombies.entity", "zombie");
				getTasks().set("Tasks.Zombies.count", 200);
				getTasks().save(CustomTasksFile);
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
	public void loadTasks() {
		CustomTasks = YamlConfiguration.loadConfiguration(CustomTasksFile);
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
			getMessages().addDefault("Ban.Format", "&4[BANNED] %reason%");
			getMessages().addDefault("Ban.isBan", "&4%player% is aleardy banned!");
			getMessages().addDefault("Ban.DefaultReason", "This is a default reason for bans.");
			getMessages().addDefault("Ban.InvalidCommand", "&4Try /ban <player> <reason>");
			getMessages().addDefault("Ban.broadcast", "&7[&aPurgatory&7]&a%player% have been banned by %admin%!");
			getMessages().addDefault("Purge.InvalidCommand", "&4Try /purge <player>");
			getMessages().addDefault("Purge.notBanned", "&4%player% is not banned!");
			getMessages().addDefault("Purge.Format", "&aYou have been unbanned by %admin%!");
			getMessages().addDefault("Purge.broadcast", "&7[&aPurgatory&7]&a%player% have been unbanned by %admin%!");
			getMessages().addDefault("Warning.InvalidCommand", "&4Try /warning <player> <reason>");
			getMessages().addDefault("Warning.isBanned", "&4%player% is aleardy banned!");
			getMessages().addDefault("Warning.Receive", "&4You have been warned(%warning%/%warning_max%) by %admin% because : %reason%");
			getMessages().addDefault("Warning.Send", "&4You warned %player% because : %reason%");
			getMessages().addDefault("Warning.broadcast", "&7[&aPurgatory&7]%admin% warned %player% for the reason : %reason%");
			getMessages().addDefault("Warning.DefaultReason", "This is a default reason for warnings");
			getMessages().addDefault("Scoreboard.Title", "&4PURGATORY");
			getMessages().addDefault("Scoreboard.TasksColor", "&e");
			getMessages().addDefault("NoPermission", "&4You don`t have permission to do that!");
			getMessages().addDefault("Menu.Cheaters.WrongWorld", "&cYou can use this just in purgatory!");
			getMessages().addDefault("Troll.Menu", "&eTrolls");
			getMessages().addDefault("Troll.SmokeScreen.Title", "SmokeScreen");
			getMessages().addDefault("Troll.SmokeScreen.Description", "&7Spawns an orb of grayish particles /n&7around the cheater that lasts\r\n 5 seconds. /n /n &7Cooldown is : &4&b%cooldown%");
			getMessages().addDefault("Troll.BurnItem.Title", "Burn");
			getMessages().addDefault("Troll.BurnItem.Description", "&7Lights the block /n&7where the cheater /n&7is standing on for fire for 1 second /n /n &7Cooldown is : &4&b%cooldown%");
			getMessages().addDefault("Troll.Flip.Title", "Flip");
			getMessages().addDefault("Troll.Flip.Description", "&7Flipst the cheater around 180 degrees/n /n Cooldown is : &4&b%cooldown%");
			getMessages().addDefault("Troll.Creeper.Title", "Creeper");
			getMessages().addDefault("Troll.Creeper.Description", "&7Play the sound of a creeper about /n&7to explode behind the cheater/n /n &7Cooldown is : &4&b%cooldown%");
			getMessages().addDefault("Troll.Web.Title", "Web");
			getMessages().addDefault("Troll.Web.Description", "&7Spawns a web block on whatever /n&7space the cheater's legs are curently on /n /n &7Cooldown is : &4&b%cooldown% ");
			getMessages().addDefault("Troll.Lag.Title", "Lag");
			getMessages().addDefault("Troll.Lag.Description", "&7Over the course of 3 seconds/n&7 teleport the cheater to themself /n&7every half of second making them 'lag' /n /n &7Cooldown is : &4&b%cooldown% ");
			getMessages().addDefault("Troll.Mole.Title", "Mole");
			getMessages().addDefault("Troll.Mole.Description", "&7Buries the cheater 3 blocks under ground /n /n &7Cooldown is : &4&b%cooldown% ");
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
