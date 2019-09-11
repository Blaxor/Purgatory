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
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
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
import ro.deiutzblaxo.Purgatory.Spigot.Commands.InfoCommand;
import ro.deiutzblaxo.Purgatory.Spigot.Commands.PurgatoryCommand;
import ro.deiutzblaxo.Purgatory.Spigot.Commands.PurgeCommand;
import ro.deiutzblaxo.Purgatory.Spigot.Commands.TempBanCommand;
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
		//TODO UPDATE ALL getMessages().getString("value") to a new line for all
		//TODO SUGGESTIONS :  ip bans , Make a command to be able to tp to purgatory and troll ,  make banned chat different from normal chat , Add the ability to give weapons to banned players when they are banned and when they respawn


		loadCommandMap();


		this.commandMap.register("purgatory", new CheatersCommand("cheaters" , this));
		this.commandMap.register("purgatory", new InfoCommand("info" , this));
		this.commandMap.register("purgatory", new PurgatoryCommand("purgatory" , this));


		WorldManager = new WorldManager(this);
		if(!isBungeeEnabled()) {
			getServer().getPluginManager().registerEvents(new JustSpigotEvents(this), this);
			//TODO ADD ALL TEMPBANS ON LOAD
			//TODO RETHINK THE TEMPBAN SYSTEM.
			this.commandMap.register("purgatory", new BanCommand(this.getConfig().getString("Command.Ban"), this));
			this.commandMap.register("purgatory", new PurgeCommand(this.getConfig().getString("Command.Purge") , this));
			this.commandMap.register("purgatory", new WarningCommand(this.getConfig().getString("Command.Warning") , this));
			this.commandMap.register("purgatory", new TempBanCommand("tempban" , this));
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
		Cooldowns();
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
	public HashMap<UUID,Integer> SmokeScreen_Effect = new HashMap<UUID,Integer>();
	public HashMap<UUID,Integer> Burn = new HashMap<UUID,Integer>();
	public HashMap<Location,Integer> Burn_Effect = new HashMap<Location,Integer>();
	public HashMap<UUID,Integer> Flip = new HashMap<UUID,Integer>();
	public HashMap<UUID,Integer> Creeper = new HashMap<UUID,Integer>();
	public HashMap<UUID,Integer> Web = new HashMap<UUID,Integer>();
	public HashMap<UUID,Integer> Lag = new HashMap<UUID,Integer>();
	public HashMap<UUID,Double> Lag_Effect = new HashMap<UUID,Double>();
	public HashMap<UUID,Integer> Mole = new HashMap<UUID,Integer>();
	public HashMap<UUID,Integer> Infection = new HashMap<UUID, Integer>();
	public HashMap<UUID,Integer> Storm = new HashMap<UUID, Integer>();
	public HashMap<UUID,Integer> Miner = new HashMap<UUID, Integer>();
	public HashMap<UUID,Integer> Miner_Effect = new HashMap<UUID, Integer>();
	public HashMap<UUID,Integer> MobSquad = new HashMap<UUID,Integer>();
	public HashMap<UUID,Integer> Paralysis = new HashMap<UUID,Integer>();
	public HashMap<UUID,Integer> Pumpkin = new HashMap<UUID,Integer>();
	public HashMap<UUID,Integer> Bouncy = new HashMap<UUID,Integer>();
	public HashMap<UUID,Integer> Slow = new HashMap<UUID,Integer>();
	public HashMap<UUID,Integer> JumpBoost = new HashMap<UUID,Integer>();
	public HashMap<UUID,Integer> Speed = new HashMap<UUID,Integer>();

	public HashMap<UUID,Integer> TempBan = new HashMap<UUID,Integer>();

	public void Cooldowns() {
		Trolls.add(SmokeScreen);
		Trolls.add(Burn);
		Trolls.add(Flip);
		Trolls.add(Creeper);
		Trolls.add(Web);
		Trolls.add(Mole);
		Trolls.add(Lag);
		Trolls.add(Infection);
		Trolls.add(Storm);
		Trolls.add(MobSquad);
		Trolls.add(Paralysis);
		Trolls.add(Miner);
		Trolls.add(Pumpkin);
		Trolls.add(Bouncy);
		Trolls.add(Slow);
		Trolls.add(JumpBoost);
		Trolls.add(Speed);

		new BukkitRunnable() {
			@Override
			public void run() {
				if(Trolls.isEmpty()) return;
				for(HashMap<UUID,Integer> test: Trolls) {
					if(test.isEmpty()) {return;}
					for(UUID uuid : test.keySet()) {
						int timeleft = test.get(uuid);
						if (timeleft == 0) {
							test.remove(uuid);
						} else if (timeleft < 0) {
							test.remove(uuid);

						} else {
							test.replace(uuid, timeleft - 1);
						}

					}
				}


			}
		}.runTaskTimer(this, 0, 20);

		new BukkitRunnable() {


			@Override
			public void run() {
				if(SmokeScreen_Effect.isEmpty()) {return; }


				for(UUID uuid : SmokeScreen_Effect.keySet()) {
					int timeleft = SmokeScreen_Effect.get(uuid);
					if (timeleft == 0) {
						SmokeScreen_Effect.remove(uuid);
					} else if (timeleft < 0) {
						SmokeScreen_Effect.remove(uuid);

					} else {
						if(!instance.getServer().getPlayer(uuid).isOnline() || instance.getServer().getPlayer(uuid) == null){SmokeScreen_Effect.remove(uuid);return;}
						SmokeScreen_Effect.replace(uuid, timeleft - 5);
						Player player = instance.getServer().getPlayer(uuid);
						player.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, player.getLocation(), 20);
					}

				}
			}



		}.runTaskTimer(this, 0, 5);
		new BukkitRunnable() {

			@Override
			public void run() {
				if(Burn_Effect.isEmpty()) {return;}

				for(Location uuid : Burn_Effect.keySet()) {
					int timeleft = Burn_Effect.get(uuid);
					if (timeleft == 0) {
						uuid.getBlock().setType(Material.AIR);
						Burn_Effect.remove(uuid);
					} else if (timeleft < 0) {
						uuid.getBlock().setType(Material.AIR);
						Burn_Effect.remove(uuid);
					} else {
						Burn_Effect.replace(uuid, timeleft - 1);
					}

				}

			}

		}.runTaskTimer(this, 0, 20);

		new BukkitRunnable() {

			@Override
			public void run() {
				if(Lag_Effect.isEmpty()) {return;}

				for (UUID uuid : Lag_Effect.keySet()) {

					Double timeleft = Lag_Effect.get(uuid);
					if (timeleft == 0) {
						Lag.remove(uuid);
					} else if (timeleft < 0) {
						Lag.remove(uuid);

					} else {
						Player p = Bukkit.getPlayer(uuid);
						p.teleport(p.getLocation());
						Lag_Effect.replace(uuid, timeleft - 0.5);
					}

				}

			}

		}.runTaskTimer(this, 0, 10);

		new BukkitRunnable() {

			@Override
			public void run() {

				for (UUID uuid : Miner_Effect.keySet()) {

					Integer timeleft = Miner_Effect.get(uuid);
					if (timeleft == 0) {
						Miner_Effect.remove(uuid);
					} else if (timeleft < 0) {
						Miner_Effect.remove(uuid);

					} else {
						Player p = Bukkit.getPlayer(uuid);
						Sound StoneBreak;
						if (Sound.valueOf("BLOCK_STONE_BREAK") != null) {
							StoneBreak = Sound.valueOf("BLOCK_STONE_BREAK");
						} else {
							StoneBreak = Sound.valueOf("DIG_STONE");
						}
						p.playSound(p.getLocation(), StoneBreak, 1, 10);
						Miner_Effect.replace(uuid, timeleft - 1);
					}

				}

			}

		}.runTaskTimer(this, 0, 7);
		new BukkitRunnable() {
			@Override
			public void run() {
				if(TempBan.isEmpty()) {return;}
				for(UUID uuid : TempBan.keySet()) {


					int timeleft = TempBan.get(uuid);
					if (timeleft == 0) {
						TempBan.remove(uuid);
						getBanFactory().removeTempBan(uuid);
						if(Bukkit.getPlayer(uuid).isOnline()) {
							Bukkit.getPlayer(uuid).kickPlayer(ChatColor.translateAlternateColorCodes('&', getConfigManager().getMessages().getString("TempBan.expire")));
						}
					} else if (timeleft < 0) {
						TempBan.remove(uuid);
						getBanFactory().removeTempBan(uuid);
						if(Bukkit.getPlayer(uuid).isOnline()) {//ADD NO FORCE KICK
							Bukkit.getPlayer(uuid).kickPlayer(ChatColor.translateAlternateColorCodes('&', getConfigManager().getMessages().getString("TempBan.expire")));
						}

					} else {
						TempBan.replace(uuid, timeleft - 1);
					}


				}


			}
		}.runTaskTimer(this, 0, 20);
	}






}
