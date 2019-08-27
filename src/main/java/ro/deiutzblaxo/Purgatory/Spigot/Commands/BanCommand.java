package ro.deiutzblaxo.Purgatory.Spigot.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;
import ro.deiutzblaxo.Purgatory.Spigot.Titles.TitleManager;

public class BanCommand extends Command{
	private	MainSpigot plugin;
	private String reason,playername;
	private OfflinePlayer player ;
	private TitleManager titlemanager;
	public BanCommand(String name , MainSpigot main) {
		super(name);
		plugin = main;
		titlemanager = new TitleManager(main);

	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean execute(CommandSender sender, String arg1, String[] args) {
		plugin.getConfigManager().loadMessages();
		if(!sender.hasPermission("purgatory.ban")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("NoPermission")));
			return false;
		}
		if(args.length < 1) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getMessages().getString("Ban.InvalidCommand")));
			return false;
		}

		if(plugin.getServer().getPlayer(args[0]) != null || plugin.getServer().getOfflinePlayer(args[0]) != null) {
			if(plugin.getServer().getPlayer(args[0]) != null) {
				player = plugin.getServer().getPlayer(args[0]);
			}
			else if(plugin.getServer().getOfflinePlayer(args[0]) != null) {
				player = plugin.getServer().getOfflinePlayer(args[0]);
			}
		}else {
			sender.sendMessage("this player don`t exists!");
			return false;
		}


		if(plugin.getBanFactory().isBan(player.getUniqueId())) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getMessages().getString("Ban.isBan").replaceAll("%player%", player.getName())));
			return false;
		}else {
			playername = args[0];
			if(args.length >= 2) {
				args[0] = "";
				StringBuilder stringBuilder = new StringBuilder();
				for (String arg : args) {
					stringBuilder.append(arg).append(" ");
				}
				reason = stringBuilder.toString();
			}else {
				reason = plugin.getConfigManager().getMessages().getString("Ban.DefaultReason");
			}
			plugin.getBanFactory().setBan(player.getUniqueId(), playername , reason);
			Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("Ban.broadcast")
					.replaceAll("%player%", player.getName()).replaceAll("%admin%", sender.getName())));
			if(player.isOnline()) {
				if(plugin.getConfig().getBoolean("Force-Kick")) {

					player.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager()
							.getMessages().getString("Ban.Format").replaceAll("%reason%", reason)));

				}else {



					titlemanager.Title(player.getPlayer(), ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager()
							.getMessages().getString("Ban.Format").replaceAll("%reason%", reason)));


				}
				player.getPlayer().teleport(plugin.getWorldManager().getPurgatory().getSpawnLocation());
			}



		}

		return false;

	}
}
