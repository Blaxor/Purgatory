package ro.deiutzblaxo.Purgatory.Spigot.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;
import ro.deiutzblaxo.Purgatory.Spigot.Titles.TitleManager;

public class PurgeCommand extends Command {
	private MainSpigot plugin;
	private OfflinePlayer player ;
	private TitleManager TitleManager;
	public PurgeCommand(String name, MainSpigot mainSpigot) {
		super(name);
		plugin = mainSpigot;
		TitleManager = new TitleManager(mainSpigot);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean execute(CommandSender sender, String arg1, String[] args) {
		if(!sender.hasPermission("purgatory.purge")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("NoPermission")));
			return false ;
		}
		if(args.length < 1) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getMessages().getString("Purge.InvalidCommand")));
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

		if(!plugin.getBanFactory().isBan(player.getUniqueId())) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("Purge.notBanned")
					.replaceAll("%player%", player.getName())));
			return false;
		}
		plugin.getBanFactory().removeBan(player.getUniqueId());
		plugin.getTaskFactory().removeTasks(player.getUniqueId());
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("Purge.broadcast")
				.replaceAll("%player%", player.getName()).replaceAll("%admin%", sender.getName())));
		if(player.isOnline()) {
			if(plugin.getConfig().getBoolean("Force-Kick")) {
				player.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("Purge.Format")
						.replaceAll("%admin%", sender.getName())));
			}else {
				TitleManager.Title(player.getPlayer(), ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getMessages().getString("Purge.Format")
						.replaceAll("%admin%", sender.getName())));

			}
			player.getPlayer().teleport(plugin.getWorldManager().getDefault().getSpawnLocation());
		}


		return false;
	}

}
