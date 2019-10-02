package ro.deiutzblaxo.Purgatory.Spigot.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import ro.deiutzblaxo.Purgatory.Spigot.MainSpigot;
import ro.deiutzblaxo.Purgatory.Spigot.Titles.TitleManager;

public class TempBanCommand extends Command {
	private MainSpigot plugin;
	private OfflinePlayer player;
	private TitleManager titlemanager;
	private String reason , playername;
	private Integer time;
	public TempBanCommand(String name ,MainSpigot main ) {
		super(name);
		plugin = main;
		titlemanager = new TitleManager(main);
		
	}

	@Override
	public boolean execute(CommandSender sender, String arg1, String[] args) {

		if(!sender.hasPermission("purgatory.tempban")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString
					(plugin.getConfigManager().getMessages(),"NoPermission")));
			return false;
		}
		if(args.length < 3) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString
					(plugin.getConfigManager().getMessages(),"TempBan.InvalidCommand")));
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
		playername = args[0];
		time = Integer.valueOf(args[1]);
		if(args.length >= 3) {
			args[0] = "";
			args[1] = "";
			StringBuilder stringBuilder = new StringBuilder();
			for (String arg : args) {
				stringBuilder.append(arg).append(" ");
			}
			reason = stringBuilder.toString();
		}else {
			reason = plugin.getConfigManager().getString
					(plugin.getConfigManager().getMessages(),"TempBan.DefaultReason");
		}

		plugin.getBanFactory().setTempBan(player.getUniqueId(), playername, reason, time);
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString
				(plugin.getConfigManager().getMessages(),"TempBan.broadcast")
				.replaceAll("%player%", player.getName()).replaceAll("%admin%", sender.getName()).replaceAll("%time%", time + "")));
		if(player.isOnline()) {
			player.getPlayer().teleport(plugin.getWorldManager().getPurgatory().getSpawnLocation());
			if(plugin.getConfig().getBoolean("Force_Kick")) {
				player.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString
						(plugin.getConfigManager().getMessages(),"TempBan.Format").replaceAll("%reason%", reason).replaceAll("%time%", time + "")));
			}else {
				titlemanager.Title(player.getPlayer(), ChatColor.translateAlternateColorCodes('&', plugin.getConfigManager().getString
						(plugin.getConfigManager().getMessages(),"TempBan.Format").replaceAll("%reason%", reason).replaceAll("%time%", time + "")));
			}

		}

		return false;
	}

}
