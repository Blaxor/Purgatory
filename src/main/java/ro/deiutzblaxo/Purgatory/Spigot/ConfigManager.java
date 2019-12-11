package ro.deiutzblaxo.Purgatory.Spigot;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;



public class ConfigManager {
	private File messagesfile, DatabaseFolder , PluginFolder , TasksDataBasefile , WarningDataBasefile , BanDataBasefile , CustomTasksFile , oldFile , IPBanDataBasefile;
	private FileConfiguration messages, TasksDataBase, WarningDataBase , BanDataBase , CustomTasks , old , IPBanDataBase;


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

		//		IPBanDataBasefile = new File(DatabaseFolder , "IPBanDataBase.yml");
		//		if(!IPBanDataBasefile.exists()) {
		//			try {
		//				IPBanDataBasefile.createNewFile();
		//				Bukkit.getConsoleSender().sendMessage("["+plugin.getDescription().getName()+"] " + "IPBanDataBase.yml created.");
		//			} catch (IOException e) {
		//				Bukkit.getConsoleSender().sendMessage("["+plugin.getDescription().getName()+"] " + "IPBanDataBase.yml can`t be created , please contact the developer!");
		//				e.printStackTrace();
		//			}
		//		}

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
	public void loadIPBansDataBase() {
		IPBanDataBase = YamlConfiguration.loadConfiguration(IPBanDataBasefile);
	}
	public void convertBaseData5_0(){
		oldFile = new File(DatabaseFolder , "Data.yml");
		if(oldFile.exists()) {
			if(oldFile.getUsableSpace() > 0) {
				old = YamlConfiguration.loadConfiguration(oldFile);
				loadTasksDataBase();
				loadTasks();
				loadBanDataBase();
				loadWarningDataBase();
				Set<String> uuids =old.getConfigurationSection("BanList").getKeys(false);
				for(String uuid : uuids) {

					if(old.contains("BanList." + uuid + ".Tasks" )) {

						for(String str : plugin.getTaskFactory().getTasks()) {
							TasksDataBase.set(uuid + "." + str, old.get("BanList." + uuid + ".Tasks." + str));
						}

					}
					if(old.contains("BanList." + uuid + ".Player")) {
						BanDataBase.set(uuid + ".Name", old.get("BanList." + uuid + ".Player"));
					}
					if(old.contains("BanList." + uuid + ".Reason")) {
						BanDataBase.set(uuid + ".Reason", old.get("BanList." + uuid + ".Reason"));
					}
					if(old.contains("BanList." + uuid + ".Location")) {
						if(old.contains("BanList." + uuid + ".Location.World")) {
							BanDataBase.set(uuid + ".Location.World", old.get("BanList." + uuid + ".Location.World"));
						}
						if(old.contains("BanList." + uuid + ".Location.X")) {
							BanDataBase.set(uuid + ".Location.X", old.get("BanList." + uuid + ".Location.X"));
						}
						if(old.contains("BanList." + uuid + ".Location.Y")) {
							BanDataBase.set(uuid + ".Location.Y", old.get("BanList." + uuid + ".Location.Y"));
						}
						if(old.contains("BanList." + uuid + ".Location.Z")) {
							BanDataBase.set(uuid + ".Location.Z", old.get("BanList." + uuid + ".Location.Z"));
						}
					}
				}
			}
			saveBanDataBase();
			saveTasksDataBases();
			saveWarningDataBase();
			oldFile.delete();
		}
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
	public void saveIPbanDataBase() {
		try {
			IPBanDataBase.save(IPBanDataBasefile);
		} catch (IOException e) {
			Bukkit.getConsoleSender().sendMessage("["+plugin.getDescription().getName()+"] " + "IPBanDataBase.yml can`t be saved!");
			e.printStackTrace();
		}
	}

	private void setMessages() {
		loadMessages();
		if(!plugin.isBungeeEnabled()) {
			getMessages().options().header("For a new line use %newline% \n Second line");
			getMessages().options().copyHeader(true);
			getMessages().addDefault("Ban.Format", "&4[BANNED] %reason%");
			getMessages().addDefault("Ban.isBan", "&4%player% is aleardy banned!");
			getMessages().addDefault("Ban.DefaultReason", "This is a default reason for bans.");
			getMessages().addDefault("Ban.InvalidCommand", "&4Try /ban <player> <reason>");
			getMessages().addDefault("Ban.broadcast", "&7[&aPurgatory&7]&a%player% have been banned by %admin%!");
			getMessages().addDefault("TempBan.Format", "&4You have been banned by %admin% %newline% because %reason% %newline% for %time%s");
			getMessages().addDefault("TempBan.DefaultReason", "This is a default reason for tempbans.");
			getMessages().addDefault("TempBan.InvalidCommand", "&4try /tempban <player> <time> <reason>");
			getMessages().addDefault("TempBan.broadcast", "&7[&aPurgatory&7]&a%player% have been tempbanned by %admin% for %time%s!");
			getMessages().addDefault("TempBan.expire", "&aYour tempban have been finished!");
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
			getMessages().addDefault("Info.Format", "&4%player%'s Info%newline%&4Is banned: %isban% %newline%&4Time: %time% %newline%&4Reason: %reason% %newline%&4Warnings: %warnings%");
			getMessages().addDefault("Yes", "Yes");
			getMessages().addDefault("No", "No");
			getMessages().addDefault("TaskComplete", "&aYou have been completed the task : %task% !");
			getMessages().addDefault("InvalidCommand.Usage", "&eUsage");
			getMessages().addDefault("InvalidCommand.Command", "&eThis is the command!");
			getMessages().addDefault("InvalidCommand.Player.hover", "&eWrite the name of an offline/online player");
			getMessages().addDefault("InvalidCommand.Player.player", "<player>");
			getMessages().addDefault("InvalidCommand.Time.hover", "&eThis needs to be a number of seconds");
			getMessages().addDefault("InvalidCommand.Time.time", "<time>");
			getMessages().addDefault("InvalidCommand.Reason.hover", "&eYou can write a reason if you choose to do so");
			getMessages().addDefault("InvalidCommand.Reason.reason", "<reason>");
			getMessages().addDefault("tpo.Error", "&4Only non-banned players can use this command!");
			getMessages().addDefault("Scoreboard.Title", "&4PURGATORY");
			getMessages().addDefault("Scoreboard.TasksColor", "&e");
			getMessages().addDefault("NoPermission", "&4You don`t have permission to do that!");
			getMessages().addDefault("Menu.Cheaters.WrongWorld", "&cYou can use this just in purgatory!");
			getMessages().addDefault("Troll.Menu", "&eTrolls");
			getMessages().addDefault("Troll.Banned", "&4You can`t open the troll menu because you are banned!");
			getMessages().addDefault("Troll.onCooldown", "&e%cooldown% to re-use this troll!");
			getMessages().addDefault("Troll.SmokeScreen.Title", "SmokeScreen");
			getMessages().addDefault("Troll.SmokeScreen.Description", "&7Spawns an orb of grayish particles %newline%&7around the cheater that lasts\r\n 5 seconds. %newline% %newline% &7Cooldown is : &4&b%cooldown%");
			getMessages().addDefault("Troll.Burn.Title", "Burn");
			getMessages().addDefault("Troll.Burn.Description", "&7Lights the block %newline%&7where the cheater %newline%&7is standing on for fire for 1 second %newline% %newline% &7Cooldown is : &4&b%cooldown%");
			getMessages().addDefault("Troll.Flip.Title", "Flip");
			getMessages().addDefault("Troll.Flip.Description", "&7Flipst the cheater around 180 degrees%newline% %newline% &7Cooldown is : &4&b%cooldown%");
			getMessages().addDefault("Troll.Creeper.Title", "Creeper");
			getMessages().addDefault("Troll.Creeper.Description", "&7Play the sound of a creeper about %newline%&7to explode behind the cheater%newline% %newline% &7Cooldown is : &4&b%cooldown%");
			getMessages().addDefault("Troll.Web.Title", "Web");
			getMessages().addDefault("Troll.Web.Description", "&7Spawns a web block on whatever %newline%&7space the cheater's legs are curently on %newline% %newline% &7Cooldown is : &4&b%cooldown% ");
			getMessages().addDefault("Troll.Lag.Title", "Lag");
			getMessages().addDefault("Troll.Lag.Description", "&7Over the course of 3 seconds%newline%&7teleport the cheater to themself %newline%&7every half of second making them 'lag' %newline% %newline% &7Cooldown is : &4&b%cooldown% ");
			getMessages().addDefault("Troll.Mole.Title", "Mole");
			getMessages().addDefault("Troll.Mole.Description", "&7Buries the cheater 3 blocks under ground %newline% %newline% &7Cooldown is : &4&b%cooldown% ");
			getMessages().addDefault("Troll.Infection.Title", "Infection");
			getMessages().addDefault("Troll.Infection.Description", "&7Buries the cheater 3 blocks under ground%newline% %newline% &7Cooldown is : &4&b%cooldown% ");
			getMessages().addDefault("Troll.Storm.Title", "Storm");
			getMessages().addDefault("Troll.Storm.Description", "&7Launchest the player in a random direction%newline% %newline% &7Cooldown is : &4&b%cooldown% ");
			getMessages().addDefault("Troll.Miner.Title", "Miner");
			getMessages().addDefault("Troll.Miner.Description", "&7Plays sound of a serier of blocks %newline%&7breaking near the cheater.%newline% %newline% &7Cooldown is : &4&b%cooldown% ");
			getMessages().addDefault("Troll.Paralysis.Title", "Paralysis");
			getMessages().addDefault("Troll.Paralysis.Description", "&7Gives the player mining fatigue %newline%&7for 10 seconds.%newline% %newline% &7Cooldown is : &4&b%cooldown% ");
			getMessages().addDefault("Troll.Pumpkin.Title", "Pumpkin");
			getMessages().addDefault("Troll.Pumpkin.Description", "&7Replace cheater's head with pumpking %newline%&7and old head is place it in inventory%newline% %newline% &7Cooldown is : &4&b%cooldown% ");
			getMessages().addDefault("Troll.Bouncy.Title", "Bouncy");
			getMessages().addDefault("Troll.Bouncy.Description", "&7Knock the cheater 25 blocks in air %newline% %newline% &7Cooldown is : &4&b%cooldown% ");
			getMessages().addDefault("Troll.Slow.Title", "Slow");
			getMessages().addDefault("Troll.Slow.Description", "&7Gives the cheater slowness 4 for 5 seconds. %newline% %newline% &7Cooldown is : &4&b%cooldown% ");
			getMessages().addDefault("Troll.JumpBoost.Title", "JumpBoost");
			getMessages().addDefault("Troll.JumpBoost.Description", "&7Give the cheater jump boost 10 for 10 seconds %newline% %newline% &7Cooldown is : &4&b%cooldown% ");
			getMessages().addDefault("Troll.Speed.Title", "Speed");
			getMessages().addDefault("Troll.Speed.Description", "&7Gives the cheater speed 100 for 5 seconds %newline% %newline% &7Cooldown is : &4&b%cooldown% ");
			getMessages().addDefault("Troll.MobSquad.Title", "MobSquad");
			getMessages().addDefault("Troll.MobSquad.Description", "&7Spawns a gang of randomly %newline%&7chosen mobs around the cheater %newline% %newline% &7Cooldown is : &4&b%cooldown%");

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
	public FileConfiguration getIPBanDataBase() {
		return IPBanDataBase;
	}
	public String getString(FileConfiguration FileConfiguration , String path) {
		return FileConfiguration.getString(path).replaceAll("%newline%", "\n");

	}
}
