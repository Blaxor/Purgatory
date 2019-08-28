package ro.deiutzblaxo.Purgatory.Spigot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandMap;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import ro.deiutzblaxo.Purgatory.Spigot.API.ScoreBoardAPI;
import ro.deiutzblaxo.Purgatory.Spigot.Commands.BanCommand;
import ro.deiutzblaxo.Purgatory.Spigot.Commands.CheatersCommand;
import ro.deiutzblaxo.Purgatory.Spigot.Commands.PurgeCommand;
import ro.deiutzblaxo.Purgatory.Spigot.Commands.TrollCommand;
import ro.deiutzblaxo.Purgatory.Spigot.Commands.WarningCommand;
import ro.deiutzblaxo.Purgatory.Spigot.Events.JustSpigotEvents;
import ro.deiutzblaxo.Purgatory.Spigot.Factory.BanFactory;
import ro.deiutzblaxo.Purgatory.Spigot.Factory.TaskFactory;
import ro.deiutzblaxo.Purgatory.Spigot.Factory.WarningFactory;
import ro.deiutzblaxo.Purgatory.Spigot.Tasks.BreakTask;
import ro.deiutzblaxo.Purgatory.Spigot.Tasks.KillTask;
import ro.deiutzblaxo.Purgatory.Spigot.Tasks.LevelUpTask;
import ro.deiutzblaxo.Purgatory.Spigot.Tasks.PlaceTask;
import ro.deiutzblaxo.Purgatory.Spigot.Troll.Players;
import ro.deiutzblaxo.Purgatory.Spigot.Troll.Trolls;

public class MainSpigot extends JavaPlugin implements Listener {
	private static MainSpigot instance;
	private ConfigManager configmanager;
	private BanFactory BanFactory;
	private WarningFactory WarningFactory;
	private TaskFactory TaskFactory;
	private CommandMap commandMap;
	private WorldManager WorldManager;
	private ScoreBoardAPI ScoreBoardAPI;


