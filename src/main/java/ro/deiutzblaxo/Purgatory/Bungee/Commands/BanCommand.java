package ro.deiutzblaxo.Purgatory.Bungee.Commands;

import java.util.UUID;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import ro.deiutzblaxo.Purgatory.Bungee.MainBungee;

public class BanCommand extends Command {
	private MainBungee plugin;
	private String name , reason;
	private UUID uuid;
	public BanCommand(String name , MainBungee main) {
		super(name);
		plugin = main;

	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(!sender.hasPermission("purgatory.ban")) {
			sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "NoPermission"))));
			return;
		}
		if(args.length < 1) {
			sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "Ban.InvalidCommand"))));
			return;
		}
		if(plugin.getProxy().getPlayer(args[0]) == null) {
			sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "Ban.PlayerOffline"))));
			return;
		}
		ProxiedPlayer player = plugin.getProxy().getPlayer(args[0]);
		uuid = player.getUniqueId();
		name = player.getName();
		if(plugin.getBanFactory().isBan(uuid)) {
			sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "Ban.isBan").replaceAll("%player%", name))));
			return;
		}else {

			if(args.length >= 2) {
				args[0] = "";
				StringBuilder stringBuilder = new StringBuilder();
				for (String arg : args) {
					stringBuilder.append(arg).append(" ");
				}
				reason = stringBuilder.toString();
			}else {
				reason = plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "Ban.DefaultReason");
			}
			plugin.getBanFactory().setBan(uuid, reason, name);

			plugin.getProxy().getPlayer(name).disconnect(new TextComponent(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "Ban.Format").replaceAll("%reason%", reason))));
			//			plugin.getProxy().getPlayer(name).disconnect(new TextComponent(ChatColor.translateAlternateColorCodes('&', plugin
			//					.getConfigManager().getMessages().getString("Ban.Format").replaceAll("%reason%", reason))));

			plugin.getProxy().broadcast(new TextComponent(ChatColor.translateAlternateColorCodes('&',
					plugin.getConfigManager().getString(plugin.getConfigManager().getMessages(), "Ban.Broadcast")
					.replaceAll("%player%", name).replaceAll("%admin%", sender.getName().replaceAll("%reason%", reason)))));
		}

	}

}
