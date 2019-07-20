package ro.deiutzblaxo.Purgatory.Spigot.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;
import ro.deiutzblaxo.Purgatory.Spigot.Titles.Spigot1_10;
import ro.deiutzblaxo.Purgatory.Spigot.Titles.Spigot1_11;
import ro.deiutzblaxo.Purgatory.Spigot.Titles.Spigot1_12;
import ro.deiutzblaxo.Purgatory.Spigot.Titles.Spigot1_13;
import ro.deiutzblaxo.Purgatory.Spigot.Titles.Spigot1_14;
import ro.deiutzblaxo.Purgatory.Spigot.Titles.Spigot1_8;
import ro.deiutzblaxo.Purgatory.Spigot.Titles.Spigot1_9;

public class BanCommand extends Command{
	private	MainSpigot plugin;
	String reason;

	public BanCommand(String name , MainSpigot main) {
		super(name);
		plugin = main;


	}

	@Override
	public boolean execute(CommandSender sender, String arg1, String[] args) {

		OfflinePlayer player = null;
		if(plugin.getServer().getPlayer(args[0]) != null || plugin.getServer().getOfflinePlayer(args[0]) != null) {
			if(plugin.getServer().getPlayer(args[0]) != null) {
				player = plugin.getServer().getPlayer(args[0]);
			}
			else if(plugin.getServer().getOfflinePlayer(args[0]) != null) {
				player = plugin.getServer().getOfflinePlayer(args[0]);
			}
		}else {
			Bukkit.broadcastMessage("this player don`t exists!");
			return false;
		}

		if(plugin.getBanFactory().isBan(player.getUniqueId())) {
			sender.sendMessage("This player is aleady banned"); //TODO
			return false;
		}else {
			args[0] = "";
			StringBuilder stringBuilder = new StringBuilder();
			for (String arg : args) {
				stringBuilder.append(arg).append(" ");
			}
			//TODO SET A DEFAULT REASON
			reason = stringBuilder.toString();
			plugin.getBanFactory().setBan(player.getUniqueId(), args[0] , "this is a reason");
			sender.sendMessage("This player have been banned"); //TODO
			if(plugin.getConfig().getBoolean("Force-Kick")) {
				if(player.isOnline()) {
					player.getPlayer().kickPlayer("You have been banned!");
				}
			}else {
				if(player.isOnline()) {
					if(plugin.getServer().getVersion().contains("1.12")) {
						Spigot1_12 test = new Spigot1_12();
						test.Packet1_12(player.getPlayer(),ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager()
								.getMessages().getString("Ban.Format").replaceAll("%reason%", reason)));

					}else if(plugin.getServer().getVersion().contains("1.11")){
						Spigot1_11 test = new Spigot1_11();
						test.Packet1_11(player.getPlayer(),ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager()
								.getMessages().getString("Ban.Format").replaceAll("%reason%", reason)));

					}else if(plugin.getServer().getVersion().contains("1.10")) {
						Spigot1_10 test = new Spigot1_10();
						test.Packet1_10(player.getPlayer(),ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager()
								.getMessages().getString("Ban.Format").replaceAll("%reason%", reason)));

					}else if(plugin.getServer().getVersion().contains("1.9")) {
						Spigot1_9 test = new Spigot1_9();
						test.Packet1_9(player.getPlayer(),ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager()
								.getMessages().getString("Ban.Format").replaceAll("%reason%", reason)));

					}else if(plugin.getServer().getVersion().contains("1.8")) {
						Spigot1_8 test = new Spigot1_8();
						test.Packet1_8(player.getPlayer(),ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager()
								.getMessages().getString("Ban.Format").replaceAll("%reason%", reason)));

					}else if(plugin.getServer().getVersion().contains("1.14")) {
						Spigot1_14 test = new Spigot1_14();
						test.Packet1_14(player.getPlayer(),ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager()
								.getMessages().getString("Ban.Format").replaceAll("%reason%", reason)));

					}else if(plugin.getServer().getVersion().contains("1.13")) {
						Spigot1_13 test = new Spigot1_13();
						test.Packet1_13(player.getPlayer(),ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager()
								.getMessages().getString("Ban.Format").replaceAll("%reason%", reason)));
					}
					plugin.getScoreBoardAPI().createScoreboard(player.getPlayer(), "Purgatory", plugin.getTaskFactory().getTasks());
				}
			}
		}


		return false;
	}

}