	@Override
	public void onEnable() {
		instance = this;
		configmanager = new ConfigManager();
		getConfigManager().createConfigs();
		BanFactory = new BanFactory();
		WarningFactory = new WarningFactory();
		TaskFactory = new TaskFactory(this);
		ScoreBoardAPI = new ScoreBoardAPI();


		loadCommandMap();

		this.commandMap.register("purgatory", new BanCommand(this.getConfig().getString("Command.Ban"), this));
		this.commandMap.register("purgatory", new PurgeCommand(this.getConfig().getString("Command.Purge") , this));
		this.commandMap.register("purgatory", new WarningCommand(this.getConfig().getString("Command.Warning") , this));
		this.commandMap.register("purgatory", new CheatersCommand("che" , this));
		this.commandMap.register("purgatory", new TrollCommand("troll" , this));

		WorldManager = new WorldManager(this);
		if(!isBungeeEnabled()) {
			getServer().getPluginManager().registerEvents(new JustSpigotEvents(this), this);
		}
		getServer().getPluginManager().registerEvents(new BreakTask(this), this);
		getServer().getPluginManager().registerEvents(new PlaceTask(this), this);
		getServer().getPluginManager().registerEvents(new KillTask(this), this);
		getServer().getPluginManager().registerEvents(new LevelUpTask(this), this);
		getServer().getPluginManager().registerEvents(new Players(this), this);
		getServer().getPluginManager().registerEvents(new Trolls(this), this);
		if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
			new PlaceHolderHooker(this).register();
			Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7[&aPurgatory&7]&0 PlaceHolderAPI have been hooked!"));
		}
		if(Bukkit.getPluginManager().isPluginEnabled("Citizens")) {
			getServer().getPluginManager().registerEvents(new CitizensHooker(this), this);
			Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7[&aPurgatory&7]&0 Citizens have been hooked!"));
		}
		updateCheckerConsole(this, "&7[&aPurgatory&7]", 65838);
		getServer().getPluginManager().registerEvents(this, this);
	}
	@Override
	public void onDisable() {

	}
	public Boolean isBungeeEnabled() {
		return this.getConfig().getBoolean("BungeeCord");
	}

	private void loadCommandMap() {
		try {
			if (Bukkit.getPluginManager() instanceof SimplePluginManager) {
				Field f = SimplePluginManager.class.getDeclaredField("commandMap");

				f.setAccessible(true);
				this.commandMap = (CommandMap) f.get(Bukkit.getPluginManager());
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	public void updateCheckerConsole(Plugin plugin, String prefix, Integer ResourceNumber) {
		PluginDescriptionFile pdffile = plugin.getDescription();
		try {
			HttpURLConnection con = (HttpURLConnection) new URL(
					"https://api.spigotmc.org/legacy/update.php?resource=" + ResourceNumber).openConnection();
			int timed_out = 1250;
			con.setConnectTimeout(timed_out);
			con.setReadTimeout(timed_out);
			String latestversion = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
			if ((latestversion.length() <= 100) && (!pdffile.getVersion().equals(latestversion))) {
				Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&8&m--------------------------------------------------------------------------------------"));
				Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
						prefix + "&8There is a new version available. &9" + latestversion));
				Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefix
						+ "&8You can download it at: &9https://www.spigotmc.org/resources/" + ResourceNumber + "/"));
				Bukkit.getConsoleSender().sendMessage(
						ChatColor.translateAlternateColorCodes('&', prefix + "&8Don`t forget to rate our plugin!"));
				Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&8&m--------------------------------------------------------------------------------------"));

			}
		} catch (Exception ex) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&8&m--------------------------------------------------------------------------------------"));
			Bukkit.getConsoleSender().sendMessage(
					ChatColor.translateAlternateColorCodes('&', prefix + "&cPlease connect to the internet."));
			Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&8&m--------------------------------------------------------------------------------------"));
		}
	}
	@EventHandler(priority = EventPriority.LOWEST , ignoreCancelled = true)
	public void updateCheckerJoin(PlayerJoinEvent event) {
		try {
			updateCheckerPlayer(this , event.getPlayer(), "&7[&aPurgatory&7]", 65838);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateCheckerPlayer(Plugin plugin, Player player, String prefix, Integer ResourceNumber)
			throws MalformedURLException, IOException {

		PluginDescriptionFile pdffile = plugin.getDescription();
		HttpURLConnection con = (HttpURLConnection) new URL(
				"https://api.spigotmc.org/legacy/update.php?resource=" + ResourceNumber).openConnection();
		int timed_out = 1250;
		con.setConnectTimeout(timed_out);
		con.setReadTimeout(timed_out);
		String latestversion = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
		if ((latestversion.length() <= 100) && (!pdffile.getVersion().equals(latestversion))) {

			if ((player.isOp()) && (!pdffile.getVersion().equals(latestversion))) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&8&m-----------------------------------------------------"));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						prefix + "&8There is a new version available. &9" + latestversion));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						prefix + "&8You can download it at: &9https://www.spigotmc.org/resources/65244/"));
				Bukkit.getConsoleSender().sendMessage(
						ChatColor.translateAlternateColorCodes('&', prefix + "&8Don`t forget to rate our plugin!"));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&8&m-----------------------------------------------------"));
			}
		}
	}
	public static MainSpigot getInstance() {
		return instance;
	}
	public ConfigManager getConfigManager() {
		return configmanager;
	}
	public BanFactory getBanFactory() {
		return BanFactory;
	}
	public WarningFactory getWarningFactory() {
		return WarningFactory;
	}
	public TaskFactory getTaskFactory() {
		return TaskFactory;
	}
	public WorldManager getWorldManager() {
		return WorldManager;
	}
	public ro.deiutzblaxo.Purgatory.Spigot.API.ScoreBoardAPI getScoreBoardAPI() {
		return ScoreBoardAPI;
	}

	public ArrayList<HashMap<UUID,Integer>> Trolls = new ArrayList<HashMap<UUID,Integer>>();
	public HashMap<UUID,Integer> SmokeScreen = new HashMap<UUID,Integer>();


	public void CooldownTrolls() {
		Trolls.add(SmokeScreen);
		new BukkitRunnable() {
			@Override
			public void run() {


			}
		}.runTaskTimer(this, 0, 20);
	}



}
