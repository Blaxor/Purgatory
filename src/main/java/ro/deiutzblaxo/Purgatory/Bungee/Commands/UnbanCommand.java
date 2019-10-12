package ro.deiutzblaxo.Purgatory.Bungee.Commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;
import ro.deiutzblaxo.Purgatory.Bungee.MainBungee;

public class UnbanCommand extends Command {

	private MainBungee plugin;
	public UnbanCommand(String name , MainBungee main) {
		super(name);
		plugin = main;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		if(!sender.hasPermission("purgatory.purge")) {
			sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "NoPermission"))));
			return;
		}
		if(args.length != 1) {

			sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "Purge.InvalidCommand"))));
			return;
		}
		if(plugin.getBanFactory().isBan(args[0])) {
			plugin.getBanFactory().removeBan(args[0]);
			plugin.getProxy().broadcast(new TextComponent(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "Purge.Broadcast")
					.replaceAll("%player%", args[0])).replaceAll("%admin%", sender.getName())));
			if(plugin.getConfigManager().getConfig().getBoolean("UnBan-Disconnect")) {
				if(plugin.getProxy().getPlayer(args[0]) != null) {
					plugin.getProxy().getPlayer(args[0]).setReconnectServer(plugin.getServerManager().getHubServer());
					plugin.getProxy().getPlayer(args[0]).disconnect(new TextComponent(ChatColor.translateAlternateColorCodes('&'
							, plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "UnBanFormat"))
							.replaceAll("%admin%", sender.getName())));
				}
			}else {
				plugin.getProxy().getPlayer(args[0]).connect(plugin.getServerManager().getHubServer());
				plugin.getProxy().getPlayer(args[0]).sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&'
						, plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "UnBanFormat"))
						.replaceAll("%admin%", sender.getName())));
			}
		}else {
			sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "Purge.notBanned").replaceAll("%player%", args[0]))));

		}

	}

}
